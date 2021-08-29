# Getting Started

### Api test
* $ curl -X GET -i http://localhost:8080/employee/all

* $ sudo apt install jq
* $ curl http://localhost:8080/employee/all -s | jq .

* $ curl -d "@add_employee.json" -H "Content-Type: application/json" -X POST http://localhost:8080/employee/add

* $ curl -d "@update_employee.json" -H "Content-Type: application/json" -X PUT http://localhost:8080/employee/update

* $ curl -X DELETE -H "Accept: application/json" http://localhost:8080/employee/delete/4

### Angular
* $ npm install -g @angular/cli
* $ ng new my-first-project
* $ cd my-first-project
* $ ng serve

* $ ng new employeemanagerapp

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

