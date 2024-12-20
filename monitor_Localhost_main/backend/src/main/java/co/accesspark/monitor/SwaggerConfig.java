/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Bobsuarez
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("co.accesspark.monitor"))
//                .paths(PathSelectors.any())
//                .build();
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().paths(regex("/api/.*")).build();
    }

    @SuppressWarnings("deprecation")
    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Java Code Geeks").description("API reference guide for developers").termsOfServiceUrl("https://www.javacodegeeks.com/").contact("Batra, Yatin").version("1.0").build();
    }

}
