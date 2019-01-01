FROM java:8
VOLUME /tmp
ADD target/ProjectManagerService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongocontainer/test","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"] 