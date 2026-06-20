# 🛒 MyShop App - Microservices E-Commerce Platform

## Overview

MyShop is a scalable e-commerce backend built using Spring Boot Microservices architecture. The project demonstrates service decomposition, API Gateway routing, service discovery, centralized configuration, asynchronous communication with Kafka, and distributed business workflows.

The platform manages users, products, inventory, orders, payments, notifications, and AI-powered services while maintaining loose coupling between services.

---

## Architecture

```text
Client
   │
   ▼
API Gateway
   │
   ├── User Service
   ├── Product Service
   ├── Inventory Service
   ├── Order Service
   ├── Payment Service
   ├── Notification Service
   └── AI Service

Supporting Infrastructure
   ├── Service Discovery (Eureka)
   ├── Config Server
   ├── Kafka
   └── MySQL Databases
```

---

## Services

### User Service

* User management
* User profile operations
* Authentication-ready structure

### Product Service

* Product catalog management
* Product CRUD operations
* Product pricing information

### Inventory Service

* Inventory tracking
* Stock validation
* Stock deduction during order processing

### Order Service

* Shopping cart management
* Order creation
* Order cancellation
* Order history

### Payment Service

* Payment processing
* Payment status tracking
* Kafka consumer for order events

### Notification Service

* Event-driven notifications
* Order and payment notifications

### AI Service

* AI-powered integrations
* Future recommendation and assistant capabilities

### Common Service

* Shared DTOs
* Event contracts
* Common utilities

---

## Event-Driven Workflow

### Order Placement Flow

1. User adds products to cart
2. Order Service validates inventory
3. Order is created
4. Inventory is reduced
5. Cart is checked out
6. OrderCreatedEvent is published to Kafka
7. Payment Service consumes event
8. Payment is processed
9. Notification Service can react to payment events

---

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Microservices

* Spring Cloud Gateway
* Eureka Service Discovery
* Spring Cloud Config

### Messaging

* Apache Kafka

### Database

* MySQL

### Build Tool

* Maven

### Testing

* Postman

### Development Tools

* IntelliJ IDEA
* Git
* GitHub

---

## Key Concepts Implemented

* Microservices Architecture
* API Gateway Pattern
* Service Discovery
* Centralized Configuration
* Event-Driven Architecture
* Asynchronous Communication
* Domain-Driven Service Separation
* Distributed System Design
* RESTful APIs
* DTO Pattern
* Repository Pattern

---

## Project Structure

```text
myshop
├── api-gateway
├── service-discovery
├── config-server
├── user-service
├── product-service
├── inventory-service
├── order-service
├── payment-service
├── notification-service
├── ai-service
└── common-service
```

---



## Author

Amul Gupta

Built as a hands-on project to gain practical experience in Spring Boot Microservices, Kafka, Distributed Systems, and Enterprise Backend Development.

