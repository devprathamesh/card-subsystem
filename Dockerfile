FROM openjdk:8-alpine
ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"
WORKDIR application
VOLUME /tmp
COPY target/card-subsystem-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "card-subsystem-0.0.1-SNAPSHOT.jar"]
