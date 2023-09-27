FROM openjdk:17-jdk-alpine
COPY target/URLShortenerManagementService-0.0.1-SNAPSHOT.jar URLShortenerManagementService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/URLShortenerManagementService-0.0.1-SNAPSHOT.jar"]