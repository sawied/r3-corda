Mule 

1. Build Automation
2. Test Automation
3. Version Control
4. Deployment Automation


### Sonar installation ###


Sonar supports MySQL Version 5.6 and 5.7 and must install with parameter use UTF8 charset and a case-sensitive (CS) collation ,only InnoDB storage engine is supported,

#### How to custom MySQL Configuration ####
The default configuration for MySQL can be found in /etc/mysql/my.cnf, which may !includedir additional directories such as /etc/mysql/conf.d or /etc/mysql/mysql.conf.d, so,
If /my/custom/config-file.cnf is the path and name of your custom configuration file, you can start your mysql container like this (note that only the directory path of the custom config file is used in this command):
```
$ docker run --name some-mysql -v /my/custom:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

sudo chown -R 200 /home/cicd/ci/data/nexus3
sudo chown -R polkitd /home/cicd/ci/sonar
```
show variables

default_storage_engine