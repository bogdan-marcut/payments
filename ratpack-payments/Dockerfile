FROM openjdk:11.0-jre

COPY ./target/ /tmp/

ENTRYPOINT ["java", "-jar", "/tmp/ratpack-payments.jar"]