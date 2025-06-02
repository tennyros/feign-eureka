# Microservices Demo: Eureka, Feign, Circuit Breaker

This project demonstrates a simple microservices architecture using:
- **Eureka Server** for service discovery
- **Spring Cloud Config Server** for centralized configuration management
- **Feign Client** for inter-service communication
- **Circuit Breaker** support via Spring Cloud OpenFeign
- **API Gateway** as the unified entry point for all client requests

## Structure

```text
├── eureka-server    # Service registry
├── config-server    # Centralized configuration server
├── api-gateway      # Gateway routing requests to services
├── order-service    # Consumer of user-service, uses Feign + circuit breaker
├── user-service     # Simple user provider API
```

## Technologies

- Java 17
- Spring Boot 3
- Spring Cloud (Eureka, Config Server, OpenFeign, Gateway, LoadBalancer)
- Maven
- Caffeine Cache (for load balancing optimization)
- Resilience4j (circuit breaker via Feign)

## How to Run

**1. Start Eureka Server:**

```bash
cd eureka-server
./mvnw spring-boot:run
```

**2. Start Config Server:**

```bash
cd ../config-server
./mvnw spring-boot:run
```

**3. Start API Gateway:**

```bash
cd ../api-gateway
./mvnw spring-boot:run
```
   
**4. Start User Service:**

```bash
cd ../user-service
./mvnw spring-boot:run
```

**5. Start Order Service:**

```bash
cd ../order-service
./mvnw spring-boot:run
````

⚠ Make sure all application.yml files have correct ports, Eureka URL, and Config Server URL configured.

## Endpoints

```http
API Gateway (Unified Entry Point)
All service endpoints are exposed via the gateway, e.g.,

GET /users/{id}    – proxied to User Service  
GET /orders/{id}   – proxied to Order Service

User Service
GET /users/{id} – returns user info

Order Service
GET /orders/{id} – returns order info with user fetched via Feign
```

## Features

- Service discovery and dynamic load balancing
- Centralized configuration with Spring Cloud Config Server
- API Gateway for routing, security, and aggregation
- Declarative REST clients (Feign)
- Fallback handling and exception mapping
- Centralized error handling with @ControllerAdvice

## Example Request

```bash
curl http://localhost:8080/orders/1
```