package com.tareqmy.springbootexamples;

import com.tareqmy.springbootexamples.configs.SbeConfigurations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SbeConfigurations.class})
public class SpringbootexamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootexamplesApplication.class, args);
    }

}
