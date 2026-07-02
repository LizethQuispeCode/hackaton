# Stage 1: Build con tu propia imagen de Maven
FROM lizethquispesuarez/maven:3.9-amazoncorretto-25-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# Stage 2: Run con tu propia imagen de Java
FROM lizethquispesuarez/eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copiamos el .jar generado
COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080

# Exponemos el puerto configurado para Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


#docker pull maven:3.9-amazoncorretto-25-alpine
#docker pull eclipse-temurin:25-jre-alpine

##docker tag maven:3.9-amazoncorretto-25-alpine tuusuario/maven:3.9-amazoncorretto-25-alpine

##docker tag eclipse-temurin:25-jre-alpine tuusuario/eclipse-temurin:25-jre-alpine

##docker push tuusuario/maven:3.9-amazoncorretto-25-alpine
##docker push tuusuario/eclipse-temurin:25-jre-alpine