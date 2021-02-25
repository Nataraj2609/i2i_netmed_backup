package com.netmed.vitalmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * VitalModuleApplication is the Vital Module of Netmed product
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableCaching
@EnableFeignClients
@RefreshScope
@EnableJpaAuditing
public class VitalModuleApplication {


    /*
     * Entry Point of the Vital Module of Netmed product
     */
    public static void main(String[] args) {
        SpringApplication.run(VitalModuleApplication.class, args);
    }

}
