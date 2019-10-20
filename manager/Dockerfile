# BUILD JAR WITH GRADLE.
FROM gradle:5.5-jdk11 as builder
RUN mkdir -p /home/gradle/project/
COPY --chown=gradle:gradle . /home/gradle/project/
WORKDIR /home/gradle/project/
#RUN cd /home/gradle/project/ && ls
ARG service_environment
ENV SPRING_PROFILES_ACTIVE=$service_environment
RUN gradle build --stacktrace

# BUILD URL-SHORTENER SERVICE IMAGE.
# Base image OpenJDK 11 running on Debian Stretch Slim OS.
FROM openjdk:11-slim-stretch
VOLUME /tmp
# Rename the built JAR file and copy it to the container.
COPY --from=builder /home/gradle/project/build/libs/service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
