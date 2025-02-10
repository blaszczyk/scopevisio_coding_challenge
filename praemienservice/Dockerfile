FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY build/libs/praemienservice.jar /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "praemienservice.jar"]
