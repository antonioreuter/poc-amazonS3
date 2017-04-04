package poc.amazons3.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 310280812 on 4/4/2017.
 */

@Slf4j
@Component
public class RedisProbe implements Probe, Serializable {

    private static final String TYPE = "RedisChecker";

    private String name = "Redis";

    private boolean optional = false;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Autowired
    private JedisConnectionFactory redisData;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ProbeResult check() {
        RedisConnection conn = null;
        boolean status = false;
        double time = 0D;
        String error = null;
        Map<String, String> additionalInformation = new LinkedHashMap<>();

        additionalInformation.put("host", host);
        additionalInformation.put("port", port);

        log.debug("Checking the state of redis on host: " + host);

        try {
            long startTime = System.currentTimeMillis();
            conn = redisData.getConnection();
            conn.get("ping".getBytes());
            time = (System.currentTimeMillis() - startTime) / 1000D;
            status = true;
            log.debug("redis is ok for host " + host + "?" + status);
        } catch (RedisConnectionFailureException exception) {
            status = false;
            error = exception.getMessage();
            log.error("Error checking the redis for host: " + host, exception);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return new ProbeResult(this.name, TYPE, status, time, this.optional, additionalInformation, error);
    }
}