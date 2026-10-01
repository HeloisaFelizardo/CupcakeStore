FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "target/cupcake-store-0.0.1-SNAPSHOT.jar"]