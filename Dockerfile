FROM openjdk:8

WORKDIR /opt/java

COPY  x-admin/x-web-api/target/*.jar  app.jar
COPY  db  ./db

EXPOSE 80

CMD ["java","-jar","app.jar"]