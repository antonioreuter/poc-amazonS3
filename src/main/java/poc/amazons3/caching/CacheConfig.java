package poc.amazons3.caching;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by antonioreuter on 02/04/17.
 */
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

  @Value("${redis.host:127.0.0.1}")
  private String host;

  @Value("${redis.port:6379}")
  private Integer port;

  @Value("${redis.database:0}")
  private Integer database;

  @Value("${redis.pool.maxConn:30}")
  private Integer poolMaxConn;

  @Value("${redis.pool.maxIdle:10}")
  private Integer poolMaxIdle;

  @Value("${redis.defaultExpires:300}")
  private Integer defaultExpires;

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(poolMaxConn);
    poolConfig.setMaxIdle(poolMaxIdle);

    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName(host);
    connectionFactory.setPort(port);
    connectionFactory.setDatabase(database);
    connectionFactory.setUsePool(true);
    connectionFactory.setPoolConfig(poolConfig);

    return connectionFactory;
  }

  @Bean
  public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(cf);

    return redisTemplate;
  }

  @Bean
  public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setDefaultExpiration(defaultExpires);

    return cacheManager;
  }
}
