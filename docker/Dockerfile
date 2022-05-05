FROM openjdk:11-jre-slim as builder
LABEL creator="Tareq Mohammad Yousuf"
LABEL email="tareq.yousuf@vivacomsolutions.com"
WORKDIR application
ARG JAR_FILE=springbootexamples.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
