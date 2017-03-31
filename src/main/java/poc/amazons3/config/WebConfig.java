package poc.amazons3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import poc.amazons3.controllers.interceptors.LoggingRequestInterceptor;

/**
 * Created by 310280812 on 3/31/2017.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new LoggingRequestInterceptor()).addPathPatterns("/api/dataitems/**");
    }

}
