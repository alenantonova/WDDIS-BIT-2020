FROM openjdk:12
RUN mkdir weather
COPY target/weather-service-0.1.0.jar /weather
WORKDIR /weather
CMD ["java", "-jar", "weather-service-0.1.0.jar"]