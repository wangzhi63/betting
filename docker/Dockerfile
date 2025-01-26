# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-bullseye

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file to the container
COPY betting.jar /app/app.jar

# Set the command to run the application
CMD ["java", "-jar", "/app/app.jar"]
