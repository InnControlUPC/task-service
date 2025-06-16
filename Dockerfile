# Use maven image with jdk 17 to build the project
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory in the image
WORKDIR /app

# Copy pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean install -DskipTests

# Use openjdk 21 for running the application
FROM openjdk:21-jdk-slim

# Copy the jar file from the build stage
COPY --from=build /app/target/inncontrol-task-service-0.0.1-SNAPSHOT.jar task-app.jar

# Expose the application's port
EXPOSE 8090

# Run the application
ENTRYPOINT ["java","-jar","/task-app.jar"]