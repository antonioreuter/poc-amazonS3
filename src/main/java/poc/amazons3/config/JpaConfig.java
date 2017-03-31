package poc.amazons3.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 310280812 on 3/31/2017.
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"poc.amazons3"})
@EnableJpaRepositories(basePackages = {"poc.amazons3.repositories"})
@EnableTransactionManagement
public class JpaConfig {
}
