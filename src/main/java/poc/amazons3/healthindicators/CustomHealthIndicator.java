package poc.amazons3.healthindicators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by 310280812 on 4/5/2017.
 */

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("Details:", "Testing the custom health indicator!").build();
    }
}
