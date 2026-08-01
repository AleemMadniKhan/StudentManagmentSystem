# 🎓 Student Management System

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=springsecurity)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon-336791?logo=postgresql)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?logo=hibernate)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger)
![License](https://img.shields.io/badge/License-MIT-blue)

A secure **Student Management System** backend built using **Spring Boot**, **Spring Security (JWT)**, **PostgreSQL**, and **Spring Data JPA**. The project follows a clean layered architecture and exposes RESTful APIs for managing students, teachers, courses, enrollments, marks, semesters, and role-based academic operations.

This project was developed to demonstrate real-world backend development practices including authentication, authorization, database normalization, REST API design, validation, exception handling, and layered architecture.

---

# 📖 Overview

The Student Management System simulates a university management platform that manages the complete student academic lifecycle—from authentication and registration to course enrollment, assessment management, and GPA calculation.

The application supports three user roles—**Admin**, **Teacher**, and **Student**—each with dedicated APIs and permissions to securely perform role-specific operations.

---

# ✨ Features

## 🔐 Authentication & Security

- JWT Authentication
- Refresh Token Authentication
- Spring Security
- BCrypt Password Encryption
- Stateless Authentication
- Role-Based Authorization
- Permission-Based Authorization
- Request Validation
- Global Exception Handling
- Secure REST APIs
- DTO Pattern
- Entity-DTO Mapping

---

## 👨‍🎓 Student APIs

- Student Registration
- Student Profile Management
- View Registered Courses
- View Assessment Marks
- View Semester Information
- Automatic GPA Calculation

---

## 👨‍🏫 Teacher APIs

- Teacher Registration
- Teacher Profile Management
- View Assigned Courses
- View Assigned Sections
- View Enrolled Students
- Manage Student Marks

---

## 👨‍💼 Admin APIs

- Manage Students
- Manage Teachers
- Manage Courses
- Manage Semesters
- Manage Sections
- Manage Course Offerings
- Manage Enrollments
- Manage Marks
- View System Statistics

---

## 📚 Academic Management

- Course Management
- Semester Management
- Section Management
- Course Offering Management
- Student Enrollment
- Assessment Marks
- GPA Calculation

---

# 💻 Tech Stack

| Layer                 | Technologies               |
| --------------------- | -------------------------- |
| **Language**          | Java 21                    |
| **Framework**         | Spring Boot                |
| **Security**          | Spring Security, JWT       |
| **ORM**               | Spring Data JPA, Hibernate |
| **Database**          | PostgreSQL (Neon Database) |
| **Build Tool**        | Maven                      |
| **API Documentation** | Swagger / OpenAPI          |
| **Testing**           | Postman, Swagger UI        |

---

# 🏗️ Architecture

```text
            React Frontend / API Client
                      │
               HTTP / REST API
                      │
                      ▼
        Spring Security (JWT Filter)
                      │
                      ▼
             REST Controllers
                      │
                      ▼
              Service Layer
                      │
                      ▼
            Repository Layer
                      │
                      ▼
         PostgreSQL (Neon Database)
```

The project follows a layered architecture that separates presentation, business logic, and data access, making the application easier to maintain and scale.

---

# 📂 Project Structure

```text
src
├── main
│
├── java
│   └── com.StudentManagementSystem.SMS
│       ├── configuration
│       ├── controller
│       ├── dto
│       │   ├── request
│       │   └── response
│       ├── entity
│       ├── enums
│       ├── exception
│       ├── filter
│       ├── mapper
│       ├── repository
│       ├── security
│       ├── service
│       ├── util
│       └── SmsApplication.java
│
└── resources
    ├── application.properties
    └── application-local.properties
```

---

# ⚙️ Configuration

The project uses **Spring Profiles** to separate common configuration from local development settings.

### `application.properties`

Contains shared application configuration.

```properties
spring.application.name=SMS
spring.profiles.active=local
```

### `application-local.properties`

Create the following file inside:

```text
src/main/resources/application-local.properties
```

Example:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD

jwt.secretkey=YOUR_SECRET_KEY
```

> **Important**
>
> `application-local.properties` contains sensitive information and is excluded from Git using `.gitignore`.
>
> Never commit database credentials or JWT secrets to GitHub.

---

# 🗄️ Database Design

The database is designed using **BCNF (Boyce-Codd Normal Form)** to reduce redundancy and maintain data integrity.

### Core Entities

- User
- Student
- Teacher
- Admin
- Course
- Semester
- Section
- Course Offering
- Enrollment
- Marks

### Concepts Implemented

- ER Diagram Design
- BCNF Normalization
- Primary & Foreign Keys
- One-to-One Relationships
- One-to-Many Relationships
- Many-to-One Relationships
- Referential Integrity

---

# 🌐 REST APIs

The project exposes RESTful APIs for:

- Authentication
- Students
- Teachers
- Courses
- Semesters
- Sections
- Course Offerings
- Enrollments
- Marks
- Student APIs
- Teacher APIs
- Admin APIs

---

# 📄 API Documentation

After starting the application, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger provides:

- Interactive API Testing
- Request & Response Models
- JWT Authentication Support
- Complete API Documentation
---

# 🚀 Getting Started

## Prerequisites

- Java 21+
- Apache Maven
- PostgreSQL
- Git

---

## 1. Clone the Repository

```bash
git clone https://github.com/AleemMadniKhan/student-management-system.git

cd student-management-system
```

---

## 2. Configure the Application

Create:

```text
src/main/resources/application-local.properties
```

Example:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD

jwt.secretkey=YOUR_SECRET_KEY
```

This file is ignored by Git using `.gitignore` to keep sensitive information secure.

---

## 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the project directly from your IDE.

The backend will start at:

```text
http://localhost:8080
```

---

## 4. Open Swagger

Visit:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🔒 Security

The application implements multiple security layers to protect REST APIs.

- JWT Authentication
- Refresh Token Authentication
- Spring Security
- BCrypt Password Encryption
- Stateless Authentication
- Role-Based Authorization
- Permission-Based Authorization
- Method-Level Security
- Request Validation
- Global Exception Handling

---

# 📌 Project Status

The backend is fully functional and currently includes:

- ✅ JWT Authentication
- ✅ Refresh Token Authentication
- ✅ Spring Security
- ✅ CRUD Operations
- ✅ Role-Based Authorization
- ✅ Permission-Based Authorization
- ✅ PostgreSQL Integration
- ✅ Swagger Documentation
- ✅ Layered Architecture
- ✅ DTO Pattern
- ✅ Global Exception Handling
- ✅ GPA Calculation

---

# 🚀 Future Improvements

- Attendance Management
- Timetable Management
- File Uploads
- Email Notifications
- PDF & Excel Report Generation
- Docker Support
- Unit Testing
- Integration Testing
- CI/CD Pipeline
- Cloud Deployment

---

# 👨‍💻 Author

**Aleem Madni Khan**
Software Engineering Student — Karachi, Pakistan

[GitHub](https://github.com/AleemMadniKhan) · [LinkedIn](https://www.linkedin.com/in/aleemmadnikhan/)

---
