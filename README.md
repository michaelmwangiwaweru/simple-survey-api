# Simple Survey API

A RESTful Survey Management API built with Spring Boot, MySQL, and JWT Authentication.

This application allows administrators to create and manage surveys while authenticated users can participate in surveys and submit responses.

---

## Features

- User Registration
- User Login (JWT Authentication)
- Role-Based Authorization (Admin & User)
- Survey Management
- Question Management
- Survey Responses
- Dashboard Statistics
- File Upload support
- RESTful API
- MySQL Database

---

## Technologies Used

- Java 21+
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman
- Git & GitHub

---

## Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── repository
 ├── security
 ├── service
 └── resources
```

---

## Prerequisites

Before running the project, ensure you have installed:

- Java JDK 21 or later
- Maven
- MySQL Server
- Git
- Postman (optional)

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/michaelmwangiwaweru/simple-survey-api.git
```

### 2. Navigate into the project

```bash
cd simple-survey-api
```

### 3. Create the database

```sql
CREATE DATABASE sky_survey_db;
```

or import the provided SQL file

```
sky_survey_db.sql
```

### 4. Configure the database

Edit

```
src/main/resources/application.properties
```

Update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sky_survey_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

---

## Running Locally

Using Maven

```bash
mvn spring-boot:run
```

or

```bash
./mvnw spring-boot:run
```

The API will be available at

```
http://localhost:8080
```

---

## Authentication

Authenticate using

```
POST /api/auth/login
```

Use the returned JWT token in every protected request.

Example

```
Authorization: Bearer YOUR_TOKEN
```

---

## API Documentation

The repository includes:

- REST API Source Code
- ERD Diagram
- Database SQL Script
- Postman Collection

---

## Included Files

```
ERD.png

sky_survey_db.sql

Simple Survey API.postman_collection.json
```

---

## Assumptions

- Only authenticated users can access protected endpoints.
- Only administrators can create, update and delete surveys.
- Users can only submit responses to available surveys.
- A survey may contain different question types such as:
  - Text
  - Text Area
  - Email
  - Number
  - Date
  - Radio Button
  - Checkbox
  - Dropdown
  - File Upload
- MySQL is running locally.
- Uploaded files are stored locally.

---

## API Testing

The project includes a Postman collection for testing all endpoints.

Import

```
Simple Survey API.postman_collection.json
```

into Postman.

---

## Author

Michael Mwangi

GitHub

https://github.com/michaelmwangiwaweru
