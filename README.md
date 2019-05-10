# R3-Corda #

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