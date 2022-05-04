# WCAssignmentFinal

Overview

    ShadowREM is a real estate management tool for property managers and their tenants. 


Technologies Used

    Spring Boot + Thymeleaf
    JavaScript 
    Spring Data + MySQL

Features

    Users can...
    (MVP) Login
    (MVP) View/Update account info

    Tenants can...

    Managers can...
    (MVP) View/Update all tenants
    (MVP) Create units
    (MVP) View/Update all units 

To-do list:

    Users can...
    Access payment portal

    Tenants can...
    View unit details
    Request maintenance tickets

    Managers can...
    Assign units to tenants
    View all maintenance tickets
    Mark tickets started/closed

Getting Started

    The application can be run as a Java app on your local machine. 
    The database can be set up in MySQL workbench using the included .sql file.
    The application.properties file will need to be updated with your local database credentials. 
    The web app can be accessed at the url: localhost:8080/

Usage

    As of this release, a tenant account can be created from the landing page by clicking on "create account".
    To access the tenant features select "tenant" from the account type drop down and log in with above created credentials. 
    Intentionally, a manager account cannot be created in the same way. 
    Instead, an admin account has been created to access manager features. 
    To access the manager features, select "manager" from the account type drop down and log in with the credentials: (username) "admin", (password) "pass".

    Thank you! 
