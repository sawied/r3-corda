package com.github.sawied.corda.iou.flow.test

import com.github.sawied.corda.iou.flow.IOUFlow
import com.github.sawied.corda.iou.schema.IOUSchemaV1
import com.github.sawied.corda.iou.state.IOUState
import net.corda.core.node.services.queryBy
import net.corda.core.node.services.vault.Builder.equal
import net.corda.core.node.services.vault.QueryCriteria
import net.corda.core.node.services.vault.builder
import net.corda.core.utilities.getOrThrow
import net.corda.testing.node.MockNetwork
import net.corda.testing.node.MockNetworkParameters
import net.corda.testing.node.StartedMockNode
import net.corda.testing.node.TestCordapp
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class IOUFlowTests {

    private lateinit var network: MockNetwork
    private lateinit var a: StartedMockNode
    private lateinit var b: StartedMockNode
    private val value = 5

    @Before
    fun setup() {
        network = MockNetwork(MockNetworkParameters(cordappsForAllNodes = listOf(
                TestCordapp.findCordapp("com.github.sawied.corda.iou.contract"),
                TestCordapp.findCordapp("com.github.sawied.corda.iou.flow")
        )))
        a = network.createPartyNode()
        b = network.createPartyNode()

        // For real nodes this happens automatically, but we have to manually register the flow for tests.
        // listOf(a, b).forEach { it.registerInitiatedFlow(CreatePersonalInfoFlow.Acceptor::class.java) }
        network.runNetwork()
    }

    @After
    fun tearDown() {
        network.stopNodes()
    }

    @Test
    fun `create a iou state success`() {
        val flow = IOUFlow.Initiator(iouValue = value, otherParty = b.info.legalIdentities.first())
        val iouflow = a.startFlow(flow)
        network.runNetwork()
        iouflow.getOrThrow()
        a.transaction {
            val queryCriteria =
                    builder {
                        val queryCriteria = QueryCriteria.VaultQueryCriteria()
                        val borrowName = IOUSchemaV1.PersistentIOU::borrowerName.equal(b.info.legalIdentities.first().name.toString())
                        val lenderName = IOUSchemaV1.PersistentIOU::lenderName.equal(a.info.legalIdentities.first().name.toString())
                        queryCriteria.and(QueryCriteria.VaultCustomQueryCriteria(borrowName)).and(QueryCriteria.VaultCustomQueryCriteria(lenderName))
                    }
            val validIOU = a.services.vaultService.queryBy<IOUState>(queryCriteria).states
            assertEquals(1, validIOU.size)
            val state = validIOU.single().state
            assertEquals(value, state.data.value)
        }
    }
}