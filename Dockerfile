FROM openjdk:8
VOLUME /tmp
EXPOSE 8010
ADD ./target/credit-card-account-ms-0.0.1-SNAPSHOT.jar credit-card-account-ms.jar
ENTRYPOINT ["java","-jar","/credit-card-account-ms.jar"]