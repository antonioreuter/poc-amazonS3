package poc.amazons3.healthindicators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by 310280812 on 4/5/2017.
 */
@Component("scondCustom")
public class SecondCustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("Details:", "Testing the second custom health indicator!").build();
    }
}