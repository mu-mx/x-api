FROM openjdk:8

VOLUME /opt/java/x-api

COPY  x-admin/x-web-api/target/*.jar  /opt/java/x-api/app.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/opt/java/x-api/app.jar"]