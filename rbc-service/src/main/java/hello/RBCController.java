package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RestController
public class RBCController {

    @Autowired
    private RBCClient client = new RBCClient();

    @RequestMapping(value = "/rbc-server", method = RequestMethod.GET)
    public String run_rbc() {
        return client.calculateMaxRate();
    }

}