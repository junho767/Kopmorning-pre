FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/springboot-developer-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} kopmorning.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "kopmorning.jar"]