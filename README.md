# Örder-project by Tom Descheemaeker

==> Trello Kanban board: https://trello.com/b/Kvo1GfI5/kanban-order-project 

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
- Make it a multi-module project.  
:x: Perform logging (use spring-boot-starter's logging dependencies: logback and slf4j)  
- Certainly log all interactions with the application that can be defined as "errors"  
-- E.g.: unauthorized access, illegal arguments, exceptions in general,...  
:x: Provide, through OpenAPI and Swagger(UI) an online manual / documentation for your Web API.  
:x: If you have already seen JPA: use JPA (Hibernate or EclipseLink) in combination with a PostgreSQL or Oracle Database to store and access the data.  
-  Setup a proper test configuration, which runs the integration tests against an in-memory database (e.g. H2)  
-- Make it a separate technical story.  
- Correctly setup and handle the transactions.  
- Write your DDL (create tables,...) in a separate .sql file, which you also put under version control.  
:x: Use Travis or Jenkins to set up a Continuous Integration (CI) pipeline.  
- Additionally, but optional, deploy to Heroku!  
:x: Think about Security: authentication and authorization. It is not a priority, but if you have the time, implement it properly.  
- Until then, you can neglect the fact that certain endpoints should only be usable by - for example - an administrator.  
