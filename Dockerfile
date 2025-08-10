FROM eclipse-temurin:17-jdk-focal
COPY target/*.jar /app.jar
ENV JAVA_TOOL_OPTIONS="-Dio.netty.transport.epoll.enabled=false"
ENTRYPOINT ["java","-jar","/app.jar"]
