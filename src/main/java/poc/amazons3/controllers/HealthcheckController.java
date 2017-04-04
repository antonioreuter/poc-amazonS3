package poc.amazons3.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import poc.amazons3.monitoring.HealthCheck;

/**
 * Created by 310280812 on 3/30/2017.
 */

@RequestMapping("/healthcheck")
@Controller
@Slf4j
public class HealthcheckController {

    @Autowired
    private HealthCheck healthCheck;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public HealthCheck healthCheck() {
        healthCheck.check();

        return healthCheck;
    }
}