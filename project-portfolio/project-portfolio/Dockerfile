# 1. Use Maven with Java 21 to build the app
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build JAR
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Use a smaller JDK image to run the app
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy only the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the same port as in application.properties (8089 for you)
EXPOSE 8089

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
