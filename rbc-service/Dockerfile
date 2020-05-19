FROM openjdk:12
RUN mkdir rbc
COPY target/rbc-service-0.1.0.jar /rbc
WORKDIR /rbc
CMD ["java", "-jar", "rbc-service-0.1.0.jar"]