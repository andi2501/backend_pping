# Layer 1: Build the JAR file and put it into the Maven target folder.
#          From here it will be picked up by the second layer.
FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /usr/app
ADD . /usr/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/app/pom.xml clean package -DskipTests

# Layer 2: Pick up the previously built JAR file and make it executable
#          by an entrypoint. This ensures the application will be running
#          once the container will have been started.
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /usr/app/target/pping-backend.jar /app/pping-backend.jar
ENTRYPOINT java -jar /app/pping-backend.jar
