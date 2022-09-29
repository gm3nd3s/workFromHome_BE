FROM maven:3.8-openjdk-18-slim as builder

RUN mkdir -p /app
WORKDIR /app
ADD pom.xml .
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests

#ENTRYPOINT ["java","-jar","/springSecurity-0.0.1-SNAPSHOT.jar"]

FROM openjdk:18-jdk-alpine as runner
ENV MYSQL_DB=backendProject
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=password
ENV SERVER_PORT=8083
ENV HIBERNATE_DDL_AUTO=update
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]