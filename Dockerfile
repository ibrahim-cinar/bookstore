FROM maven:3.8.3-openjdk-17 AS build
COPY src /github/bookstore/src
COPY pom.xml /github/bookstore/
WORKDIR /github/bookstore
RUN mvn clean package
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/bookstore-0.0.1-SNAPSHOT.jar"]



