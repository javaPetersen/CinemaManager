# CinemaManager


CinemaManager was created as my final project for the Coderslab course. This application is intended to be used to manage the cinema.

At this moment app has the following functionalities:
For Administrator:
* Adding, editing and deleting movies
* Adding, editing, showing details and deleting cinema halls
* Adding, editing and deleting ticket types
* Adding, editing and deleting screening
* Creating and deleting reservations 

For users:
* registration
  
(in near future I'm planning to add landing page and user panel, that will allow user to create reservation for selected screening for themselves)

Technologies and tools used:
* Java 11
* SpringBoot
* Spring Security
* Hibernate
* MySQL
* JSP & JSTL
* IntellijIDEA
* Git
* Maven
* Bootstrap


#How to use it?
At first, you have to create database on your local mysql, then configure your database connection by adding environment variables:
* DB_NAME (name of your database)
* DB_USER (your mysql username)
* DB_PASS (your mysql password)


Then you can run application in your IntelliJ. If everything is set correctly app should run without any errors. 
Next step is creating your admin user, you have to do it manually (remember to encrypt your password, you can use https://bcrypt-generator.com/ -
simply enter your password as String, click encrypt button and copy your hashed password. 
Also remember to set true in active column and set role to ROLE_ADMIN).
When you finish it, you could now log in as administrator.
Go to http://localhost:8080/login  enter your email and password, and here we go! :)




Author: Piotr Perliński



