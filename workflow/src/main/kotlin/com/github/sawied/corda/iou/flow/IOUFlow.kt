package com.github.sawied.corda.iou.flow

import co.paralleluniverse.fibers.Suspendable
import com.github.sawied.corda.iou.contract.IOUContract
import com.github.sawied.corda.iou.state.IOUState
import net.corda.core.contracts.Command
import net.corda.core.contracts.requireThat
import net.corda.core.flows.*
import net.corda.core.identity.Party
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.TransactionBuilder
import net.corda.core.utilities.ProgressTracker
import net.corda.core.utilities.ProgressTracker.Step

object IOUFlow{


    @InitiatingFlow
    @StartableByRPC
    class Initiator(val iouValue:Int,
                    val otherParty: Party
                    ): FlowLogic<SignedTransaction>(){


        companion object{
            object GENERATING_TRANSACTION : Step("Generating transaction based on new IOU.")
            object VERIFYING_TRANSACTION : Step("Verifying contract constraints.")
            object SIGNING_TRANSACTION : Step("Signing transaction with our private key.")
            object GATHERING_SIGS : Step("Gathering the counterparty's signature.") {
                override fun childProgressTracker() = CollectSignaturesFlow.tracker()
            }
            object FINALISING_TRANSACTION : Step("Obtaining notary signature and recording transaction.") {
                override fun childProgressTracker() = FinalityFlow.tracker()
            }

            fun tracker() = ProgressTracker(
                    GENERATING_TRANSACTION,
                    VERIFYING_TRANSACTION,
                    SIGNING_TRANSACTION,
                    GATHERING_SIGS,
                    FINALISING_TRANSACTION
            )
        }


        override val progressTracker = tracker()




        override fun call(): SignedTransaction {
          val notary= serviceHub.networkMapCache.notaryIdentities[0]

            //Stage 1
            progressTracker.currentStep=GENERATING_TRANSACTION
            // Generate an unsigned transaction.
            val iouState = IOUState(iouValue, serviceHub.myInfo.legalIdentities.first(), otherParty)
            val txCommand = Command(IOUContract.Commands.Create(), iouState.participants.map { it.owningKey })

            val txBuilder=TransactionBuilder(notary).addOutputState(iouState,IOUContract.ID).addCommand(txCommand)


            // Stage 2.
            progressTracker.currentStep = VERIFYING_TRANSACTION
            // Verify that the transaction is valid.
            txBuilder.verify(serviceHub)


            // Stage 3.
            progressTracker.currentStep = SIGNING_TRANSACTION
            // Sign the transaction.
            val partSignedTx = serviceHub.signInitialTransaction(txBuilder)

            // Stage 4.
            progressTracker.currentStep = GATHERING_SIGS
            // Send the state to the counterparty, and receive it back with their signature.
            val otherPartySession = initiateFlow(otherParty)
            val fullySignedTx = subFlow(CollectSignaturesFlow(partSignedTx, setOf(otherPartySession), GATHERING_SIGS.childProgressTracker()))

            // Stage 5.
            progressTracker.currentStep = FINALISING_TRANSACTION
            // Notarise and record the transaction in both parties' vaults.
            return subFlow(FinalityFlow(fullySignedTx, setOf(otherPartySession), FINALISING_TRANSACTION.childProgressTracker()))

        }

    }


    @InitiatedBy(Initiator::class)
    class Acceptor(val otherPartySession: FlowSession) : FlowLogic<SignedTransaction>() {
        @Suspendable
        override fun call(): SignedTransaction {
            val signTransactionFlow = object : SignTransactionFlow(otherPartySession) {
                override fun checkTransaction(stx: SignedTransaction) = requireThat {
                    val output = stx.tx.outputs.single().data
                    "This must be an IOU transaction." using (output is IOUState)
                    val iou = output as IOUState
                    "I won't accept IOUs with a value over 100." using (iou.value <= 100)
                }
            }
            val txId = subFlow(signTransactionFlow).id

            return subFlow(ReceiveFinalityFlow(otherPartySession, expectedTxId = txId))
        }
    }
}