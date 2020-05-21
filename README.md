# Internet Shop

![Header Image](/src/main/resources/banner-1.png)

# Table of Contents

[Project purpose](#purpose)

[Project structure](#structure)

[For developer](#developer)

[Author](#author)

## <a name='purpose'></a>Project purpose

This project is a simple version of internet shop.

<hr>

This shop has basic functions for online store such as:

- Registration and log in forms
- Shopping cart and order services
- Two roles: User and Admin

<hr>

This project has authentication and authorization filters, DAO and Service layers, Servlets and JSP pages.

DAO layer based on MySQL DB.

## <a name='structure'></a>Project structure

- Java
- Maven
- MavenCheckstylePlugin
- javax.servlet
- javax.jstl
- mysql-connector-java
- log4j

## <a name='developer'></a>For developer
To run this project you need to install:

- <a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html">Java 11</a>
- <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>
- <a href="https://www.mysql.com/downloads/">MySQL 8</a> (optional)

<hr>

Add this project to your IDE as Maven project.

Add Java SDK 11 in project structure.

Configure Tomcat:
- Add artifact
- Add Java SDK 11

Change a path to your Log file in **src/main/resources/log4j.properties** on line 12.

<hr>

To work with MySQL you need to:
- Use file **src/main/resources/init_db.sql** to create schema and all the tables required by this app in MySQL DB
- Change username and password to match with MySQL in **src/main/java/mate/academy/internetshop/util/ConnectionUtil.java** class on 19 line

<hr>

Run the project:

Main page is at URL: .../{context_path}/index

## <a name='author'></a>Author
[Maksym Matuliak](https://github.com/MaksymMatuliak)

