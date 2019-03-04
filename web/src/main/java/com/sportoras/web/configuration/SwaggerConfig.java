package com.sportoras.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    private final String ADMIN_ID = "admin";
    private final String ADMIN_SECRET = "111";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/products/**"))
                .build()
                .apiInfo(apiInfo("2.0"));
    }

    @Bean
    public Docket authTokenSecuredApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Admin")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(new ApiKey("Admin", ADMIN_ID, ADMIN_SECRET)))
                .securityContexts(Collections.singletonList(xAuthTokenSecurityContext()))
                .apiInfo(apiInfo("2.0"));
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfo(
                "REST DOCUMENTATION",
                "Manufacture of sports and playwear",
                version,
                "http://www.sportoras.com",
                new Contact("Loiko Svetlana", "http://sportoras.com/kontakty/", "infooraz@gmail.com"),
                "License of API", "http://localhost:8080/web_war/products", Collections.emptyList());
    }

    private SecurityContext xAuthTokenSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Admin", new AuthorizationScope[0])))
                .build();
    }
}