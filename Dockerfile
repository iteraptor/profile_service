#FROM alpine:3.13
#RUN apk add openjdk
FROM openjdk:17-alpine3.14

COPY ./target/profile_service.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]