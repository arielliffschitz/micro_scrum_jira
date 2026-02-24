FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-audit-0.0.1-SNAPSHOT.jar msvc-audit.jar

ENTRYPOINT [ "java", "-jar", "msvc-audit.jar" ]
