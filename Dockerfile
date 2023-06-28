FROM openjdk:8

VOLUME /opt/x-api

COPY  x-admin/x-web-api/target/*.jar  /opt/x-api/app.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/opt/java/app.jar"]