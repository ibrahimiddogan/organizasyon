# Maven ve Java 20 kullanarak projeyi derle
FROM maven:3.9.9-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Java 20 kullanarak çalıştır
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/organization-0.0.1-SNAPSHOT.jar ./organization.jar
EXPOSE 8080
CMD ["java", "-jar", "organization.jar"]
