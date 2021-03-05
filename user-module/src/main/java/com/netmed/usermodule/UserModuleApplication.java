package com.netmed.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * UserModuleApplication is the User Module of Netmed product
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableJpaAuditing
@EnableCaching
@RefreshScope
@EnableBinding(Source.class)
public class UserModuleApplication {

    /*
     * Entry Point of the User Module of Netmed product
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(UserModuleApplication.class, args);
    }

}
