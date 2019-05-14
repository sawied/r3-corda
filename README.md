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

2. if i deploy Cardapp into a production environment, it need all the platform must run in unified versions , such as JVM Corda runtime ?

3. In development , RPC call is a main function to interact with Corda Nodes, This communicate by TCP protocol? how the network location in production environment? and how to make sure client's call is safe.

4.


# Run a Cordapp #

running the following commands from the root of the project

```
gradlew clean deployNodes
## start nodes in terminal console via:
build/nodes/runnodes.bat
```

start IOUFlow iouValue: 100, otherParty: "O=PartyC,L=Paris,C=FR"

run vaultQuery contractStateType: com.github.sawied.corda.iou.state.IOUState