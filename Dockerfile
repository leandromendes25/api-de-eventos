FROM ubuntu:latest as build

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-slim

EXPOSE 8080

COPY --from=build /target/Api-de-eventos-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","jar","Api-de-eventos-0.0.1-SNAPSHOT.jar"]