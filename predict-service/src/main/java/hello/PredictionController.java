package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RestController
public class PredictionController {

    @Autowired
    private PredictionClient predictionClient = new PredictionClient();

    @RequestMapping(value = "/pred-server", method = RequestMethod.GET)
    public String run_pred() throws IOException { return predictionClient.getWeatherPrediction(); }
}
