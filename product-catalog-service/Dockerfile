FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product-catalog-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test","/product-catalog-service.jar"]