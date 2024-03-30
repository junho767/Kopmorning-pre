FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/springboot-developer-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} liv-blog.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "liv-blog.jar"]