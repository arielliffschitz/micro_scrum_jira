FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-product-backlog-0.0.1-SNAPSHOT.jar msvc-product-backlog.jar

ENTRYPOINT [ "java", "-jar", "msvc-product-backlog.jar" ]
