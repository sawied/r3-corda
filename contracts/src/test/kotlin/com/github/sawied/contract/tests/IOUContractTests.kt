package com.github.sawied.contract.tests

import com.github.sawied.corda.iou.contract.IOUContract
import com.github.sawied.corda.iou.state.IOUState
import net.corda.core.identity.CordaX500Name
import net.corda.testing.core.TestIdentity
import net.corda.testing.node.MockServices
import net.corda.testing.node.ledger
import org.junit.Test

class IOUContractTests {
    private val ledgerServices = MockServices(listOf("com.github.sawied.contract", "com.example.flow"))
    private val miniCorp = TestIdentity(CordaX500Name("MiniCorp", "New York", "US"))
    private val megaCorp = TestIdentity(CordaX500Name("MegaCorp", "London", "GB"))
    private val superCorp = TestIdentity(CordaX500Name("SuperCorp", "Chicago", "US"))

    @Test
    fun `transaction must include Create command`() {
        ledgerServices.ledger {
            transaction {
                attachment(IOUContract.ID)
                output(IOUContract.ID, IOUState(5, megaCorp.party, miniCorp.party))
                fails()
                command(listOf(megaCorp.publicKey, miniCorp.publicKey), IOUContract.Commands.Create())
                verifies()
            }
        }
    }
}