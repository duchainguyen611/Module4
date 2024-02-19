FROM eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]
LABEL authors="Admin"