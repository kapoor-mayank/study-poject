# Stage 1: Build the application using Java 8 and Maven
FROM maven:3.9.6-eclipse-temurin-8 AS builder

# Set working directory
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project with custom profile for dependencies
RUN mvn clean package -P jar-with-dependencies -DskipTests

# Stage 2: Run the application using Java 8
FROM eclipse-temurin:8-jdk

WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/conf ./conf

# Expose multiple ports (customize as needed)
EXPOSE 6137

# Start the application
ENTRYPOINT ["java", "-jar", "tracker.jar", "conf/traccar.xml"]
