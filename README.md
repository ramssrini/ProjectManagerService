# TaskManagerService

Step 1:
docker network create fsd_workout_network

Step 2:
docker run -d --name mongocontainer --network=fsd_workout_network -v ~/mongo-data:/data/db mongo

Step 3: Clone the source code. Navigate to the application folder and build the application using the following command
./mvnw install dockerfile:build

Step 4:
docker run -p 8081:8081 --name fsdTaskManager --network=fsd_workout_network ramssrini/spring-boot-taskmanager-service:0.0.1-SNAPSHOT
