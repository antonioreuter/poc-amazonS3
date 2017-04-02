package poc.amazons3.caching;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by antonioreuter on 02/04/17.
 */
@Slf4j
public class CacheExpiresConfiguration {
  public static void configureTtlWithReflection(RedisCacheManager redisCacheManager, Long defaultExpires) {
    Set<Method> annotatedMethods = retriveAnnotatedMethods("poc.amazons3", CacheExpires.class);
    Map<String, Long> map = defineCacheTtl(annotatedMethods, defaultExpires);

    redisCacheManager.setExpires(map);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private static Set<Method> retriveAnnotatedMethods(String packageUrl, Class annotatedClass) {
    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .setUrls(ClasspathHelper.forPackage(packageUrl))
        .setScanners(new MethodAnnotationsScanner()));

    Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(annotatedClass);

    return annotatedMethods;
  }

  private static Map<String, Long> defineCacheTtl(Set<Method> annotatedMethods, Long defaultExpires) {
    Map<String, Long> map = new HashMap<String, Long>();

    if (CollectionUtils.isNotEmpty(annotatedMethods)) {
      for (Method method : annotatedMethods) {
        log.debug("Inspect method {}", method);

        if (method.isAnnotationPresent(Cacheable.class) && method.isAnnotationPresent(CacheExpires.class)) {
          String cacheName = null;
          Cacheable cacheable = method.getAnnotation(Cacheable.class);
          if (cacheable != null) {
            cacheName = cacheable.value()[0];
            log.debug("Cacheable annotation name: {}", cacheName);
          }

          Long ttl = defaultExpires;
          CacheExpires cacheExpires = method.getAnnotation(CacheExpires.class);
          if (cacheExpires != null) {
            ttl = cacheExpires.expires();
            log.debug("CacheExpires annotation ttl: {}", ttl);
          }

          if (StringUtils.isNotBlank(cacheName)) {
            map.put(cacheName, ttl);
          }
        }
      }
    }

    return map;
  }
}
