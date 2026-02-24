FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-sprint-backlog-0.0.1-SNAPSHOT.jar msvc-sprint-backlog.jar

ENTRYPOINT [ "java", "-jar", "msvc-sprint-backlog.jar" ]