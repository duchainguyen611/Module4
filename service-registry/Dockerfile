FROM eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} service-registry.jar
ENTRYPOINT ["java", "-jar", "service-registry.jar"]
LABEL authors="Admin"