# 🎓 Student Management System

A secure **Student Management System** built using **Spring Boot**, **Spring Security (JWT)**, and **MySQL**. The project follows a clean layered architecture and exposes RESTful APIs for managing students, teachers, courses, enrollments, marks, semesters, and role-based dashboards.

This project was developed to demonstrate real-world backend development practices including authentication, authorization, database normalization, REST API design, and layered architecture.

---

## Overview

The Student Management System simulates a university management platform that manages the complete student academic lifecycle — from authentication and registration to course enrollment, assessment management, and GPA calculation.

The application supports three user roles — **Admin**, **Teacher**, and **Student** — each with distinct permissions and a dedicated dashboard.

---

## Features

### Authentication & Security

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

### Student Module

- Student Registration
- Student Profile
- Student Dashboard
- View Registered Courses
- View Assessment Marks
- View Semester Information
- Automatic GPA Calculation

### Teacher Module

- Teacher Registration
- Teacher Profile
- Teacher Dashboard
- View Assigned Courses
- View Assigned Sections
- View Total Enrolled Students

### Admin Module

- Manage Students
- Manage Teachers
- Manage Courses
- Manage Semesters
- Manage Sections
- Manage Course Offerings
- Manage Enrollments
- Manage Marks
- System Statistics Dashboard

### Academic Management

- Course Management
- Semester Management
- Section Management
- Course Offering Management
- Student Enrollment
- Weighted Assessment Marks
- GPA Calculation

---

## Dashboards

**Student Dashboard** — Student information, semester details, registered courses, assessment marks, course percentage, grade points, GPA, completed credits.

**Teacher Dashboard** — Teacher information, assigned courses, assigned sections, student count per section.

**Admin Dashboard** — Totals for students, teachers, courses, sections, enrollments, and course offerings, plus recently added students, teachers, and courses.

---

## Tech Stack

| Layer                 | Technologies                                                             |
| --------------------- | ------------------------------------------------------------------------ |
| **Backend**           | Java 21, Spring Boot, Spring Security, Spring Data JPA, Hibernate, Maven |
| **Database**          | MySQL                                                                    |
| **API Documentation** | Swagger / OpenAPI                                                        |
| **Testing**           | Postman, Swagger UI                                                      |

---

## Architecture

The application follows a clean layered architecture:

```text
                Client
                  │
           HTTP / REST API
                  │
                  ▼
          Spring Boot Controller
                  │
                  ▼
             Service Layer
                  │
                  ▼
          Repository Layer
                  │
                  ▼
             MySQL Database
```

### Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.aleemmadni.sms
│   │       ├── configuration
│   │       ├── controllers
│   │       ├── dto
│   │       │   ├── request
│   │       │   └── response
│   │       ├── exceptions
│   │       ├── filter
│   │       ├── mapper
│   │       ├── model
│   │       ├── repository
│   │       ├── security
│   │       ├── service
│   │       └── SmsApplication.java
│   │
│   └── resources
│       └── application.properties
│
└── test
```

---

## Database Design

The database is designed using **BCNF (Boyce-Codd Normal Form)** to reduce redundancy and maintain data integrity.

**Core entities:** User, Student, Teacher, Admin, Course, Semester, Section, Course Offering, Enrollment, Marks

**Concepts implemented:** ER diagram design, BCNF normalization, primary/foreign keys, one-to-one, one-to-many, and many-to-one relationships, SQL joins, referential integrity.

---

## REST APIs

The project exposes RESTful APIs for:

Authentication · Students · Teachers · Courses · Semesters · Sections · Course Offerings · Enrollments · Marks · Student Dashboard · Teacher Dashboard · Admin Dashboard

Interactive API documentation is available via Swagger after running the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

## GPA Calculation

GPA is calculated automatically using weighted assessment scores, course percentage, grade point conversion, and credit hour weighting. Grading logic is implemented inside dedicated service classes to keep the application modular and maintainable.

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL 8+

### 1. Clone the Repository

```bash
git clone https://github.com/AleemMadniKhan/student-management-system.git
cd student-management-system
```

### 2. Configure the Database

Create a MySQL database and update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management_system
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD

jwt.secret=YOUR_BASE64_SECRET_KEY
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`. Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.

---

## Status

Core backend is feature-complete: JWT authentication, role- and permission-based authorization, full CRUD across all modules, role-specific dashboard APIs, GPA calculation, and Swagger documentation are all implemented and working.

---

## Roadmap

- [ ] Attendance management
- [ ] Timetable management
- [ ] File uploads
- [ ] Email notifications
- [ ] Report generation (PDF/Excel)
- [ ] Docker support
- [ ] Unit and integration test coverage

---

## Author

**Aleem Madni Khan**
Software Engineering Student — Karachi, Pakistan

[GitHub](https://github.com/AleemMadniKhan) · [LinkedIn](https://www.linkedin.com/in/aleemmadnikhan/)

---

If you found this project helpful, consider giving it a star on GitHub.
