FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY build/libs/webservice.jar /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "webservice.jar"]
