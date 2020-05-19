package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RestController
public class WeatherServiceController {
    @Autowired
    private WeatherClient weatherClient = new WeatherClient();

    @RequestMapping(value= "/weather-server", method = RequestMethod.GET)
    public String run_weather() throws IOException { return weatherClient.getCurrentForecast(); }
}
