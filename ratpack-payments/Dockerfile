FROM openjdk:11.0-jre

ENV RATPACK_DB_URL jdbc:mysql://10.5.0.2:3306/payments

COPY ./target/ratpack-payments.jar /tmp/

ENTRYPOINT ["java", "-jar", "/tmp/ratpack-payments.jar"]