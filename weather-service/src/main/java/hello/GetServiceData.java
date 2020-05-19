package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetServiceData {
    String temperature;

    public void takeWeather(String request_date) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.darksky.net/forecast/7680422f32effb534f62f1283f0c38be/42.3601,-71.0589," + request_date, String.class);
        this.temperature = response.getBody();
    }
}
