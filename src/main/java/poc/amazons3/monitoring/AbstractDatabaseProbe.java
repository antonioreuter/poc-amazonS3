package poc.amazons3.monitoring;

/**
 * Created by 310280812 on 4/4/2017.
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractDatabaseProbe implements Probe {

    private static final String TYPE = "DatabaseChecker";

    abstract public DataSource dataSource();

    abstract public boolean optional();

    @Override
    public ProbeResult check() {
        DataSource dataSource = dataSource();
        Connection conn = null;
        boolean status = false;
        double time = 0D;
        String error = "";

        Map<String, String> additionalInformation = new LinkedHashMap<>();

        log.debug("Checking the state of db on host: " + dataSource.getUrl());

        try {
            long startTime = System.currentTimeMillis();
            conn = dataSource.getConnection();
            additionalInformation.put("connectionProperties", dataSource.getConnectionProperties());
            additionalInformation.put("validationQuery", dataSource.getValidationQuery());
            additionalInformation.put("className", dataSource.getDriverClassName());
            additionalInformation.put("user", dataSource.getUsername());
            additionalInformation.put("url", dataSource.getUrl());

            time = (System.currentTimeMillis() - startTime) / 1000D;

            status = true;
        } catch (SQLException exception) {
            status = false;
            error = exception.getMessage();
            log.error("Error when we tried to retrieve a connection from pool", exception);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException exception) {
                    error = exception.getMessage();
                    log.error("Something went wrong when tried to close the db connection", exception);
                }
            }
        }

        return new ProbeResult(this.getName(), TYPE, status, time, this.optional(), additionalInformation, error);
    }
}