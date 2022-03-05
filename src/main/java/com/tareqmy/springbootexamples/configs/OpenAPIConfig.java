package com.tareqmy.springbootexamples.configs;

import com.tareqmy.springbootexamples.web.utils.SpringBootExamplesProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(SpringBootExamplesProperties springBootExamplesProperties) {
        return new OpenAPI()
            .info(new Info().title("SpringBootExamples API")
                .description("Spring boot example application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .components(new Components()
                .addSecuritySchemes(springBootExamplesProperties.getSecuritySchemeKey(),
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")))
            //to add in all api request
            .addSecurityItem(new SecurityRequirement().addList(springBootExamplesProperties.getSecuritySchemeKey()));
    }
}
