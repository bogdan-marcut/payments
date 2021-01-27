FROM openjdk:11.0-jre

ENV JDBC_URL jdbc:mysql://10.5.0.2:3306/payments

COPY ./target/micronaut-payments-0.1-shaded.jar /tmp/

ENTRYPOINT ["java", "-jar", "/tmp/micronaut-payments-0.1-shaded.jar"]