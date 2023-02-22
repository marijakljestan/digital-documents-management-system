# digital-documents-management-system


### Backend
The backend is a Maven Spring Boot application. Source code is in the <i>./Agency/backend/agency</i> directory. It can be started localy as a Java project in Eclipse or IntelliJ. I used Java 17 and Spring Boot 2.7.5 version. Server is running on port 8080.\
PostgreSQL is needed for the backend. For that purpose, I used offical postgres:14.1 image from Docker Hub.
If you have docker, you can run both backend application and database by running <code> docker compose up --build </code> in mentioned root directory, but first you have to set all required environment variables.

### ELK stack
Whole ELK stack (Elasticsearch, Logstash and Kibana) can be started by running <code> docker compose up --build </code> in directory <i> ./docker-elk </i>, where you have to set all required environment variables and where you can change my configuration of this tools.
  
### Frontend
The frontend is an React application. Source code is in the <i>./Agency/frontend/agency-app</i> directory. It can be started by running <code>npm install</code> and then <code>npm run start</code> in the mentioned directory. The URL address is http://localhost:3000/.