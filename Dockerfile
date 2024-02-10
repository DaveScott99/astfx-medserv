FROM eclipse-temurin:17-jdk-alpine
LABEL authors="Davi Santos"
WORKDIR /app
COPY target/media-1.1.5.jar media-1.1.5.jar
EXPOSE 8763
CMD ["java", "-jar", "media-1.1.5.jar"]