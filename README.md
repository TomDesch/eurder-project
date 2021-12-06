# Örder-project by Tom Descheemaeker

## Timing
**Test-first** way with hopefully a proper design and architecture.

## Must-Have stories:

:x: Story 0 (your project setup + other technical requirements)  
:x: Story 1  
:x: Story 2  
:x: Story 3  
:x: Story 7  
:x: Story 8  

## Nice-To-Have stories:

:x: Story 4  
:x: Story 5  
:x: Story 6  
:x: Story 9  
:x: Story 10  

## Technical requirements for JAVA  
:heavy_check_mark: Create a new GitHub repository  
:heavy_check_mark: Create a REST(ful) Web API (with JSON as the message / payload format)  
:heavy_check_mark: Use Spring Boot (and Spring MVC, Spring Security,...)  
:heavy_check_mark: Use Maven as the Build & Dependency Management project tool.  
:x: Make it a multi-module project.  
:x: Perform logging (use spring-boot-starter's logging dependencies: logback and slf4j)  
:x: Certainly log all interactions with the application that can be defined as "errors"  
:x: E.g.: unauthorized access, illegal arguments, exceptions in general,...  
:x: Provide, through OpenAPI and Swagger(UI) an online manual / documentation for your Web API.  
:x: If you have already seen JPA: use JPA (Hibernate or EclipseLink) in combination with a PostgreSQL or Oracle Database to store and access the data.  
:x: Setup a proper test configuration, which runs the integration tests against an in-memory database (e.g. H2)  
:x: Make it a separate technical story.  
:x: Correctly setup and handle the transactions.  
:x: Write your DDL (create tables,...) in a separate .sql file, which you also put under version control.  
:x: Use Travis or Jenkins to set up a Continuous Integration (CI) pipeline.  
:x: Additionally, but optional, deploy to Heroku!  
:x: Think about Security: authentication and authorization. It is not a priority, but if you have the time, implement it properly.  
:x: Until then, you can neglect the fact that certain endpoints should only be usable by - for example - an administrator.  
