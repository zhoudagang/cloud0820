FROM airdock/oraclejdk
VOLUME /tmp
ADD cloud-gateway-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]