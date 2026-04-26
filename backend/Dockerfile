# STAGE 1: Build the JAR
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
# Copy only the pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# STAGE 2: Run the JAR
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
# Copy the compiled jar from the build stage
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]