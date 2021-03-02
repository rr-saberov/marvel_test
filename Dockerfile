FROM adoptopenjdk/openjdk11:alpine-jre
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} marvel-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/marvel-api-0.0.1.jar"]
#FROM adoptopenjdk/openjdk11:alpine-jre
#VOLUME /tmp
#COPY target/marvel-api*.jar marvel-api.jar
#CMD java ${JAVA_OPTS} -jar marvel-api.jar