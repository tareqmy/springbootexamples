package com.tareqmy.springbootexamples.web.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "springbootexamples.app")
public class SpringBootExamplesProperties {

    private static String jwtSecret;

    private Long jwtExpirationMs;

    private String securitySchemeKey;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Long getJwtExpirationMs() {
        return jwtExpirationMs;
    }

    public void setJwtExpirationMs(Long jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String getSecuritySchemeKey() {
        return securitySchemeKey;
    }

    public void setSecuritySchemeKey(String securitySchemeKey) {
        this.securitySchemeKey = securitySchemeKey;
    }
}
