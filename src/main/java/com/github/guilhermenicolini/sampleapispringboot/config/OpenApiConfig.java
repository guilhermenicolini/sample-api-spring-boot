package com.github.guilhermenicolini.sampleapispringboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .scheme("bearer")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")))
                .info(new Info().title("Sample API").version(appVersion).description(
                        "Documentation Sample API v1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
