package com.netmed.usermodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * UtilConfig provides beans for Utility classes
 *
 * @author Nataraj m
 * @created 07/02/20
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.netmed.usermodule"))
                .build().apiInfo(getApiInfo());
    }

    /*Function returning Api Information Contact details - For ApiInfo in Swagger purpose */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Netmed User API documentation",
                "Ideas2It Sample Healthcare software",
                "1.0",
                "http://www.appsdeveloperblof.com/service.html",
                new Contact("Nataraj", "", "nataraj.manivannan@ideas2it.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

}
