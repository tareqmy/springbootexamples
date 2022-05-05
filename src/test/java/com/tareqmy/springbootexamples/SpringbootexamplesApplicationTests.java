package com.tareqmy.springbootexamples;

import com.tareqmy.springbootexamples.web.utils.SpringBootExamplesProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//loads full application into context. good for integration testing
@SpringBootTest
class SpringbootexamplesApplicationTests {

    @Autowired
    private SpringBootExamplesProperties springBootExamplesProperties;

    @Test
    void contextLoads() {
        assertNotNull(springBootExamplesProperties.getJwtSecret());  // JUnit assertion
        assertThat(springBootExamplesProperties.getJwtSecret()).isNotNull();  // Fest assertion
    }

}
