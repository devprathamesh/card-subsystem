#STAGE 1
FROM openjdk:8-jre-alpine as builder
WORKDIR application
COPY maven/${project.build.finalName}.jar ./
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract
ENTRYPOINT ["java", "-jar", "card-subsystem-0.0.1-SNAPSHOT.jar"]

# STAGE 2
FROM openjdk:8-jre-alpine
WORKDIR application
VOLUME /tmp
COPY --from=builder application/dependencies ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/snapshot-dependencies ./
COPY --from=builder application/application ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]