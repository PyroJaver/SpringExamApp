## Description

This application can:
1) Generate tests from the questions contained in the database
2) Check the correctness of the answers and display the test result
3) Perform CRUD operations on users and questions

Stack:

1) **Spring Boot**, **Spring MVC** as the basis of the application
2) **Spring Security** for authorization and authentication
3) **Hibernate**, **PostgreSQL** for database performing  
4) For views I have used **Thymeleaf** templates 
5) For testing I have used **JUnit** and **Mockito**


## Data Base Configuration
During the development process, I used PostgreSQL. You should change the configuration in hibernate.properties to the
values corresponding to your database, and execute the commands located in **/resources/sql**