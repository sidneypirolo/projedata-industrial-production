# Industrial Production Optimization System

## Overview

This application was developed to manage industrial raw materials and optimize production in order to maximize total revenue based on available stock.

The system analyzes available raw materials and determines which products should be manufactured to achieve the highest possible total sales value.

---

## Architecture

The application follows a full-stack architecture:

- Backend: REST API built with Spring Boot
- Frontend: Vue.js application (Vite)
- Database: H2 (in-memory relational database)

---

## Technologies Used

### Backend
- Java 17
- Spring Boot
- Maven
- H2 Database
- JUnit (Unit Tests)

### Frontend
- Vue 3
- Vite
- Axios

---

## Project Structure
industrial-production-optimization/ 
           │├── backend/ → Spring Boot REST API 
           └── frontend/ → Vue.js application

##  How to Run the Backend

1. Navigate to the backend folder:
2. Run the application:

Windows:
mvnw.cmd spring-boot:run

Mac/Linux:
./mvnw spring-boot:run

The backend will run at:
http://localhost:8081


## How to Run the Frontend

1. Navigate to the frontend folder:
cd frontend

2. Install dependencies:
npm install

3. Start the application:
npm run dev

The frontend will run at:
http://localhost:5173


## Main Feature – Production Optimization

Endpoint:
GET /production/optimization

The optimization logic uses a **greedy algorithm**:

1. Products are sorted by highest sales value.
2. The system checks available raw materials.
3. It produces as many units as possible of the most profitable product.
4. Stock is virtually updated.
5. The process continues until raw materials are exhausted.

The result includes:
- Suggested products to manufacture
- Quantity of each product
- Total potential revenue


## Tests

Unit tests were implemented in the backend to validate the production optimization logic


## Functionalities Implemented

-CRUD for Raw Materials  
-CRUD for Products  
-Production optimization calculation  
-REST API  
-Simple frontend interface  


## Author

Sidney Pirolo Dezotti Simoni 
