FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/order-0.0.1-SNAPSHOT.jar OrderService.jar
ENTRYPOINT ["java", "-jar", "OrderService.jar"]