# 🚀 RESTful To-Do List API

This project is a robust **To-Do List RESTful API** developed using **Java** and the **Spring Boot** framework. It provides a set of endpoints for managing tasks, with data persisted in a **MySQL** database.

## ✨ Features

* **CRUD Operations:** Full capabilities to Create, Read, Update, and Delete tasks.
* **RESTful Design:** Clean and standard REST architecture using HTTP methods.
* **Data Persistence:** Uses **Spring Data JPA** to manage data in a **MySQL** database.
* **Minimalist:** API-only solution (no frontend/GUI).

## 🛠️ Tech Stack

* **Language:** Java 25
* **Framework:** Spring Boot 3+
* **Database:** MySQL
* **ORM/Data:** Spring Data JPA / Hibernate
* **Build Tool:** Maven

## 📌 Prerequisites

Before running the application, ensure you have the following installed:

1.  **Java Development Kit (JDK):** Version 17 or higher.
2.  **MySQL Server:** Running instance of MySQL.
3.  **Maven** 

## ⚙️ Configuration

### 1. Database Setup

You need to create a database schema (e.g., `todolist`) and update the credentials in the `src/main/resources/application.properties` file:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/todolist
spring.datasource.username=root
spring.datasource.password=

# JPA/Hibernate Settings (Optional - for development)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
