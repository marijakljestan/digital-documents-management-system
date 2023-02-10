FROM maven:3.8.7-openjdk-18-slim AS agencyBackendBuild
WORKDIR /root/src/backend
COPY . .
RUN mvn package  -DskipTests

FROM openjdk:19 AS agencyBackendRuntime
WORKDIR /app
COPY --from=agencyBackendBuild /root/src/backend/target/agency-exe.jar ./
VOLUME ./logs
VOLUME ./cv
VOLUME ./coverLetters
COPY --from=agencyBackendBuild /root/src/backend/logs ./logs
EXPOSE 8080
CMD java -jar agency-exe.jar
