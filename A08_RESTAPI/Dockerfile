FROM openjdk:21
ADD target/rest-api.jar rest-api.jar
ENTRYPOINT ["java", "-jar", "rest-api.jar"]


# docker build -t rest-api:25 . // to build
# docker run -it -p 8080:8080 rest-api:25 // to run