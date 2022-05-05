# Getting Started

### todo
- security -> apikey, oauth2, openid, amazon cognito
- cloud -> eureka/zookeeper/consul, discovery/registry-server, config-server, 
           gateway(load balancing and rate limiting), ribbon, openfeign, aws xray/zipkin
- testing - unit testing, service testing, integration testing
- data jpa -> redis, influxdb, dynamodb
- cache -> second level caching
- message/events -> rabbitmq, kafka, sse
- elasticsearch, kibana, logstash
- apm -> datadog/dynatrace/sentry/newrelic

### about
Simple configurations for most common usecases with sprint boot.
Check each branches/tags for different usecases.

### metrics and monitoring
check a basic monitoring in here -> https://github.com/tareqmy/springbootmonitoring

### jmx instruction for remote connection
- the application must run with the following VM options
  - -Dcom.sun.management.jmxremote.port=<jmxPort> 
  - -Dcom.sun.management.jmxremote.ssl=false 
  - -Dcom.sun.management.jmxremote.authenticate=false
- from the jconsole use <hostIP>:<jmxPort>
- check here for authentication options -> https://docs.oracle.com/en/java/javase/11/management/monitoring-and-management-using-jmx-technology.htm
- dont know how stop it from connecting locally run spring bot applications!

### sonarQube instructions
- running sonarqube locally through docker
- installed sonarlint plugin in idea
- logged into sonarqube http://localhost:9000/ with default login admin/admin
- created a project key and token
- copied the command in sonarqube.sh and run the command from the terminal
- the report is found in the ui for the project

### dockerization instructions
- https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html#container-images.dockerfiles

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

