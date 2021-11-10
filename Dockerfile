FROM openjdk:8u111-jre-alpine
VOLUME /customermanagement
EXPOSE 9090
ADD ./target/customermanagement-1.0.0.jar customermanagement.jar
ENTRYPOINT ["java","-jar","/customermanagement.jar"]