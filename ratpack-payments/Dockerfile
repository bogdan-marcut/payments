FROM openjdk:11.0-jre

ENV JDBC_URL jdbc:mysql://localhost:3306/payments

COPY ./build/libs/ratpack-payments-all.jar /tmp/

ENTRYPOINT ["java", "-jar", "/tmp/ratpack-payments-all.jar"]