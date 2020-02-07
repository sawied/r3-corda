# Cloud Migration #

1. Software Architecture
   1. SOA,DSA(Distributed Service Architecture),MicroServices
   2. MicroServices Architecture Design
      1. Bounded Context
      2. Services Management
      3. MicroService in Cloud 
   3. MicroServices
      1. Aim of MicroService
      2. Difficult of implement MicroService
2. Technology Architecture
   1. Front End
      1. Mobile H5
      2. Mobile Native
      3. PC Web
      4. POS
   2. API Gateway/SLB
      1. Traffic Gateway
         1. Log
         2. Traffic Control
         3. IP Black and white list
         4. Firewall
         5. Kong/OpenRedty
      2. Business Gateway
         1. Vert.X
         2. API SDK
         3. Rest API/WebSocket
   3. Business Application
         1. Stateless&Stateful service
         2. Release Management
         3. Service monitoring and link tracing
         4. Monitoring
         5. Log collection
         6. Auto scaling
         7. Health check
         8. Encryption Service
         9. Access Control
         10. 2 Factor Authentication
         11. Key Management
         10. Distributed transaction
   4. Shared Service Center(BME)
         1. EDAS(Enterprise distributed service framework)
         2. Container Service(Kubernetes)
         3. Message queue
         4. Object Storage Service (OSS)
         5. Database service
         6. Cache Service
         7. VPC Network

3. Technology  Stack
   1. Spring boot
   2. Apache Dubbo 
   3. Restful API
   4. Swagger API
   5. Congfiguration Center(ACM)
   6. Spring Cloud
   7. Spring MVC
   8. Spring data JPA
   9. Log4j2
   10. React(Frontend)
   11. Java(vjdk8u65)
   12. Container platform(Kubernetes)
   13. Middleware(EDAS, MQ, ACM)
4. Devops CI
   1. git branch management & release Version
   2. Docker Image 
   3. Docker Image repository
   4. git repository
   5. git Config Center
   6. Nexus 
   7. Jira
   8. Collaborate
   9. Jenkins pipeline
   10. SonarQube
   11. Kubernetes Cluster For different environment
   12. Release version Controll & Rollback
   13. FlyWay script migration
5. Data migration
   1. Data Security
      1. On Premise
         1. identify sensitive data 
         2. Load data and encrypt data with Thales VTS
         3. Stage database
      2. On Cloud
         1. Operational database
         2. ETL & Application database
         3. Thales API for Detokenize data
      3. Security
         1. White IP list 
         2. VPC
         3. RDS SSL
         4. TDE(Transparent Data Encryption)
   2. Blob data 
      1. OSS storge
      2. OSS Encryption
   3. Data Warehouse
      1. ETL
      2. MapRedule
      3. Sqoop,Spring batch
      4. NetFlix Conductor
6. Legacy System
   1. API Integration Bus
   2. Split Monolithic application
   
   -------------------------------------
   # Task Assignment #
   
   |Index|Task|Description|Owner|Start|End|
   |-----|:----|:-----------|-----|----|----|
   |1   |  MicroServices Introduction | Introduce New Architecture for Enterprise application Large-scale and how to implement and the advancement of Enterprise architecture in cloud | James X W Zhang  |  10-Feb | 14-Feb  |
   |2   | Frontend architecture  | Introduce popular framework from frontside include how to adapter different terminal device for API Integration，include new UI Framework React and related component framework| Yangyanbin  | 10-Feb  |  21-Feb |
   |3   |API Gateway   |Introduce API Gateway for Microservice Integration, Traffic Gateway and Business Gateway，how to handler Security issue and API Control,kong and  Vert.X| James X W Zhang  |  17-Feb |  21-Feb |
   |4   | Spring cloud & Dubbo Framework in Cloud  | Base on Dubbo & Spring cloud to implement the scalable MicroService Application, include Dubbo, ACM register, Load balancer and Circuit Breaker etc   | Mory  | 10-Feb  | 14-Feb  |
   |5   |Enterprise Core Service   |Introduce how to implement Access Control, API Management,Log tracking & Cache  | James X W Zhang  |  24-Feb | 28-Feb   |
   |6   |Devops Tools  | Jenkins,git,SonarQube, Jenkins,Docker and Kubernetes to manage project life cycle | zhaopeng  | 10-Feb  |14-Feb |
   |7   |Data Migration   |As a Company, how to migrate on premise data to cloud , data Security, solution, such as ETL tools ,Thales, Stage database and data Encryption  |   Derex|  10-Feb |14-Feb   |
   |8   | Big data   |Collect data from different channel, and do data analysis, data Storage and MapRedule |  Derex |17-Feb   |21-Feb   |