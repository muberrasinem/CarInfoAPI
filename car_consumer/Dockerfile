# Use OpenJDK as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
EXPOSE 8082
# Copy the built JAR file into the container
COPY target/car-consumer-0.0.1-SNAPSHOT.jar car-consumer.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "car-consumer.jar"]