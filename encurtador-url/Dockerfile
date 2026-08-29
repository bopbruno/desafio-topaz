FROM maven:3.9.6-eclipse-temurin-8 AS build

WORKDIR /app

# Copia só o pom.xml primeiro para aproveitar cache de dependências do Docker
COPY pom.xml .
RUN mvn -B dependency:go-offline || true

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM jboss/wildfly:10.1.0.Final

COPY --from=build /app/target/encurtador-url.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
