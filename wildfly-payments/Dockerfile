FROM eu.gcr.io/bright-modem-181915/tol-base-wildfly:22.0.0_13

# Switch to jboss user
USER jboss
WORKDIR /opt/jboss

ENV JDBC_URL jdbc:mysql://10.5.0.2:3306/payments

EXPOSE 8080 8080

RUN chmod +x ${JBOSS_HOME}/bin/standalone.sh

RUN curl -o /tmp/mysql-connector-java.tar.gz -SsL https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.23.tar.gz && \
	mkdir -p ${JBOSS_HOME}/modules/system/layers/base/com/mysql/driver/main/ && \
  	tar -p -C /tmp -xzf /tmp/mysql-connector-java.tar.gz  && \
  	mv /tmp/mysql-connector-java-*/mysql-connector-java-*.jar ${JBOSS_HOME}/modules/system/layers/base/com/mysql/driver/main/mysql-connector.jar && \
  	rm -Rf /tmp/mysql*


COPY ./standalone.xml ${JBOSS_HOME}/standalone/configuration

# Copy application
COPY ./target/wildfly-payments.war ${JBOSS_HOME}/standalone/deployments


ENTRYPOINT ["sh", "-c", "${JBOSS_HOME}/bin/standalone.sh -b=0.0.0.0 --server-config=standalone.xml"]