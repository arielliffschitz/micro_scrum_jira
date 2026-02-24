FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-project-0.0.1-SNAPSHOT.jar msvc-project.jar

ENTRYPOINT [ "java", "-jar", "msvc-project.jar" ]