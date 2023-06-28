FROM openjdk:8

VOLUME /opt/java

COPY  x-admin/x-web-api/target/*.jar  app.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/app.jar"]