# spring-boot-microservice
spring-boot-microservice-project
# Spring Boot Microservices: Order-Payment-Notification

This project demonstrates a **microservices architecture** using Spring Boot, implementing the following patterns:

- **CQRS (Command Query Responsibility Segregation)**
- **Event-Driven Architecture with Kafka**
- **Saga Pattern (Choreography)**
- **Circuit Breaker for fault tolerance**

## Microservices

1. **Order Service**
   - Handles order creation, cancellation
   - Emits `OrderCreatedEvent`, `OrderCancelledEvent`

2. **Payment Service**
   - Handles payment processing
   - Consumes `OrderCreatedEvent` and emits `PaymentCompletedEvent` or `PaymentFailedEvent`

3. **Notification Service**
   - Sends email/SMS notifications
   - Consumes `PaymentCompletedEvent` and emits `NotificationSentEvent`

## Architecture

- Commands and write operations are separated from read models (**CQRS**)
- Services communicate asynchronously using **Kafka events**
- Distributed transactions are managed using **Saga (Choreography)** pattern

## Running the Project

1. Start Kafka locally or via Docker
2. Start each microservice:
   ```bash
   ./mvnw spring-boot:run
