FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY build/libs/postcodeservice.jar /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "postcodeservice.jar"]
