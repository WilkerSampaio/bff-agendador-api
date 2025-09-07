FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/bff-agendador-tarefas-api-0.0.1-SNAPSHOT.jar /app/bff-agendador-tarefas-api.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/bff-agendador-tarefas-api.jar"]
