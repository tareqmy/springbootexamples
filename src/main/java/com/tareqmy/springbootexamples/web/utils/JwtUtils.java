package com.tareqmy.springbootexamples.web.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpringBootExamplesProperties springBootExamplesProperties;

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
            .setSubject((authentication.getName()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + springBootExamplesProperties.getJwtExpirationMs()))
            .signWith(SignatureAlgorithm.HS512, springBootExamplesProperties.getJwtSecret())
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
            .setSigningKey(springBootExamplesProperties.getJwtSecret())
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(springBootExamplesProperties.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
