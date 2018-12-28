FROM java:8
VOLUME /tmp
ADD target/TaskManagerService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongocontainer/test","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"] 