FROM eclipse-temurin:17-jdk-jammy

WORKDIR clinic-voll-app

COPY .mvn .mvn
COPY src src
COPY mvnw pom.xml ./

RUN chmod 777 /clinic-voll-app/mvnw
RUN /clinic-voll-app/mvnw dependency:resolve
RUN /clinic-voll-app/mvnw compile package -DskipTests

ENTRYPOINT ["/clinic-voll-app/mvnw", "spring-boot:run", "-Pproduction", "-DskipTests"]
