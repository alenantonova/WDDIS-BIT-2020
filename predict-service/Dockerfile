FROM openjdk:12
RUN mkdir predict
COPY target/predict-service-0.1.0.jar /predict
WORKDIR /predict
CMD ["java", "-jar", "predict-service-0.1.0.jar"]