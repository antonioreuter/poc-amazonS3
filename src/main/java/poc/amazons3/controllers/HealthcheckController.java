package poc.amazons3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 310280812 on 3/30/2017.
 */

@RequestMapping(value = "/health2")
@RestController
public class HealthcheckController {

    @RequestMapping(method = RequestMethod.GET)
    public String status() {
        return "LIVE";
    }
}
