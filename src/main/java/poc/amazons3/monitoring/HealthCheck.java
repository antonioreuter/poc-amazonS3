package poc.amazons3.monitoring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by 310280812 on 4/4/2017.
 */
@Component("healthCheck")
public class HealthCheck implements Serializable {

    @Value("${info.app.name}")
    @JsonProperty(value = "applicationName", index = 1)
    private String applicationName;

    @Value("${info.app.description}")
    @JsonProperty(value = "description", index = 2)
    private String description;

    @Value("${info.app.version}")
    @JsonProperty(value = "version", index = 3)
    private String version;


    @JsonProperty(value = "currentTime", index = 6)
    private Date currentTime = new Date();

    @JsonProperty(value = "totalStatus", index = 7)
    private boolean totalStatus = true;

    @JsonIgnore
    private Set<Probe> resources = new LinkedHashSet<>();

    @JsonProperty(value = "checks", index = 8)
    private Set<ProbeResult> checks;

    @Autowired
    private RedisProbe redisProbe;

    @PostConstruct
    private void initialize() {
        resources.add(redisProbe);
    }

    public Set<ProbeResult> check() {
        checks = new LinkedHashSet<>();
        totalStatus = true;

        if (CollectionUtils.isNotEmpty(this.resources) && CollectionUtils.isEmpty(checks)) {
            for (Probe probe : resources) {
                ProbeResult result = probe.check();
                checks.add(result);
                if (!result.isStatus() && !result.isOptional())
                    totalStatus = false;
            }
        }

        return checks;
    }
}