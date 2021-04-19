# i2i_netmed_backup

Patient & Vital module are enabled with Basic Auth

Username / Password : i2i/123

# Open Bugs:

1.In Feign Call, need to apply circuit breaker pattern
2.In Feign Call, Need to add Authentication Header in order to make a Call to User-Module (which is unsecured as of now)


## i2i_Netmed_Vital_Sign_Module

### Requirment Analysis

Pulse Rate : 60 to 100 beats per minute. - int

Body Temperature : 95 degree F to 115 degree F

On July 10, 1980, 52-year-old Willie Jones of Atlanta was admitted to the hospital with heatstroke and a temperature of 115 degrees Fahrenheit. He spent 24 days in the hospital and survived.

Spo2 level: 60 to 100 - int

I had seen spo2 drops below 70 in Covid Affected Patients in Real life. But normal range is between 85 to 100

Weight - int

Blood Sugar level : 120 to 300 (3 Hours after eating)



<!--- ![Image of Yaktocat](https://octodex.github.com/images/yaktocat.png)--->


## SQL Scripts:

create database netmed_dev

use netmed_dev

create table netmed_role(role_id Integer primary key, role_name varchar(40));

insert into netmed_role values(1,"Doctor");

select * from netmed_role


create table netmed_user(user_id Integer Primary key, user_name varchar(256), password varchar(256), role_id Integer references netmed_role (role_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp);

insert into netmed_user values(1, "Hari Prasath", "xyz", 1, "Admin","2021-01-19 03:14:07" , "Admin", "2021-01-19 03:14:07");
insert into netmed_user values(2, "Nataraj", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");

select * from netmed_user


create table netmed_patient_mst(patient_id Integer, user_id long references netmed_user (user_id), dob date, age integer, gender integer, mobile varchar(20), email varchar(256), address varchar(256), zipcode integer, subscription_start_date date, subscription_end_date date);

alter table netmed_patient_mst add created_by varchar(256), add created_date timestamp , add last_modified_by varchar(256), add last_modified_date timestamp 

0 - Male
1 - Other
2 - Others

insert into netmed_patient_mst values(1, 2, '1995-09-26', 25, 0, "9842482716", "nataraj2609@gmail.com", "219 Florida", 608602, '2020-09-26', '2030-09-26')

update netmed_patient_mst set created_by = "Doctor hari",  created_date = "2021-01-19 03:14:07",
last_modified_by = "Doctor hari",  last_modified_date = "2021-01-19 03:14:07"
where patient_id = 1 and user_id = 2

select * from netmed_patient_mst

create table netmed_vital_mst(checkup_id integer, patient_id integer references netmed_patient_mst (patient_id) ,checkup_date date, body_temperature float, pulse_rate integer, weight integer, spo2 integer, blood_sugar_level integer, examiner_id integer references netmed_user (user_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp )

insert into netmed_vital_mst values(1, 1, '2021-02-02', 98.2, 93, 80, 98, 240, 1, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07")

select * from netmed_vital_mst

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

https://github.com/ThilakrajSelvam/user-module/tree/main/src/main/java/com/playground/usermodule

https://github.com/gokulsankar715/board1/blob/master/HealthCareVitalSignsModule/src/main/java/com/ideas2it/health/vital/signs/Model/VitalSign.java


Training - Learnt things

14. Service Discovery ( Consul)				 -----------Server Agent / dashboard
12. Spring Boot Actuator and configure custom metrics
2. Spring Boot and its components
3. Spring Cloud Config(yml)
4. Config Management based on profile
6. Spring Persistence Layer(JPA,Entitymanager,Transaction Manager,Named Queries)
10. API Documentation (Swagger)
9. Caching Data in Spring(Redis,Memcache)   -----------Server Agent 
8. AOP (Custom Annotation)
13. Containerization with Docker (Use linux and low size images)
1. Microservices Architecture and Its Best Practices
5. Hashicorp Vault                          -----------Server Agent - dashboard


15. NoSQL Database(Mongodb/Dynamodb)
7. Authentication Filters(Basic and oauth2,JWT)
16. Queuing (RabbitMQ/ActiveMQ/SQS)
17. Streaming (Kafka/Kinesis)


18. Search (Elasticsearch/ Cloud search)
21. Logging (ELk Stack)
19. Unit test(Mockito) and Coverage using Atlassian Clover
22. Distributed tracing with jaeger
20. HikariCP Configurations to control connections



![Image of Itachi](https://i0.wp.com/ramenswag.com/wp-content/uploads/2017/11/Uchiha_Itachi_quotes_on_life_and_death.jpg?resize\u003d1060%2C1060\u0026ssl\u003d1)


create database netmed_dev

use netmed_dev

create table netmed_role(role_id Integer primary key, role_name varchar(40));

insert into netmed_role values(1,"Doctor");

select * from netmed_role
______________________________________________________________________________________________________________________________________________________________________


create table netmed_user(user_id Integer Primary key, user_name varchar(256), password varchar(256), role_id Integer references netmed_role (role_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp);

insert into netmed_user values(1, "Hari Prasath", "xyz", 1, "Admin","2021-01-19 03:14:07" , "Admin", "2021-01-19 03:14:07");
insert into netmed_user values(2, "Nataraj", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");
insert into netmed_user values(3, "Thilak", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");
insert into netmed_user values(4, "Gokul", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");
insert into netmed_user values(5, "Dinesh", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");
insert into netmed_user values(6, "Abhi", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");
insert into netmed_user values(7, "Dharshini", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");


select * from netmed_user



create table netmed_patient_mst(patient_id Integer, user_id long references netmed_user (user_id), dob date, age integer, gender integer, mobile varchar(20), email varchar(256), address varchar(256), zipcode integer, subscription_start_date date, subscription_end_date date);

alter table netmed_patient_mst add created_by varchar(256), add created_date timestamp , add last_modified_by varchar(256), add last_modified_date timestamp 

0 - Male
1 - Other
2 - Others

insert into netmed_patient_mst values(1, 2, '1995-09-26', 25, 0, "9842482716", "nataraj2609@gmail.com", "219 Florida", 608602, '2020-09-26', '2030-09-26')

update netmed_patient_mst set created_by = "Doctor hari",  created_date = "2021-01-19 03:14:07",
last_modified_by = "Doctor hari",  last_modified_date = "2021-01-19 03:14:07"
where patient_id = 1 and user_id = 2


select * from netmed_patient_mst




create table netmed_vital_mst(checkup_id integer, patient_id integer references netmed_patient_mst (patient_id) ,checkup_date date, body_temperature float, pulse_rate integer, weight integer, spo2 integer, blood_sugar_level integer, examiner_id integer references netmed_user (user_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp )


insert into netmed_vital_mst values(1, 1, '2021-02-02', 98.2, 93, 80, 98, 240, 1, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07")


select * from netmed_vital_mst

-----------------------------------------------------------------------------------------------------------------------

//User Module

ManyToOne Mapping Explained well

http://fruzenshtein.com/bidirectional-many-to-one-association/

//Model Mapper

https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/


Create 
Read		/id
update      /id
Delete      /id

/netmed-user-api/getallusers?page=1&limit=10&sort=asc/des


/netmed-user-api/search=abc

-----------------------------------------------------------------------------------------------------------------------
# To Expose all Actuators

management:
  endpoints:
    web:
      exposure:
        include: "*"

-----------------------------------------------------------------------------------------------------------------------
# Swagger

https://medium.com/swlh/restful-api-documentation-made-easy-with-swagger-and-openapi-6df7f26dcad

https://medium.com/weareservian/get-started-with-hashicorp-vault-cc132dce627d
-----------------------------------------------------------------------------------------------------------------------

# Redis Cache Implementation

@EnableCaching in Main Application

Pom
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-data-redis</artifactId>
		</dependency>

Added CacheConfig Class

@Cacheable for Get Method
@CachePut for Post, Put Methods
@CacheEvict for Delete Method
-----------------------------------------------------------------------------------------------------------------------

# Aop Implementation

		Add Aop Dependency

		Add a new annotation @interface

		Add a Aspect Class with advice around above annotation and write code for logging

		Annotate Controller with this annotation
-----------------------------------------------------------------------------------------------------------------------

# Vault Implementation

vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"

Add Dependency

Added in Bootstrap.yml

   vault:
      scheme: http
      kv:
        enabled: true
      uri: http://localhost:8200
      token: 00000000-0000-0000-0000-000000000000

Put key in localhost:8200/ui

	vault kv put secret/user-module dbusername=root
    vault kv put secret/user-module dbpassword=root  //user-module is referring to Spring.application.name

Username Password Sealed inside Vault - in Git

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/netmed_dev
    username: ${dbusername}
    password: ${dbpassword}
    driver-class-name: com.mysql.jdbc.Driver

https://medium.com/@rameez.s.shaikh/spring-cloud-hashicorp-vault-hello-world-example-f92dcee4cab1

-----------------------------------------------------------------------------------------------------------------------

# Docker:

	Add a Dockerfile
		FROM openjdk:15
		EXPOSE 8080
		ADD target/netmed-user-module.jar netmed-user-module.jar 
		ENTRYPOINT ["java","jar","/netmed-user-module.jar"]
	cd..
	cmd >> docker build -t netmed-user-module.jar .
		   docker image ls
		   docker run -p 9001:8080 netmed-user-module.jar

-----------------------------------------------------------------------------------------------------------------------

# Running Consul Server via Docker Container

	docker pull consul
	docker image ls

Configure and run a Consul server
		
	>>docker run -d -p 8500:8500 -p 8600:8600/udp --name=my-consul-server consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0


-d : detached (runs in background)
-p (maps Port)
--name : giving a name to container
consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0  => Actual consul commands

If you see docker desktop, there will be running container, open url => http://localhost:8500/ui/dc1/services

To find the Ip Address

	docker exec my-consul-server consul members

	Node      Address          Status  Type    Build  Protocol  DC   Segment
	server-1  172.17.0.2:8301  alive   server  1.9.3  2         dc1  <all>

----------------------------

			TESTING PURPOSE [Not Recommended]

			Configure and run a Consul client

			Here we are not using -d detached mode in order to view logs of the client

				>>docker run --name=fox consul agent -node=client-1 -join=172.17.0.2

			We started a client called fox

				>>docker exec my-consul-server consul members
				To verify in a new cmd
------------------------------------------------------------------------------------

#  Redis

docker pull redis
docker run --name my-redis-server -d redis

redis-cli ping

------------------------------------------------------------------------------------

For Vault Run with Docker

docker run -d --name sample-just-to-see-vault-dev --link consul-server:consul -p 8200:8200 --cap-add=IPC_LOCK vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"

------------------------------------------------------------------------------------

Profiles dev will be added to Vmargs Environment Variable - Run Configuration while developing apps

But on running Docker Image, you just need to add options like 

$ docker run -e JAVA_OPTS="-DTOMCAT=Y -DOracle.server=1234 [...]" your_image:your_tag 

So in our case
	
	docker run -d -e JAVA_OPTS=" -Dspring.profiles.active=dev"

------------------------------------------------------------------------------------


HOW REDIS STORES DATA - LIST OF USER PAGE

	First, I flush all keys stored in redis in order to remove old cache entries (never do this in production as this removes all data from redis):

	> redis-cli FLUSHALL
	Then activate caching in my application, and see what redis does:

	> redis-cli MONITOR

In contrast to the @Cacheable annotation, this annotation does not cause the advised method to be skipped. Rather, it always causes the method to be invoked and its result to be stored in the associated cache if the condition() and unless() expressions match accordingly. Note that Java8's Optional return types are automatically handled and its content is stored in the cache if present.

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/CachePut.html

https://www.baeldung.com/spring-cache-tutorial

For Post,

createUser method is invoked at 4:19:36 Pm
Then is EXISTS is checked in redis cache, it will be false by default
Hence SET happens in cache
------------------------------------------------------------------------------------
CODE REVIEW:

	Dont use Autowired, Use Constructor injection --	http://olivergierke.de/2013/11/why-field-injection-is-evil/
			Constructor injection:
					++ safe code
					 - more code to write (see the hint to Lombok)
					 + easy to test

	Url naming Pattern 
	For Delete , Some Entity needs to be sent
	Return Long if method return value is of long in controller
	For Repository 

------------------------------------------------------------------------------------

# MongoDB Auditing in User Module
	
	Install mongoDB
		Add dependency for mongoDB - ONLY IF MONGODB AS A DATABASE [In this eg., sql - DB Mongo - for Auditing]
		   <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-data-mongodb</artifactId>
	        </dependency>


		    Add uri in the bootstrap.yml file as

		    spring.data.mongodb.uri=mongodb://myusername:mypassword@localhost:27017/nullbeans

		Actual one is 
		        spring.data.mongodb.uri=mongodb://localhost/usermodule_auditing
		Adding Vault secret as
				mongocred=mongodb://localhost/usermodule_auditing

				spring:
					data:
						mongodb:
							uri: ${mongocred}

FOR AUDITING ONLY

Javers is needed

		JaVers is an open source framework that provides auditing functionality in object oriented data environments. Javers allows developers to log changes to their entities into a JaVers repository, which can later be used to perform change auditing and tracking tasks

		<dependency>
			<groupId>org.javers</groupId>
			<artifactId>javers-spring-boot-starter-mongo</artifactId>
			<version>5.14.0</version>
		</dependency>

 In application.yml file

 	javers:
	  mongodb: 
	    uri: "${mongocred}"

Adding Vault secret as
				mongocred=mongodb://localhost/usermodule_auditing

		Add the annotation @JaversSpringDataAuditable for whom auditing to be done	

	@JaversSpringDataAuditable
	public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

	[Sql - DB Mongo - Auditing : works for Save, Update]

------------------------------------------------------------------------------------
# RabbitMq - open source, multi protocol message broker (message-oriented middleware)

Gem:
	https://stackoverflow.com/questions/51578794/docker-rabbitmq-how-to-expose-port-and-reuse-container-with-a-docker-file

	First pull the docker image
		docker pull rabbitmq

	As per documentation	
		There is a second set of tags provided with the management plugin installed and enabled by default, which is available on the standard 3-managementent port of 15672, with the default username and password of guest / guest:

		$ docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3-management


	docker run -d --hostname my-rabbit --name rabbitMq -p 15672:15672 -p 5672:5672 rabbitmq:3-management 


Process:
	In User Module, once user is created, a message notification will be sent to the Patient Module Rest EndPoint "/notifications"

		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit-test</artifactId>
            <scope>test</scope>
        </dependency>
 
Steps: [Publisher]
	1. Create a Queue
	2. Create a Exchange
	3. Make a bond between two
	4. JsonMessageConverter
	5. create a bean to return RabbitTemplate

	In Service Class
	Inject
		    private final RabbitTemplate rabbitTemplate;
		    and add code like

		rabbitTemplate.convertAndSend(RabbitMqConfig.NEW_USER_NOTIFY_EXCHANGE, RabbitMqConfig.NEW_USER_ROUTING_KEY, userEntity);

Steps: [Consumer]
	1. Create a Queue
	2. Create a Exchange
	3. Make a bond between two
	4. JsonMessageConverter
	5. create a bean to return RabbitTemplate

	Add a Dto with Lombok - NoArgs and All Args Data

	/**
     * Listener function to listen for RabbitMq Messages
     *
     * @param userDto
     */
    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void messageListener(UserDto userDto) {
        System.out.println("NEW USER: " + userDto.getUserName() + " has been created by "
                + userDto.getCreatedBy());
        System.out.println("Please book an appointment to Collect Patient and Vital Details");
    }
------------------------------------------------------------------------------------
# Kafka [Deprecated]

For Kafka [Run a dedicated cmd for each step in C:\kafka_2.13-2.7.0\bin\windows dir]

1. start zookeeper.start bat file like below[In windows, dir bug is there Dont keep this folder with name having space (like 		Program Files)]

	zookeeper-server-start.bat C:\kafka_2.13-2.7.0\config\zookeeper.properties

2. start kafka server

	kafka-server-start.bat C:\kafka_2.13-2.7.0\config\server.properties

3. Create Topic:
		
		kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic javatechie

4. Produce a message
	
	kafka-console-producer.bat --broker-list localhost:9092 --topic javatechie

5. Consume a message

	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic javatechie



For Streaming using Kafka, we need 3 dependencies

	Cloud Stream - this acts as intermediate
	Kafka 		 - actual tool
	Kafka Binder - this is a bridge

	In case, if we need to change to RabbitMq means, Only dependencies needs to be changed as

	Cloud Stream - this acts as intermediate
	Rabbitmq	 - actual tool
	Rabbitmq Binder - this is a bridge [Visit docs.springs.io page]


Process:

Run Zookeeper server
Run Kafka Server

Create a Topic "netmed_new_user"

To list down all topics
	kafka-topics.bat --list --zookeeper localhost:2181

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic netmed_new_user


Add dependencies
	
	        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream</artifactId>
	        </dependency>
	         <dependency>
	            <groupId>org.springframework.kafka</groupId>
	            <artifactId>spring-kafka</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.kafka</groupId>
	            <artifactId>spring-kafka-test</artifactId>
	            <scope>test</scope>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-stream-binder-kafka</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-stream-test-support</artifactId>
	            <scope>test</scope>
	        </dependency>


Publisher: [Source]

	Add @EnableBinding(Source.class) to the Spring Main Application

	Add this code to the service class

		private final MessageChannel messageChannel;

        messageChannel.send(MessageBuilder.withPayload(createdUserDto).build()); 
        		//MessageBuilder from org.springframework.messaging.support

    Add TOPIC to application.yml file

    	Spring:
    		cloud:
			    stream:
			      bindings:
			        output:
			          destination: netmed_new_user


Consumer: [Sink]

		Add @EnableBinding(Sink.class) to the Spring Main Application

		@StreamListener("input")

			Add Above annotation to a method

		Add TOPIC to application.yml file

    	Spring:
    		cloud:
			    stream:
			      bindings:
			        input:
			          destination: netmed_new_user

KAFKA : @EnableBinding and @StreamListener are deprecated as of Mar 2021

Solution:

Use StreamBridge instead

	https://www.youtube.com/watch?v=IhkvjyasLtw&ab_channel=Confluent

	https://github.com/CloudKarafka/java-kafka-example/blob/master/src/main/java/KafkaExample.java
	https://assets.confluent.io/m/2849a76e39cda2bd/original/20201119-EB-Kafka_The_Definitive_Guide-Preview-Chapters_1_thru_6.pdf?_ga=2.43774117.1871732057.1615270353-2027185234.1615270353
	https://kafka-tutorials.confluent.io/?_ga=2.84137113.1871732057.1615270353-2027185234.1615270353
	https://www.confluent.io/blog/category/apache-kafka/
------------------------------------------------------------------------------------

# Elastic Search:

	Elasticsearch offers speed and flexibility to handle data in a wide variety of use cases:

		Add a search box to an app or website
		Store and analyze logs, metrics, and security event data
		Use machine learning to automatically model the behavior of your data in real time
		Automate business workflows using Elasticsearch as a storage engine
		Manage, integrate, and analyze spatial information using Elasticsearch as a geographic information system (GIS)
		Store and process genetic data using Elasticsearch as a bioinformatics research tool

TO start using in docker

Create user defined network (useful for connecting to other services attached to the same network (e.g. Kibana)):

	$ docker network create elastic_network

Run Elasticsearch:

	docker run -d --name elasticsearch --net elastic_network -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.11.1

Launch localhost:9200 in browser and ensure elastic search is up.
____________________________________________________________________________________________________________________________

	curl -X PUT "localhost:9200/customer/_doc/1?pretty" -H 'Content-Type: application/json' -d {"name": "John Doe"}
	//USE POSTMAN

	Make a get request
	localhost:9200/customer/_doc/1

	let us add 1000 dummy data => POST
		localhost:9200/bank/_bulk?pretty&refresh 
		Take data from https://github.com/elastic/elasticsearch/blob/master/docs/src/test/resources/accounts.json?raw=true

	Get => localhost:9200/_cat/indices?v=true 		//For successful insertion of 1000 records

	NOW DO A SEARCH

	curl -X GET "localhost:9200/bank/_search?pretty" -H 'Content-Type: application/json' -d 
		{
		  "query": { "match_all": {} },
		  "sort": [
		    { "account_number": "asc" }
		  ]
		}

We have to use Java Rest Client

	Java Rest Client is the official ElasticSearch client that is used by Java to connect and communicate to ES. We would be using Java High-Level Rest Client for the same purpose. JHLC is used to expose API-specific method that enables transfer of objects between source and destination as request and response. In simpler words , we can use JHLC to connect to ElasticSearch server and then perform various operations on ElasticSearch Indexes like Insert , Update , Ingestion etc




Jackson library [Object Mapper]- Mainly concerned with converting Objects/ Entities to JSON and back.

ModelMapper/ MapStruct - Concerned with mapping One entity to another like, mapping an Entity to its DTO. This operation can get pretty gnarly owing to the size and complexity of different entities, so we need these libraries to make work easier.


Dependency :
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
        </dependency>

        RestHighLevelClient restHighLevelClient; is autowired in service class


        GET _cat/indices in Kibana DevTools to view all indices created


		    private final RestHighLevelClient restHighLevelClient;

		    private final String INDEX = "netmed_user";

INSERT:

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.convertValue(userEntity, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX).source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        In Kibana devTools

        GET /netmed_user/_search
			{
					  "query": { "match_all": {} }
			}

DELETE:
	
	1.	We can delete using id 	DELETE /<index>/_doc/<_id>

	2.  Or using Query Api

			POST /my-index-000001/_delete_by_query
			{
			  "query": {
			    "match": {
			      "user.id": "elkbee"
			    }
			  }
			}

			POST /netmed_user/_delete_by_query
			{
			  "query": {
			    "match": {
			      "userId": 50
			    }
			  }
			}

https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-document-delete-by-query.html

			DeleteByQueryRequest request = new DeleteByQueryRequest(INDEX);
            request.setConflicts("proceed");
            request.setQuery(new TermQueryBuilder("userId", userId));
            BulkByScrollResponse bulkResponse = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
            System.out.println("------>  Deleted Docs = "+bulkResponse.getDeleted());   //Count of How many records affected


UPDATE:

https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-document-update-by-query.html

		POST /netmed_user/_update_by_query
			{
			  "query": {
			    "match": {
			      "userId" : 49                 //Where condition
			    }
			  },
			  "script": {
			    "source": "ctx._source = params",
			    "params": {                         //Whole Entity needs to be updated
			          "userId" : 49,
			          "userName" : "Vasuki",
			          "password" : "Flipkart",
			          "role" : {
			            "roleId" : 2,
			            "roleName" : "Patient"
			          },
			          "createdBy" : "Doctor Nat",
			          "createdDate" : {
			            "year" : 2021,
			            "monthValue" : 3,
			            "dayOfMonth" : 12,
			            "hour" : 12,
			            "minute" : 22,
			            "second" : 40,
			            "nano" : 809435100,
			            "dayOfWeek" : "FRIDAY",
			            "dayOfYear" : 71,
			            "month" : "MARCH",
			            "chronology" : {
			              "id" : "ISO",
			              "calendarType" : "iso8601"
			            }
			          },
			          "lastModifiedBy" : "Doctor Hari",
			          "lastModifiedDate" : {
			            "year" : 2021,
			            "monthValue" : 3,
			            "dayOfMonth" : 12,
			            "hour" : 12,
			            "minute" : 22,
			            "second" : 40,
			            "nano" : 809435100,
			            "dayOfWeek" : "FRIDAY",
			            "dayOfYear" : 71,
			            "month" : "MARCH",
			            "chronology" : {
			              "id" : "ISO",
			              "calendarType" : "iso8601"
			            }
			          }
			    }
			  }
			}

https://stackoverflow.com/questions/50743317/elasticsearch-update-the-whole-source-field-based-on-search-query

		Map<String, Object> params = objectMapper.convertValue(oldUserEntity, Map.class);
        UpdateByQueryRequest request = new UpdateByQueryRequest(INDEX);
        request.setConflicts("proceed");
        request.setQuery(new TermQueryBuilder("userId", oldUserEntity.getUserId()));
        request.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source.putAll(params)", params));
        try {
            BulkByScrollResponse bulkResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
            System.out.println("Updated Response doc : "+bulkResponse.getUpdated());
        } catch (IOException e) {
            e.printStackTrace();
        }

Search EndPoint:

		
		 @Override
    public List<UserDto> doElasticSearch(String query) {
        List<UserDto> searchUserDtoList = new ArrayList<>();
        List<User> searchUserList = new ArrayList<>();

        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus status = searchResponse.status();
            TimeValue took = searchResponse.getTook();
            Boolean terminatedEarly = searchResponse.isTerminatedEarly();
            boolean timedOut = searchResponse.isTimedOut();

            SearchHits hits = searchResponse.getHits();
            TotalHits totalHits = hits.getTotalHits();
            System.out.println("totalHits --> " + totalHits);
            long numHits = totalHits.value;
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();

            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                String index = hit.getIndex();
                String id = hit.getId();
                float score = hit.getScore();

//                String sourceAsString = hit.getSourceAsString();
//                User userEntity = objectMapper.readValue(sourceAsString, User.class);
//                searchUserList.add(userEntity);

                Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                User userEntity = new User();
                userEntity.setUserName((String) sourceAsMap.get("userName"));
                userEntity.setPassword((String) sourceAsMap.get("password"));
                userEntity.setRole(new Role(2, "Patient"));
                userEntity.setCreatedBy((String) sourceAsMap.get("createdBy"));
                userEntity.setCreatedDate(LocalDateTime.now());
                userEntity.setLastModifiedDate(LocalDateTime.now());
                userEntity.setLastModifiedBy((String) sourceAsMap.get("lastModifiedBy"));
                searchUserList.add(userEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchUserList.stream().forEach(userEntity -> {
            searchUserDtoList.add(modelMapper.map(userEntity, UserDto.class));
        });
        return searchUserDtoList;
    }

------------------------------------------------------------------------------------

# ELK Stack
	
	Elastic Search : Search & Analyze
	Logstash       : Collect & Transform 
	Kibana         : Visualize & Manage

Kibana
	
		Kibana is an open source analytics and visualization platform designed to work with Elasticsearch. You use Kibana to search, view, and interact with data stored in Elasticsearch indices. You can easily perform advanced data analysis and visualize your data in a variety of charts, tables, and maps.

Note: In this example, Kibana is using the default configuration and expects to connect to a running Elasticsearch instance at http://localhost:9200

Kibana will a attach to a user defined network (useful for connecting to other services (e.g. Elasticsearch)). 

		which is elastic_network

	Run Kibana

		docker run -d --name kibana --net elastic_network -p 5601:5601 kibana:7.11.1

	Access in http://localhost:5601/

Access 3rd menu from the bottom of side menu - Devtools [like a Postman] where ES queries (curl) can be accessed
------------------------------------------------------------------------------------

# Logstash

We need a logstash.conf file which configures the logging

To have Logstash to ship logs to ElasticSearch then we need to have our application to store logs in a file.

1. Configure Spring Boot’s log file [Add below code in application.properties file]
	
			logging.file.name=application.log
 

Logstash file consists of three section: input, filter and output.

			input {
			  file {
			    type => "java"
			    # Logstash insists on absolute paths...
			    path => "/Users/hitenpratap/Downloads/todo/logs/application.log"
			    codec => multiline {
			      pattern => "^%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME}.*"
			      negate => "true"
			      what => "previous"
			    }
			  }
			}

			filter {
			  #If log line contains tab character followed by 'at' then we will tag that entry as stacktrace
			  if [message] =~ "\tat" {
			    grok {
			      match => ["message", "^(\tat)"]
			      add_tag => ["stacktrace"]
			    }
			  }

			  #Grokking Spring Boot's default log format
			  grok {
			    match => [ "message",
			               "(?<timestamp>%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME})  %{LOGLEVEL:level} %{NUMBER:pid} --- \[(?<thread>[A-Za-z0-9-]+)\] [A-Za-z0-9.]*\.(?<class>[A-Za-z0-9#_]+)\s*:\s+(?<logmessage>.*)",
			               "message",
			               "(?<timestamp>%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME})  %{LOGLEVEL:level} %{NUMBER:pid} --- .+? :\s+(?<logmessage>.*)"
			             ]
			  }

			  #Parsing out timestamps which are in timestamp field thanks to previous grok section
			  date {
			    match => [ "timestamp" , "yyyy-MM-dd HH:mm:ss.SSS" ]
			  }
			}

			output {
			    stdout {
			        codec => rubydebug
			    }
			    elasticsearch{
			        hosts=>["localhost:9200"]
			        index=>"todo-logstash-%{+YYYY.MM.dd}"
			    }
			}

https://www.elastic.co/guide/en/logstash/current/docker-config.html
https://medium.com/hprog99/spring-boot-logs-with-elasticsearch-logstash-and-kibana-elk-c61a378f3cb4


	Elastic search Network needs to be connected to

		1. Kibana
		2. Logstash

	docker run -d --name elasticsearch --net elastic_network -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.11.1

	docker run -d --name kibana --net elastic_network -p 5601:5601 kibana:7.11.1

	docker run --name logstash --net elastic_network -p 9600:9600 -p 5044:5044 -it -d -v ${PWD}:/config-dir logstash:7.11.1 -f /config-dir/logstash.conf

				>>Successfully started Logstash API endpoint {:port=>9600}

				When you access http://localhost:9600/, You will get JSon response

	docker network inspect -v network elastic_network

In Kibana,

		Go to Side menu - > Management -> Stack Management -> Index Pattern under Kibana

		Click Create Index Pattern 

		Input Index pattern name like "check-user-module-*"

		Time Field : "I dont want to use Time field" and click create index pattern

		After creating , go to Discover Under Analytics & select above created index

------------------------------------------------------------------------------------

# Junit Testing:

	No Dependency required (starter-test will be already there)

	We need to use Mockito to mock the beans used in source code

	To do that, 3 ways are there

		1. Using Mockito.mock(  .class)

				  private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

				   @BeforeEach
					  void setup() {
					    this.bookingService = new BookingService(bookingRepository, customerRepository, flightService);
					  }

		2. Using @MockBean & @SpringBootTest (Mandatory) and remaining using Autowired

					  @MockBean
					  private UserRepository userRepository;

					  @Autowired
					  private RegisterUseCase registerUseCase;


		3. Using @Mock & @ExtendWith(MockitoExtension.class) and remaining using Autowired


					  @Mock
					  private SaveUserPort saveUserPort;

					  @BeforeEach
					  void initUseCase() {
					    registerUseCase = new RegisterUseCase(saveUserPort, sendMailPort);
					  }

					  OR

						  @InjectMocks
						  private RegisterUseCase registerUseCase;

		https://github1s.com/thombergs/code-examples/blob/master/spring-boot/spring-boot-testing/src/test/java/io/reflectoring/testing/MockBeanTest.java

Constructor injection in Junit is hard, refer

	https://junit.org/junit5/docs/current/user-guide/#writing-tests-dependency-injection

------------------------------------------------------------------------------------
Useful:	
https://stackoverflow.com/questions/51467132/spring-webmvctest-with-enablejpa-annotation	
# Dont annotate @EnableJpaAuditing above spring boot main app	

------------------------------------------------------------------------------------


	mvn clean install -Dmaven.test.skip=true

	mvn clean package -Dmaven.test.skip=true
-----------------------------------------------------------------------------------------------------------------

Atlassian Clover:

In Pom.xml, Under <build> -- <plugins> :

			<plugin>
			    <groupId>org.openclover</groupId>
			    <artifactId>clover-maven-plugin</artifactId>
			    <version>4.2.0</version>
			</plugin>

mvn clean test - Runs Junit test cases alone   =>>>> BUILD SUCCESS
	
			mvn clean clover:setup test clover:aggregate clover:clover	
			mvn clean package -Dmaven.test.skip=true

This will instrument your sources, build your project, run your tests and create a Clover coverage report in the target/site/clover directory.

OPEN target - site - clover - index.html file in browser to view reports

Achieve 90% Code Coverage

-----------------------------------------------------------------------------------------------------------------


# Distributed tracing with jaeger

https://www.scalyr.com/blog/jaeger-tracing-tutorial/

The Jaeger tracing system is an open-source tracing system for microservices, and it supports the OpenTracing standard

		docker run -d --name jaeger -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411 jaegertracing/all-in-one:1.9


http://localhost:16686/search

Add Dependencies

	    <dependency>
            <groupId>io.opentracing.contrib</groupId>
            <artifactId>opentracing-spring-jaeger-cloud-starter</artifactId>
            <version>3.3.1</version>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign.opentracing</groupId>
            <artifactId>feign-opentracing</artifactId>
            <version>0.4.1</version>
        </dependency>

Add a Configuration class to return bean

		  @Bean
		    public JaegerTracer getTracer() {
		        io.jaegertracing.Configuration.SamplerConfiguration samplerConfig =  io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
		        io.jaegertracing.Configuration.ReporterConfiguration reporterConfig = io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
		        io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("jaeger netmed-user-module").withSampler(samplerConfig).withReporter(reporterConfig);
		        return config.getTracer();
		    }

------------------------------------------------------------------------------------
# HikariCP

Connection pooling is a technique used to improve performance in applications with dynamic database-driven content. Opening and closing database connections may not seem like a costly expense, but it can add up rather quickly. Let's assume it takes 5ms to establish a connection and 5ms to execute your query (completely made up numbers), 50% of the time is establishing the connection. Extend this to thousands or tens of thousands of requests and there is a lot of wasted network time. Connection pools are essentially a cache of open database connections. Once you open and use a database connection instead of closing it you add it back to the pool. When you go to fetch a new connection, if there is one available in the pool, it will use that connection instead of establishing another.


With Spring Boot we don’t need to do much to get HikariCP ready for our application. Spring has the autoconfigure module that does all the tasks for us. we only need to include spring-boot-starter-data-jpa or spring-boot-starter-jdbc dependency to the application.

Simply add these to application.properties

		spring.datasource.hikari.pool-name=ps-demo
		spring.datasource.hikari.maximum-pool-size=20
		spring.datasource.hikari.max-lifetime=1800000
		spring.datasource.hikari.idle-timeout=30000

		logging.level.com.zaxxer.hikari.HikariConfig=DEBUG 
		logging.level.com.zaxxer.hikari=DEBUG


------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------

AWS

EC2 - Elastic Compute Cloud

Running our own Cloud computer

Services ->  EC2 -> Launch Instances

Image 
	- Amazon Linux AMI 2018.03.0 (HVM) , SSD Volume type (Python Java Docker Mysql Php are in built) [Deprecated]
	- Hence selected Amazon Linux 2 AMI (HVM), SSD Volume Type - ami-0bcf5425cdc1d8a85 (64-bit x86) / ami-003025fed2eb22f59 (64-bit Arm)
	 [Amazon Linux 2 comes with five years support. It provides Linux kernel 4.14 tuned for optimal performance on Amazon EC2, systemd 219, GCC 7.3, Glibc 2.26, Binutils 2.29.1, and the latest software packages through extras. This AMI is the successor of the Amazon Linux AMI that is approaching end of life on December 31, 2020 and has been removed from this wizard.]

Instance Type
Configure Instance
Add Storage - 10GB
Add tags 
Configure Security Group
	- Add Rule
		Custom Tcp Rule 	TCP  8080  


		Then Connect via SSH Client - Termius

To install Java 11 

		sudo amazon-linux-extras install java-openjdk11

		java -version

		whoami

		sudo -i #to move to root directory
------------------------------------------------------------------------------------
S3 services

		Create Bucket
		created the spring app jar
		uploaded it to s3 and made public [Important]

		Copy s3 Object URL

		and get back to ssh - instance

		wget https://natarajbuck.s3.ap-south-1.amazonaws.com/spring-aws-poc.jar
		ls

		java -jar spring-aws-poc.jar

		Access http://ec2-13-126-90-129.ap-south-1.compute.amazonaws.com:8080/ in Browser [Dont use https as our spring boot expect Raw data Not encrypted data]

------------------------------------------------------------------------------------
Elastic BeanStalk:

		It internally follows above process - like creating Ec2 instance behind the scenes & places WAR file in s3 bucket and then point Tomcat in Ec2 instance ---> War file

		Here There's no need for Port Number [Advantage]


		Create a War file by providing   <packaging>war</packaging> below 	<version>0.0.1-SNAPSHOT</version> in pom.xml

			& run mvn clean package -Dmaven.test.skip = true [Building war: C:\Users\Windows\Downloads\poc\target\spring-aws-poc.war]

		Create a Web app in EBS [You can even place docker image here]

		Upload your code
		[Maximum Size 512 MB]

------------------------------------------------------------------------------------
Connecting with Amazon Dynamo DB

	Semi structured DB
	Partial MongoDB

	@DynamoDBDocument
	@DynamoDBAttribute

	@DynamoDBHashKey --- >>  Primary Key @Id

		& then We need DynamoDBMapper - which is config file
		Which holds Access key & secret key provided by AWS

		https%3A%2F%2Fgithub.com%2FJava-Techie-jt%2Fspringboot-dynamodb-example

------------------------------------------------------------------------------------
ECS & Fargate

	Create a Docker image using the command 

	mvn spring-boot:build-image


	A) Create New Task Definition - 

	1. select launch type compatibility

		Fargate - AWS managed infrastructure, no Ec2 instances needed
		Ec2 	- Self managed infrastructure using Ec2 instance

	2. Configure task and container definitions

		Task Definition Name*

		A task definition specifies which containers are included in your task and how they interact with each other. You can also specify data volumes for your containers to use.

		Task size  1GB & 0.5 vCPU

		The task size allows you to specify a fixed size for your task. Task size is required for tasks using the Fargate launch type and is optional for the EC2 launch type. Container level memory settings are optional when task size is set. Task size is not supported for Windows containers.

	Container Definitions

		You need to host your docker image in cloud

		>>>>> So using ECR

		Create a Reposistoy -> Private
		You need a Amazon CLI and Docker installed on your computer

			Download Access key from My Account -> My Security Credentials [Access keys (access key ID and secret access key)]

			AWS account ID: 387723181851

			Access Key ID: 			***
			Secret Access Key:			***

		For Amazon CLI,
		https://docs.aws.amazon.com/AmazonECR/latest/userguide/getting-started-cli.html
		https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html [Windows]
		https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html

		In Cmd,

		>> aws --version

		>> aws configure
		access key
		secret access key
		ap-south-1		=> default region name
		json            => default output format

	Now, copy registry uri from ECR
		387723181851.dkr.ecr.ap-south-1.amazonaws.com/spring-aws-poc

		In order to push from local machine, go to ECR via AWS CLI

		>> aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 387723181851.dkr.ecr.ap-south-1.amazonaws.com

				Login Succeeded --> console log

		Now we are in ECR, we need to push our docker image

		>> docker image ls

		REPOSITORY                 TAG            IMAGE ID       CREATED        SIZE
		spring-aws-poc.war         latest         27cf2a0a2eb4   19 hours ago   360MB

		>> docker tag spring-aws-poc.war:latest 387723181851.dkr.ecr.ap-south-1.amazonaws.com/spring-aws-poc

		>> docker push 387723181851.dkr.ecr.ap-south-1.amazonaws.com/spring-aws-poc

		Copy Image uri

		387723181851.dkr.ecr.ap-south-1.amazonaws.com/spring-aws-poc:latest

	Back to ECS

	Container name: spring-aws-poc
	Image : 387723181851.dkr.ecr.ap-south-1.amazonaws.com/spring-aws-poc:latest
	Memory limits: Hard limit 1024
	Port Mappings : 8080

	and click Add --> Create 
	Task definition status - 3 of 3 completed

   B) Create clusters

   An Amazon ECS cluster is a regional grouping of one or more container instances on which you can run task requests.

   Create Cluster

   	Select cluster template: Networking only (Powered by aws fargate)
   	configure cluster: specify cluster name : poc-cluster and create cluster

   	click on View Cluster

   	Go to Tasks --> Run New Task

   		Launch type: Fargate
   		Task Definition : (Will be added automatically)
   		Cluster Vpc: Add the first one(default)
   		Subnet: first one

   		Security Group: -> HAVE TO EXPOSE 8080 PORT in this group
   			Click on Edit security group, provide a Name: poc-all-access-security
   			Add Inbound Rules as 
   				All TCP 	 0 - 65535
   				All Traffic  0 - 65535

   		Now click on RUN TASK

   		and click on the task details get public IP and go for IP:8080 :)

   		http://13.233.40.255:8080/

------------------------------------------------------------------------------------
Lambda

	Created a in built Hello World Lambda in AWS
	Added an Trigger - Aws Gateway Rest Api

	https://rzrbmxstua.execute-api.ap-south-1.amazonaws.com/default/basicExample

	 > Hello from Lambda!

	AWS Lambda is an event-driven, serverless computing platform. 

	In AWS Lambda the code is executed based on the response of events in AWS services such as add/delete files in S3 bucket, HTTP request from Amazon API gateway, etc. However, Amazon Lambda can only be used to execute background tasks.

	AWS Lambda function helps you to focus on your core product and business logic instead of managing operating system (OS) access control, OS patching, right-sizing, provisioning, scaling, etc.

AWS Lambda VS AWS EC2

	AWS Lambda is a Platform as a Service (PaaS). It helps you to run and execute your backend code.	

			AWS EC2 Is an Infrastructure as a Service (laaS). It provides virtualized computing resources.

	It is restricted to fewlanguages.		
		
			No environment restrictions.

	You need to select your environment where you want to runthe code and push the code into AWS Lambda.		

			For the first time in EC2, you have to choose the OS and install all the software required and then push your code in EC2.

AWS Lambda VS AWS Elastic Beanstalk

	Deploy and manage the apps on AWS Cloud without worrying about the infrastructure which runs those applications.	

			AWS Lambda is used for running and executing your Back-end code. You can't use it to deploy an application.

	It gives you a Freedom to select AWS resources; For example, you can choose EC2 instance which is optimal according to your application.

			You can't select the AWS resources, like a type of EC2 instance, Lambda offers resources based on your workload.

	It is a stateful system.	

			It is a stateless system.

https://www.guru99.com/aws-lambda-function.html

Use Cases of AWS Lambda

	Extract Transform Load Process
	Use in Amazon products like Alexa Chatbots and Amazon Echo/Alexa
	Automated Backups of everyday tasks
	Allows you to filter and Transform data

While most of the PaaS offerings are designed to be running 24/7, Lambda is completely event-driven; it will only run when invoked.

https://www.contino.io/insights/aws-lambda-use-cases

At the recently held ServerlessConf, ‘A Cloud Guru’ gave proof of serverless promise saying they were never required to change their architecture due to performance reasons. They are running 287 Lambda functions, 19 microservices with 3.68 TB of data at the mere cost of $580 per month. Read that again!


https://www.simform.com/serverless-examples-aws-lambda-use-cases/

		Serverless Website Example with AWS Lambda
		Serverless Authentication Using AWS Cognito
		Multi-Location Media Transformation
		Mass Emailing using AWS Lambda & SES
		AWS Lambda Use Case for Real-time Data Transformation
		Serverless CRON Jobs
		AWS Lambda Use Case for Efficient Monitoring
		Real-time Notifications with AWS Lambda and SNS
		Building a Serverless Chatbot
		Serverless IoT Backend

------------------------------------------------------------------------------------
Jenkins

	docker run -p 8080:8080 -p 50000:50000 -v /jenkinsDirectory:/var/jenkins_home jenkins

To turn on Logs, add a log.properties in a folder 'data' with contents

	handlers=java.util.logging.ConsoleHandler
	jenkins.level=FINEST
	java.util.logging.ConsoleHandler.level=FINEST


	docker run -d --name jenkins -p 8080:8080 -p 50000:50000 --env JAVA_OPTS="-Djava.util.logging.config.file=/var/jenkins_home/log.properties" -v $pwd/data:/var/jenkins_home jenkins/jenkins


Jenkins is a self-contained, open source automation server which can be used to automate all sorts of tasks related to building, testing, and delivering or deploying software.

1. Access localhost:8080 for jenkins ui and input initial secret key

2. Install plugins

	Username: nataraj password: I21014
			

	[Lots of bugs found in Docker Jenkins & hence switched to PC version, it needs java 8 or 11 only Hence Jre 8 is installed]
https://stackoverflow.com/questions/12681308/failed-to-connect-to-repository-error-while-setting-up-github-jenkins-plugin

Go to Powershell,

>> Get-command git.exe
	

CommandType     Name                                               Version    Source
-----------     ----                                               -------    ------
				Application     git.exe                                            2.30.0.2   C:\Program Files\Git\cmd\git.exe	650755d4670940c9b32eaf4b474527d9 is the password

LTS of Jenkins is installed in local

	set path="c:\jre_8\bin" => jenkins runs on Java 8, 11 only
	>>java -jar jenkins.jar


https://github.com/Nataraj2609/jenkinshhh

Create a New Job
	Provide a name & choose Freestyle Project
	check Github checkbox and add url
	Under SCM, Choose Git radio button 
		& provide git url

			Initially "Failed to connect to repository : Error performing git command: git.exe ls-remote -h https://github.com/Nataraj2609/jenkinshhh HEAD" will be displayed

				https://stackoverflow.com/questions/12681308/failed-to-connect-to-repository-error-while-setting-up-github-jenkins-plugin

				Go to Powershell,

				>> Get-command git.exe

								
					CommandType     Name        Version    Source
					-----------     ----        -------    ------
					Application     git.exe     2.30.0.2   C:\Program Files\Git\cmd\git.exe

				You might need to set the path to your git executable in Manage Jenkins -> Configure System -> Git -> Git Installations -> Path to Git executable.	

				Global Configure Systems and add git home & mvn home (Uncheck Install automatically)	

				Save & Refresh

	Add Git Credentials of yours

	Under Build Triggers
		Check Poll SCM
			Schedule : * * * * *
	Under Build
		Add Invoke Top level maven targets
			choose mvn
				and give goals AS "install"
	Under Post Build Actions
		Choose Emil notification
			Provide ur Email
				Click Save


	Now make a change in code & commit, push / Job will be triggered automatically

In order to email to Jenkin
	Go to Jenkins -> Manage Jenkins -> COnfigure System -> scroll to last
	E-mail notification

		SMTP server    : smtp.gmail.com
		Default suffix : @gmail.com
		Check Use SSL Checkbox
		Check SMTP Authentication
			UserName  : riderdon26@gmail.com
			Password  : ****
			SMTP Port : 465
			Reply.To address: nataraj.manivannan@ideas2it.com
			Charset   : UTF-8

			Or

			587 with TLS

	https://support.google.com/mail/answer/7126229?p=BadCredentials&visit_id=637532975329331045-658335943&rd=2#cantsignin

	Jenkin pipeline:

	Workflow - Jobs that are chained & integrated with each other in sequence

	Pipeline needs to be enabled for the first time

	Jenkin -> Manage Jenkin -> Manage Plugin -> Available
	search for Build Pipeline and add it (innstall without restart)

	Example : Dev (Job 1) -> Stage(Job 2) -> Prod

	Add new pipeline
		Select Initial Job: Job 1
	Now go to dashboard to view Stage Job and click Configure -> Build Triggers section -> Check Build after other projects are built
		Give Project to watch -> Dev
		likewise chain all

		and go to Dashboard to see pipeline

Docker Build image automatically

	Install Plugin for it
	Go to Jenkin -> Manage Jenkin -> Manage Plugins ->
	1. CloudBees Docker Build and Publish plugin
	2. Docker Plugin
	3. docker build step

	These 3 are needed

	Go to dashboard -> click on Project Job -> configure
	-> Post Build Trigger

	Build/publish Docker Image
	Docker build and Publish (Choose this one)

	Add a DockerFile in git repo [Provide DockerHub / AWS ECR registry]

		Repository name: 
		tag :
		Docker host URI:
		Server Credentials: 
		Docker Registry URL:
		Registry Credentials: 

------------------------------------------------------------------------------------

Angular FrontEnd:

	ng new user-module-frontend

	cd user-module-frontend

	ng serve -o




Cheat Sheet:

	--dry-run:

		ng new example --dry-run

		This option will stop the CLI from making any changes to the file system. Instead it will print everything it would have done to the console. That way you can check if the command actually does what you thought it does, before it causes any harm.

	--skip-tests:

		ng new example --skip-tests

		Seriously, there are legit scenarios where you just don't need them.

	ng generate:

		ng generate component [name]

		You need a new component, module, service or any other angular construct.

	ng build:

		When you are happy with your angular app, it is time to deploy it to a web-server.

		This command will cause the CLI to build your application and places the output in a directory called "dist" (by default).

		This means, that certain optimizations where not performed and the app is still using the development environment variables. To change that, use the --prod flag like this:

		ng build --prod

<!--- ![Image of Yaktocat](https://malcoded.com/static/68c150aaaee9e8056f44fb81a08799ad/d9b4a/angular-cli-cheat-sheet.webp)--->


Components:
	
	list
	detail
	menubar
	footer

Get [view]
	localhost:9000/netmed-user-api/v1/users/5

	{
    "userName": "Thilak Raj S",
    "password": "PassWORDNULL",
    "roleName": "Doctor",
    "createdBy": "Doctor Hari",
    "createdDate": "2021-01-19T03:14:07",
    "lastModifiedBy": "Doctor Hari",
    "lastModifiedDate": "2021-03-02T19:18:03"
    }

1. Created a model/entity/ds -> User.ts class

		export class User{
	    userName!: String;
	    password!: String;
	    roleName!: String;
	    createdBy?:String;
	    createdDate?:String;
	    lastModifiedBy?:String;
	    lastModifiedDate?: String;
		}

2. Created a Component -> user-record-detail (html, css, ts, spec.ts)

		ng g component user-record-detail

3. Created a service -> user-record-service

		ng g service service/user-record-service (ts, spec.ts)

4. Update Routes in app-routing-module.ts

		Register Routes as

			const routes: Routes = [
			  {path: 'view-user', component: UserRecordDetailComponent},
			  {path: 'list-users', component: UserRecordListPageComponent},
			];

5. This is the important One ==> app.module.ts

		Every application has at least one Angular module,the root module that you bootstrap to launch the application(AppModule)

		Modules are a way of organizing and separating your code. You can have multiple modules and lazy load some modules.
		https://stackoverflow.com/questions/45942332/what-does-the-app-module-ts-file-serve-for-what-should-i-do-inside-of-it

		Two components added by ng g command will be added automatically by angular under declarations.

		Now add dependencies used in our Projects like

			imports: [
		    BrowserModule,
		    AppRoutingModule,
		    HttpClientModule,
		    ReactiveFormsModule
		  ],

After service is done, go for component

Detail-page-component.html ==> added table to display list
	
	<router-outlet></router-outlet> needs to added at end of each Component Page as this is used to set routes in a SPA.
	Acts as a placeholder that Angular dynamically fills based on the current router state.

	https://stackoverflow.com/questions/50854312/angular-6-with-2-router-outlet

Detail-page-component.ts   ==> 

		 users!: User[];

		  constructor(private router: Router, private userService: UserRecordServiceService) { }

		  ngOnInit(): void {
		    this.router.events.subscribe(value => {
		      this.getUsers();
		    });
		  }

		  getUsers() {
		    this.userService.getUserRecord().subscribe(data => {
		      this.users = data;
		    });
		  }

Important By default CORS is activated. localhost:4200 cannot invoke server running on localhost:9000
To achieve this, Cors needs to be applied 

You can enable cross-origin resource sharing (CORS) from either in individual controllers or globally.

	1. @CrossOrigin(origins = "http://localhost:8080")
		@GetMapping("/greeting")
		public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
			System.out.println("==== get greeting ====");
			return new Greeting(counter.incrementAndGet(), String.format(template, name));
OR
		@RestController
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping("/netmed-user-api/v1/users")
		@RequiredArgsConstructor
		public class UserController {

	2. GlobalConfiguration like adding a Filter

https://www.split.io/blog/crud-app-spring-boot-angular/
https://bezkoder.com/angular-10-spring-boot-crud/

https://www.smashingmagazine.com/2021/03/svg-generators/


https://mdbootstrap.com/docs/angular/components/demo/

https://mdbootstrap.com/education/angular/getting-started-3-export/ ==> Useful angular tutorial


	npm install bootstrap --save
	npm install jquery --save

	Then Open angular.json and add like below

	 "styles": [
	              "./node_modules/bootstrap/dist/css/bootstrap.css", 
	              "src/styles.css"
	            ],

	 "scripts": [
	              "./node_modules/jquery/dist/jquery.js",              
	              "./node_modules/bootstrap/dist/js/bootstrap.js"
	            ]

$0.57 -- April 7 2021 AWS Bill  42.27 INR	

------------------------------------------------------------------------------------
Git Workflow:

	https://www.youtube.com/watch?v=3a2x1iJFJWc&ab_channel=Udacity

	Working Dir ---> Stage/Index ---> HEAD[local repo] ---> Remote Repo

------------------------------------------------------------------------------------

Multi Tenant Configuration

https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a

	1. Database per Tenant
	2. Shared Database, Shared Schema
	3. Shared Database, Separate Schema

https://www.youtube.com/watch?v=iDogrHEo4x0&ab_channel=JavaTechie

https://tech.asimio.net/2017/01/17/Multitenant-applications-using-Spring-Boot-JPA-Hibernate-and-Postgres.html
https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/


https://dev.to/aws-builders/how-to-pass-the-aws-cloud-practitioner-exam-complete-guide-g18
https://raygun.com/blog/git-workflow/
https://www.atlassian.com/git/tutorials/comparing-workflows#:~:text=A%20Git%20Workflow%20is%20a,in%20how%20users%20manage%20changes.

----------------------------------------------------------------------------------------------------------------------------------------------------------

https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html
https://www.educative.io/courses/grokking-the-system-design-interview?affiliate_id=4793322061168640



https://lethain.com/introduction-to-architecting-systems-for-scale/
https://www.tothenew.com/blog/caching-what-why-and-how-with-hazelcast/
https://hazelcast.org/compare-with-redis/#:~:text=The%20biggest%20difference%20between%20Hazelcast,this%20introduces%20extra%20network%20hops.
https://www.javadoc.io/doc/org.mockito/mockito-core/2.2.9/org/mockito/Answers.html#RETURNS_DEEP_STUBS


https://www.youtube.com/watch?v=iTMClgY1heA&ab_channel=SkillFillip

Cloud config servers

https://developer.okta.com/blog/2020/05/04/spring-vault

https://blog.marcosbarbero.com/integrating-vault-spring-cloud-config/



user module   - 9000
patient module- 9001
vital module  - 9002

config server - 8888
config git url- https://github.com/Nataraj2609/i2i_netmed_config_files

Cloud config working check uri

http://localhost:8888/user-module/dev
http://localhost:8888/user-module/default


		BIG MISTAKE:
		Number 1:

		<dependency>
		      <groupId>org.springframework.cloud</groupId>
		      <artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

		is not added in user module and hence bootstrap.yml file is not located by spring app

		Number 2:

		org.springframework.boot.context.config.ConfigDataResourceNotFoundException: Config data resource 'file [configserver:http:\localhost:8888]'	 via location 'configserver:http://localhost:8888/' cannot be found

		Did not add above dependency again in CLIENT


http://localhost:8888/user-module/dev

consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=192.168.1.100

http://localhost:8500/ui/dc1/services


		Big Mistake 3

		While get request, use         UserDto user = modelMapper.map(userEntity.get(), UserDto.class);

		because findById provides Optional record, model Mapper will return null in this case

		Big Mistake 4

		Redis Cache Not Worked due to - UserDto not Serialized

		Spring supports caching Optional. The issue is your Redis serializer (JdkSerializationRedisSerializer probably). It uses Java based serialization which requires the classes to be Serializable. You can solve this by configuring the RedisCacheManager to use another serializer that doesn't have this limitation. For example you can use Kryo (com.esotericsoftware:kryo:3.0.3):

		@Cacheable ---> Check the imports - Swagger cacheable is imported wrongly

Redis server: 6379
Vault Server: 8200

_________________________________________________________________________
Docker Bridge Network - Port Forwarding - Container to Expose

Consul 8500:8500
	   8600:8600/udp

Redis      :6379
(Just Run For Redis it will pick)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
PHASE 2:

SpringBoot

Java 8
JUNIT Test Cases
Jenkins/Docker


Cache (Clients considering using Hazelcast)
Artifactory



==> Junit :

	Stubbing means replacing a method, function or an entire object with a version that produces hard-coded responses. This is typically used to isolate components from each other, and your code from the outside world. For example, stubbing is often used to decouple tests from storage systems and to hard-code the result of HTTP requests to test code that relies on data from the internet.

	Mocking is a form of testing that involves verifying behaviour by checking which methods are called during a test. Like stubbing, it involves replacing methods with fake versions, but it also means setting expectations that those methods must be called. This is used to specify contracts between layers of an application, and to test side-effects.

Mocks and stubs are fake Java classes that replace these external dependencies. These fake classes are then instructed before the test starts to behave as you expect. 

A stub is a fake class that comes with preprogrammed return values. It’s injected into the class under test to give you absolute control over what’s being tested as input. A typical stub is a database connection that allows you to mimic any scenario without having a real database.

A mock is a fake class that can be examined after the test is finished for its interactions with the class under test. For example, you can ask it whether a method was called or how many times it was called. Typical mocks are classes with side effects that need to be examined, e.g. a class that sends emails or sends data to another external service.


Notice that, even though stubbing and mocking are two different things, Mockito uses “mocks” for everything


stubbed/Mocked in junit mockito - solution as below
	
	 /* The UserRepository*/
    @MockBean(answer= Answers.RETURNS_DEFAULTS)
    private UserRepository userRepository;


    CALLS_REAL_METHODS
		An answer that calls the real methods (used for partial mocks).
	RETURNS_DEEP_STUBS
		An answer that returns deep stubs (not mocks).
	RETURNS_DEFAULTS
		The default configured answer of every mock.
	RETURNS_MOCKS
		An answer that returns mocks (not stubs).
	RETURNS_SELF
		An answer that tries to return itself.
	RETURNS_SMART_NULLS
		An answer that returns smart-nulls.

		https://www.javadoc.io/doc/org.mockito/mockito-core/2.2.9/org/mockito/Answers.html#RETURNS_DEEP_STUBS


Null Pointer Exception:

	If you attempt to dereference num before creating the object you get a NullPointerException.

	If the method is intended to do something to the passed-in object as the above method does, it is appropriate to throw the NullPointerException because it's a programmer error and the programmer will need that information for debugging purposes.

	https://stackoverflow.com/questions/218384/what-is-a-nullpointerexception-and-how-do-i-fix-it

		Objects.requireNonNull(userDto, "UserDto should not be null");

	You can check for Null like this and throw a message

How to avoid NPE:

	1. Only Non Primitive data types produces NPE (String, arrays, Classes/Objects) 
		Call equals() and equalsIgnoreCase() method on known String literal rather unknown object
					Object unknownObject = null;

					//wrong way - may cause NullPointerException
					if(unknownObject.equals("knownObject")){
					   System.err.println("This may result in NullPointerException if unknownObject is null");
					}

					//right way - avoid NullPointerException even if unknownObject is null
					if("knownObject".equals(unknownObject)){
					    System.err.println("better coding avoided NullPointerException");
					}
	2. Prefer valueOf() over toString() where both return same result

	3. Using null safe methods and libraries
			StringUtils.isEmpty();

	4) Avoid returning null from a method, instead, return an empty collection or an empty array.

			    public static List<String> getMe(String str) {
			        List<String> stringList = new ArrayList<>();
			        stringList = null;
			        return stringList == null ? Collections.EMPTY_LIST : stringList;
			    }

			    OR

			    public static List<String> getMe(String str) {
			        List<String> stringList = Collections.EMPTY_LIST;
			        return stringList;
			    }

	5. Use of annotation @NotNull and @Nullable

	6. Avoid unnecessary autoboxing and unboxing in your code

	7. If you are using database for storing your domain object such as Customers, Orders etc than you should define your null-ability constraints on database itself. Since database can acquire data from multiple sources, having null-ability check in DB will ensure data integrity.

	8. Use Null Object Pattern
		The Null object is a special object, which has different meaning in different context, for example, here an empty Iterator, calling hasNext() on which returns false, can be a null object. 

Check Array Null Using Java 8

	If you are working with Java 8 or higher version then you can use the stream() method of Arrays class to call the allMatch() method to check whether array contains null values or not.

				 	public class SimpleTesting {

						String[] arr = new String[10];

						public static void main(String[] args) {
							SimpleTesting obj = new SimpleTesting();
							Boolean containNull = Arrays.stream(obj.arr).allMatch(Objects::nonNull);
							if(!containNull) {
								System.out.println("Array is null");
							}
						}
					}


Caching:

https://lethain.com/introduction-to-architecting-systems-for-scale/

Application caching requires explicit integration in the application code itself. Usually it will check if a value is in the cache; if not, retrieve the value from the database; then write that value into the cache (this value is especially common if you are using a cache which observes the least recently used caching algorithm). The code typically looks like (specifically this is a read-through cache, as it reads the value from the database into the cache if it is missing from the cache)

Cache invalidation:

While caching is fantastic, it does require you to maintain consistency between your caches and the source of truth (i.e. your database), at risk of truly bizarre applicaiton behavior.

Solving this problem is known as cache invalidation.

If you’re dealing with a single datacenter, it tends to be a straightforward problem, but it’s easy to introduce errors if you have multiple codepaths writing to your database and cache (which is almost always going to happen if you don’t go into writing the application with a caching strategy already in mind). At a high level, the solution is: each time a value changes, write the new value into the cache (this is called a write-through cache) or simply delete the current value from the cache and allow a read-through cache to populate it later (choosing between read and write through caches depends on your application’s details, but generally I prefer write-through caches as they reduce likelihood of a stampede on your backend database).


