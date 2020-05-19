package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class PredictionClient {
    public static void main(String[] args) {
        SpringApplication.run(PredictionClient.class, args);
    }

    public String getWeatherPrediction() throws IOException, RestClientException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://rbc:8093/rbc-server";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String rates = response.getBody();
        System.out.println(rates);
        String url2 = "http://weather:8091/weather-server";
        ResponseEntity<String> response2 = restTemplate.getForEntity(url2, String.class);
        String weather = response2.getBody();
        System.out.println(weather);
        return rates+weather;
    }
}
