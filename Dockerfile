
# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application package
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime production image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the executable jar from the build stage
COPY --from=build /app/target/order-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8082

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
