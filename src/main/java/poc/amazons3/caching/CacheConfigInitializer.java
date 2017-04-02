package poc.amazons3.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by antonioreuter on 02/04/17.
 */
@Slf4j
@Component
public class CacheConfigInitializer implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private RedisCacheManager redisCacheManager;

  @Value("${redis.defaultExpires:300}")
  private Long defaultExpires;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    try {
      CacheExpiresConfiguration.configureTtlWithReflection(redisCacheManager, defaultExpires);
    } catch (NoSuchBeanDefinitionException nsbdex) {
      String errorMessage = "Could not configure redis cache manager due to some misconfiguration. Caching will be ignored.";
      log.debug(errorMessage, nsbdex);
      log.warn(errorMessage);
    }
  }
}