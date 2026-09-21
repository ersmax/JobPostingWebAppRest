# 1) Build React
FROM node:20-alpine AS frontend
WORKDIR /ui
COPY CRUD-UI/package*.json ./
RUN npm ci
COPY CRUD-UI/ ./
RUN CI=false npm run build

# 2) Build Spring Boot with React inside /static
FROM maven:3.9-eclipse-temurin-21 AS backend
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
COPY --from=frontend /ui/build ./src/main/resources/static
RUN mvn -q clean package -DskipTests

# 3) Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=backend /app/target/*.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]