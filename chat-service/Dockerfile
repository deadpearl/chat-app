FROM openjdk:11-jre

WORKDIR /app

COPY target/chat-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
