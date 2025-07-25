#FROM maven:3.9.4-eclipse-temurin-21-alpine
#
#WORKDIR /app
#
#COPY ./target/A28_SAMPLE-FOR-DOCKER-0.0.1-SNAPSHOT.jar .
#
#EXPOSE 8080
#
#CMD ["java", "-jar", "A28_SAMPLE-FOR-DOCKER-0.0.1-SNAPSHOT.jar"]


FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]




#WORKDIR : create 'app' folder inside container and set it for working directory like : cd app
#COPY : Copies the jar file from the host machine to the /app directory inside the container, Here  it copies jar file to . means inside /app , because /app is working directory
#CMD : Runs the jar file inside the container, if commands not provided
#ENTRYPOINT : Runs the command every time even if command provided
#ADD : it is same as COPY but it Can automatically extract .tar, .tar.gz, .zip files.
#EXPOSE : says developers that our container is listening on port 8080, so they can forward this port to them system


FROM openjdk:21-alpine
WORKDIR /app
COPY target/A28_SAMPLE-FOR-DOCKER-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "A28_SAMPLE-FOR-DOCKER-0.0.1-SNAPSHOT.jar"]