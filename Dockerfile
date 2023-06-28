FROM openjdk:8

VOLUME /opt/java

COPY  x-admin/x-web-api/target/*.jar  /opt/java/app.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/opt/java/app.jar"]