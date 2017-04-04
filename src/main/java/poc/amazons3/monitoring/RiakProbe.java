package poc.amazons3.monitoring;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Created by 310280812 on 4/4/2017.
 */
public class RiakProbe extends AbstractDatabaseProbe {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public DataSource dataSource() {
        return null;
    }

    @Override
    public boolean optional() {
        return false;
    }
}
