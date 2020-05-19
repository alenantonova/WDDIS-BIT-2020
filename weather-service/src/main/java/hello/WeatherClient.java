package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.DataBase.Weather;
import hello.DataBase.WeatherRepo;
import hello.WeatherDataParsers.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class WeatherClient {
    @Autowired
    private WeatherRepo weatherRepo;

    @Transactional
    public void addTemperatureToBase(Double temperature, long time) {
        Weather newItem = new Weather(time, temperature);
        weatherRepo.save(newItem);
    }

    public String getResponse(String time){
        GetServiceData getter = new GetServiceData();
        getter.takeWeather(time);
        return getter.temperature;
    }

    public WeatherData parseResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        WeatherData weatherData = mapper.readValue(response, WeatherData.class);
        System.out.println(weatherData.currently);
        return weatherData;
    }

    @Transactional
    public double getCurrentWeather() throws IOException {
        long cur_time = System.currentTimeMillis() / 1000L;
        Optional<Weather> temp = weatherRepo.findByTime(cur_time);
        if (temp.isPresent()) {
            System.out.println("Temperature was found in DB\n");
            return (temp.map(Weather::getTemperature)).get();
        } else {
            String response = getResponse(Long.toString(System.currentTimeMillis() / 1000L));
            WeatherData weatherData = parseResponse(response);
            System.out.println("Add new temperature to DB\n");
            addTemperatureToBase(weatherData.currently.temperature, cur_time);
            return weatherData.currently.temperature;
        }
    }

    @Transactional
    public Optional<Weather> tryGetWeatherFromBase(long time) {
        Optional<Weather> temp = weatherRepo.findByTime(time);
        if (temp.isPresent()) {
            return temp;
        } else {
            return Optional.of(new Weather(-1, 0.0));
        }
    }

    public List<Double> getAllTemperature() throws IOException {
        List<Double> data = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            String response = getResponse(Long.toString(System.currentTimeMillis() / 1000L - i *86400L));
            WeatherData weatherData = parseResponse(response);
            data.add(weatherData.currently.temperature);
        }
        return data;
    }

    public String getCurrentForecast() throws IOException {
        return "Current temperature : " + Double.toString(getCurrentWeather());
    }

    public static void main(String[] args){
        SpringApplication.run(WeatherClient.class);
    }

}
