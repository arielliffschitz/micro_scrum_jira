FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-task-0.0.1-SNAPSHOT.jar msvc-task.jar

ENTRYPOINT [ "java", "-jar", "msvc-task.jar" ]