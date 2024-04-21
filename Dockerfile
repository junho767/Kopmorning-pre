# ChatGPT의 도움을 받아 docker-compose.yml과 호환되도록 수정한 파일
# 서버를 구동시킬 자바를 받아옵니다.
FROM openjdk:17-jdk

# `JAR_FILE` 이라는 이름으로 build 한 jar 파일을 지정합니다.
ARG JAR_FILE=./build/libs/*.jar

# 지정한 jar 파일을 kopmorning.jar 라는 이름으로 Docker Container에 추가합니다.
COPY ${JAR_FILE} kopmorning.jar

ENV TZ Asia/Seoul

# kopmorning.jar 파일을 실행합니다.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kopmorning.jar"]