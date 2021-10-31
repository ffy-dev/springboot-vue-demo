package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author
 * @Date 2021/10/22 19:14
 * @Version 1.0
 */
@Configuration
@EnableSwagger2  //开启Swagger使用
public class SwaggerConfig {
    @Bean
    public Docket buildSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.example.springboot.Controller"))
                .build();
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                //扫描所有的包 可以扫描指定的包 .apis(RequestHandlerSelectors.basePackage("具体controller所在的包"))
//                .paths(PathSelectors.any())
//                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger接口文档")//标题
                .description("Swagger-接口文档")// API描述
                .version("1.0.0")//接口的版本
                .build();
    }

}
