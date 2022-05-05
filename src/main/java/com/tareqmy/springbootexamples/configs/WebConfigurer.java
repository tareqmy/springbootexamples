package com.tareqmy.springbootexamples.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by tareqmy at 5/5/22
 */
@Slf4j
@Configuration
public class WebConfigurer {

    private final SbeConfigurations sbeConfigurations;

    public WebConfigurer(SbeConfigurations sbeConfigurations) {
        this.sbeConfigurations = sbeConfigurations;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = sbeConfigurations.getCors();
        if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/api/openapi/**", config);
            source.registerCorsConfiguration("/api/actuator/**", config);
        }
        return new CorsFilter(source);
    }
}
