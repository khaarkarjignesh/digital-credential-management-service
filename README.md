# Digital Credential Management Service

A Spring Boot service providing credential template management and issuance for users. 
Implements Builder and Factory patterns, OpenAPI/Swagger, SLF4J logging, persistence, validation and pagination.

## Features
- Create dynamic credential templates (supports arbitrary fields) using Builder pattern.
- Issue credentials to users using Factory pattern (different credential types).
- Validate required fields and prevent duplicate template names.
- Store templates and issued credentials in database.
- Fetch user credentials with pagination.
- OpenAPI/Swagger documentation.
- Logging with SLF4J.

## Tech Stack

-   Java 17
-   Spring Boot 3.5.8
-   Spring Data JPA
-   MySQL
-   Swagger (OpenAPI 3)
-   Lombok
-	SLF4J

## Setup Instructions

### 1. Clone the repo

    git clone https://github.com/khaarkarjignesh/digital-credential-management-service.git

### 2. Update DB config (`application.properties`)

    spring.datasource.url=jdbc:mysql://localhost:3306/credential_system
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update

### 3. Run the application

    mvn spring-boot:run

### 4. Swagger URL

    http://localhost:8080/swagger-ui/index.html

## APIs Overview

### 1️⃣ Create Credential Template

**POST** `/api/v1/credential-templates`

### 2️⃣ Issue Credential

**POST** `/api/v1/credentials`

### 3️⃣ Verify Credential

**POST** `/api/v1/credentials/verify`

### 4️⃣ Fetch User Credentials

**GET** `/api/v1/users/{userId}/credentials?page=0&type=passport`