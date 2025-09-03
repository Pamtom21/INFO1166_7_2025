# Etapa de compilación
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Render usa la variable PORT
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
