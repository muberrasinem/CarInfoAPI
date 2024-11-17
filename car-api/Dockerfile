# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/car-api-0.0.1-SNAPSHOT.jar /app/car-api.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "car-api.jar"]
