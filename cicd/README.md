Mule 

1. Build Automation
2. Test Automation
3. Version Control
4. Deployment Automation

### Jenkins configuration ###

1. JVM Setting
   
   default JVM installation directory JAVA_HOME is "**/usr/lib/jvm/java-1.8-openjdk**"
   
   default maven settings:
   
   Maven Name: maven 3.6.1
   Maven Home: /usr/share/maven
   
   
   
   
   
   
2. Nexus defailt credentials

   Default credentials are: admin / admin123



### Sonar installation ###


Sonar supports MySQL Version 5.6 and 5.7 and must install with parameter use UTF8 charset and a case-sensitive (CS) collation ,only InnoDB storage engine is supported,

#### How to custom MySQL Configuration ####
The default configuration for MySQL can be found in /etc/mysql/my.cnf, which may !includedir additional directories such as /etc/mysql/conf.d or /etc/mysql/mysql.conf.d, so,
If /my/custom/config-file.cnf is the path and name of your custom configuration file, you can start your mysql container like this (note that only the directory path of the custom config file is used in this command):
```
$ docker run --name some-mysql -v /my/custom:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

sudo chown -R 200 /home/cicd/ci/data/nexus3
sudo chown -R 1000 /home/cicd/ci/data/jenkins
sudo chown -R polkitd /home/cicd/ci/sonar
sudo chgrp -R docker docker
```
show variables

default_storage_engine


 mvn clean install -DskipTests
 export  MVN_HOME=/usr/share/maven
 export PATH=$MVN_HOME/bin:$PATH
 /var/jenkins_home/workspace/swire-development/scct-middleware/
 <localRepository>/var/jenkins_home/.m2</localRepository>
 
 mvn -s ../scct-middleware-aggregator/settings.xml clean install -DskipTests 

deploy to cloudhub
```
mvn -p cloudHub clean package deploy -DmuleDeploy -DskipTests -Danypoint.username=sawied2009 -Danypoint.password=password -Dcloudhub.environment=Sandbox
```

"environmentId": "bccf7ba8-354b-4247-a856-ba18c9b2cd2c",
"organizationId": "f83d932a-a3b8-45dc-a2b8-f0317b8c4345",


anypoint.platform.analytics_base_uri=https://analytics-ingest.anypoint.mulesoft.com
anypoint.platform.client_id=8234b3859bf146118261ba391b85391e
anypoint.platform.client_secret=795484d9295d405fA0DBBD9AD7457bDd
anypoint.platform.base_uri=https://anypoint.mulesoft.com



client_id : a69e6d9c301a44b394d138e0f70d79d0
client_secret:875d94B581344D0fA205008440814af3

