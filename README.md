# R3-Corda #

development guide:


*   [Set up gitlab for version repository](#/) 
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


# Install gitlab in Ubuntu #

There are two gitlab version available for community and enterprise, but for installation , we don't distinct both version, The enterprise package contains all the features for community version,
if you don't active enterprise features(license), so following guide will use enterprise version to install.

1. Install and configure the necessary dependencies
    ```bash
    sudo apt-get update
    sudo apt-get install -y curl openssh-server ca-certificates
    ```
2. Add the GitLab package repository and install the package
    ```bash
    curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ee/script.deb.sh | sudo bash
    sudo  apt-get install gitlab-ee
    sudo dpkg -i packagename # if you prefer to manual installing
    ``` 
3.Config Gitlab using a non-bundled web server

   1. Disable bundle Nginx(In /etc/gitlab/gitlab.rb set:)
      
      ```
      nginx['enable'] = false
      ```
   2. Set the username of the non-bundled web-server user
      
      By default,The Nginx user is *nginx* , In /etc/gitlab/gitlab.rb set:
      
      ```
      web_server['external_users'] = ['www-data']   
      ```
   3. Add the non-bundled web-server to the list of trusted
   
      Normally, omnibus-gitlab defaults the list of trusted proxies to what was configured in the real_ip module for the bundled NGINX.
      
      For non-bundled web-servers the list needs to be configured directly, and should include the IP address of your web-server if it is not on the same machine as GitLab. Otherwise, users will be shown as being signed in from your web-server’s IP address.
      
      ```
      gitlab_rails['trusted_proxies'] = [ '192.168.1.0/24', '192.168.2.1', '2001:0db8::/32' ]
      ```
      
   4. Run sudo gitlab-ctl reconfigure for the change to take effect. 
      ```
      sudo gitlab-ctl reconfigure
      ``` 
   5. Setting Nginx web server
      
      Download the right web server configs from [gitlab recipes repository](https://gitlab.com/gitlab-org/gitlab-recipes/tree/master/web-server),Make sure you pick the right configuration file depending whether you choose to serve GitLab with SSL or not. The only thing you need to change is YOUR_SERVER_FQDN with your own FQDN and if you use SSL, the location where your SSL keys currently reside. You also might need to change the location of your log files.
      ```
      sudo ln -s /etc/nginx/sites-available/gitlab gitlab
      ```
         
   6. Useful command for gitlab
      ```
      sudo gitlab-ctl start    # start all gitlab components
      sudo gitlab-ctl stop        # stop all gitlab components
      sudo gitlab-ctl restart        # restart all gitlab components
      sudo gitlab-ctl status        # check the service status
      sudo gitlab-ctl reconfigure        # startup service
      sudo vim /etc/gitlab/gitlab.rb        # modify default configuration
      gitlab-rake gitlab:check SANITIZE=true --trace    # check gitlab
      sudo gitlab-ctl tail                 # look up logs
      ```   
4. Install GitLab Runner
   1. Add GitLab’s official repository:
    ```
    curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash
    ``` 
   2. Install the latest version of GitLab Runner, or skip to the next step to install a specific version:
    ```
    sudo apt-get install gitlab-runner
    sudo apt-get install gitlab-runner=10.0.0 # (if you use version8.0,select runner version gitlab-ci-multi-runner-1.11.2-1)
    apt-get install gitlab-ci-multi-runner=1.11.2
    yum install gitlab-ci-multi-runner-1.11.2-1
    ```
    > manual install gitlab-runner:
    > uninstall ,query  has installed package , and remove ```rpm -qa | grep -i gitlab```, ```sudo rpm -e gitlab-ci-multi-runner-9.5.0-1.x86_64```
    > install from rpm package : ```sudo rpm -ivh gitlab-ci-multi-runner_amd64.rpm```, and register : ```sudo gitlab-ci-multi-runner register```
    > query gitlab-runner status : ```sudo gitlab-ci-multi-runner status```
5. Registering Runners
   > before run following command,make sure you have got a registration token from gitlab admin page (admin->runners) 
   Registering a Runner is the process that binds the Runner with a GitLab instance
   ```
   sudo gitlab-runner register
   ```
   
> ***Note: For Git***    
> Sometime, you want to change the gitignore, but not everyone on the team wants there changes. you can add ignore in your local global config as following:
> ```git config --global core.excludesfile ~/.gitignore``` , Add .idea to user specific gitignore file ```echo .idea > ~/.gitignore``` 


> ***Note:For Gradle***
> Using private repository to speech up downloading is useful, however, how to do this for gradle?
> put the ```gradle/init.gradle``` file into ```~/.gradle``` to change the default dependencies repository to a custom one. 

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