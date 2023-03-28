# Use a base image with Maven and Java 11 installed
FROM maven:3.8.3-openjdk-11-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source code to the container
COPY . .
ENTRYPOINT [ "java","-jar","target/springmvc-1.jar" ]
# Build the Spring Boot application with Maven
RUN mvn clean package -DskipTests

# Expose port 8080 for the application
EXPOSE 8080

# Start the application when the container is launched
CMD ["java", "-jar", "target/springmvc.jar"]