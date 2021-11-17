FROM adoptopenjdk/openjdk11:x86_64-alpine-jre11u-nightly
VOLUME /customermanagement
EXPOSE 9090
ADD ./target/customermanagement-1.0.0.jar customermanagement.jar
ENTRYPOINT ["java","-jar","/customermanagement.jar"]