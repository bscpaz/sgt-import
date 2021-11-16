FROM maven AS maven
WORKDIR /usr/src/sgt-import
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:11
WORKDIR /usr/src/sgt-import
COPY --from=maven /usr/src/sgt-import/target/sgt-import.jar .
ENTRYPOINT ["java", "-jar", "sgt-import.jar"]