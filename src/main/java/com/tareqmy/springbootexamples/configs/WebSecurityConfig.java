package com.tareqmy.springbootexamples.configs;

import com.tareqmy.springbootexamples.web.filters.JWTTokenFilter;
import com.tareqmy.springbootexamples.web.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class SecurityConfigBasicAuth extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;

        private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        @Autowired
        public SecurityConfigBasicAuth(UserDetailsService userDetailsService,
                                       RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
            this.userDetailsService = userDetailsService;
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // authentication: http basic
            http.cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.userDetailsService(userDetailsService);

            // authorization: access control
            http.antMatcher("/api/actuator/**").authorizeRequests()
                .antMatchers("/api/actuator/**").access("hasRole('ADMIN')");
        }
    }

    @Configuration
    @Order(10)
    public static class SecurityConfigJsonWebToken extends WebSecurityConfigurerAdapter {

        private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        private final UserDetailsService userDetailsService;

        @Autowired
        public SecurityConfigJsonWebToken(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                          UserDetailsService userDetailsService) {
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // authentication: jwt
            http.cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.userDetailsService(userDetailsService);

            http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/openapi/**").permitAll()
                .antMatchers("/api/users/**").access("hasRole('ADMIN')")
                .antMatchers("/api/account/**").authenticated()
                .anyRequest().authenticated();

            http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        public JWTTokenFilter jwtTokenFilter() {
            return new JWTTokenFilter();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());
        encoders.put("bcrypt", bCryptPasswordEncoder);
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    //https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authz-hierarchical-roles
    @Bean
    public RoleVoter roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_SYSTEM_ADMIN > ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }
}

