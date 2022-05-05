package com.tareqmy.springbootexamples.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Created by tareqmy at 5/5/22
 */
@ConfigurationProperties(prefix = "springbootexamples")
public class SbeConfigurations {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return cors;
    }
}
