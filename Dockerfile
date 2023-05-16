FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR spring-redis-cache
COPY --from=build target/*.jar spring-redis-cache.jar
ENTRYPOINT ["java", "-jar", "spring-redis-cache.jar"]