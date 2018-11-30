package com.example.demoM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demoM.vo.UserInfoVO;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@Profile({ProfileNames.DEFAULT, ProfileNames.DEV})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASEPACKAGE = "com.viewhigh.epro.mall.api";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.ignoredParameterTypes(UserInfoVO.class)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASEPACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("epro-mall")
                .description("restful api")
                .termsOfServiceUrl("http://viewhigh.com/")
                .version("1.0")
                .build();
    }
}
