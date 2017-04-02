package poc.amazons3.caching;

import java.lang.annotation.*;

/**
 * Created by antonioreuter on 02/04/17.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheExpires {
  long expires() default 300L;
}
