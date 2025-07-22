# 🚀 Async Order Processing System

A scalable, cloud-native microservices backend built with **Java**, **Spring Boot**, **Docker**, **AWS SQS**, and **Kubernetes**. Designed to asynchronously handle high-volume order processing using distributed messaging queues.

---

## 🧠 Architecture

```plaintext
[Client] → [Producer REST API] → [AWS SQS Queue] → [Consumer Service] → [Database / Logging]
```

- `producer-service`: Accepts and validates incoming order requests, sends to SQS queue.
- `consumer-service`: Listens to SQS, processes each message asynchronously.

---

## 🛠️ Tech Stack
- Java 17 + Spring Boot
- AWS SQS (via LocalStack for local development)
- Docker & Kubernetes
- GitHub Actions CI/CD
- Maven

---

## 🧪 How to Run Locally

```bash
# In producer-service
./mvnw clean package
java -jar target/*.jar

# In consumer-service
./mvnw clean package
java -jar target/*.jar
```

---

## ☸️ Kubernetes Deployment

```bash
kubectl apply -f k8s/
```

---

## 🔁 CI/CD Pipeline
- GitHub Actions builds both services
- Builds Docker images
- Pushes to DockerHub (requires secrets)

---

## 📬 Sample API
```http
POST /orders
Content-Type: application/json

{
  "orderId": "123",
  "product": "Laptop"
}
```

---

## 🏷️ Badges

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Tech](https://img.shields.io/badge/SpringBoot-Java-blue)
