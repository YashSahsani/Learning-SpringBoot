# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-23-alpine as builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/learning-spring.jar app.jar

