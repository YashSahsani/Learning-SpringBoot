# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-23-alpine as builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:24-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/learning-spring.jar app.jar
EXPOSE 8080
ENV DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/learning_spring
ENV DATASOURCE_USERNAME=root
ENV DATASOURCE_PASSWORD=prodroot
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]
