package com.netmed.patientmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableJpaAuditing
@EnableSwagger2
@RefreshScope
public class PatientModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientModuleApplication.class, args);
    }

}
