FROM maven:3.9.4-eclipse-temurin-20
ARG EXTENT_VERSION=2.41.2
WORKDIR /QPros-Web
COPY src /web/SeleniumTests/src
COPY ./pom.xml /web/SeleniumTests/pom.xml
ENTRYPOINT mvn clean test
