# R3-Corda #

development guide:


 
*   [Set up for CorDapp development](https://docs.corda.net/getting-set-up.html)
*	[Structuring CorDapp project](https://docs.corda.net/writing-a-cordapp.html)
*	[Build a CorDapp project](https://docs.corda.net/cordapp-build-systems.html)
*	[Writing a contract](https://docs.corda.net/tutorial-contract.html)
*	[Writing a contract test](https://docs.corda.net/tutorial-test-dsl.html)
*	[Using client RPC API](https://docs.corda.net/tutorial-clientrpc-api.html)
*	[Building transactions](https://docs.corda.net/tutorial-building-transactions.html)
*	[Writing flows](https://docs.corda.net/flow-state-machines.html)
*	[Writing flow test](https://docs.corda.net/flow-testing.html)
*	[Using attachment](https://docs.corda.net/tutorial-attachments.html)
*	[Event scheduling](https://docs.corda.net/event-scheduling.html)
*	[CorDapp API](https://docs.corda.net/corda-api.html) 



# Corda Trainning Contents #

1. Block chain Basic Concept
2. R3 Corda introduce
3. The key concepts and features of the Corda platform
   1. The network
   2. Ledger
   3. States
   4. Transactions
   5. Contracts
   6. Flow
   7. Consensus
   8. Notaries
   9. Vault
   10. Time-Windows
   11. Nodes
   12. Transaction Tear-offs
   13. Trade-offs
   14. Deterministic JVM
4. CorDapp development
   1. Set up for CorDapp Development
   2. Kotlin programming language introduce
   3. Gradle build tool
   4. developing an IOU application
      1. Structuring Cordapp project
      2. writing a contract
      3. build trasctions
      4. writing flows
      5. Using client RPC API  
5. Corda API 
   1. States
   2. Persistence
      1. Schemas
      2. JPA
   3. Contracts
   4. Vault Query
   5. Transactions
   6. Flows
   7. Identity
   8. ServiceHub
   9. Testing
6. Corda Network builder
   1. Nodes 
   2. Docker
 




### Tools Setup ###
* Java JVM require at least version **8u171** 
* Git
* Gradle
* Kotlin

> Solve ports conflict 
>```
> netstat -aon|findstr "10000"
> 
> C:\Windows\System32>netstat -aon|findstr "10000"
>  TCP    127.0.0.1:10000        0.0.0.0:0              LISTENING       10108
> 
> tasklist|findstr "10108"
>````
# Key Components #
State objects are the data held on the ledger, which represent the current state of an instance of a contract, and are used as inputs and outputs of transactions.

The core of a smart contract is the executable code which validates changes to state objects in transactions. State objects are the data held on the ledger, which represent the current state of an instance of a contract, and are used as inputs and outputs of transactions.

1. what is the key word @Suspendable of ability ? if someone missed this annotition, what is the behavior it is?
   
   initiateFlow()
   session.send()
   session.receive()

2. if i deploy Cardapp into a production environment, it need all the platform must run in unified versions , such as JVM Corda runtime ?
  
   Application decided
   
   Spring boot, Hot-hot(Corda enterprise)

3. In development , RPC call is a main function to interact with Corda Nodes, This communicate by TCP protocol? how the network location in production environment? and how to make sure client's call is safe.
   
   RPC overs ssl
   

4. Event scheduling 's aim for issue
   
   User case: Owner change 


5. database;


# Run a Cordapp #

running the following commands from the root of the project

```
gradlew clean deployNodes
## start nodes in terminal console via:
build/nodes/runnodes.bat
```

start IOUFlow iouValue: 100, otherParty: "O=PartyC,L=Paris,C=FR"

run vaultQuery contractStateType: com.github.sawied.corda.iou.state.IOUState