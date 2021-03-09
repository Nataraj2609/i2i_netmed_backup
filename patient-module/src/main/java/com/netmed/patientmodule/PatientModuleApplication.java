package com.netmed.patientmodule;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * PatientModuleApplication is the patient Module of Netmed product
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableJpaAuditing
@EnableSwagger2
@RefreshScope
@EnableBinding(Sink.class)
public class PatientModuleApplication {

    /*
     * Entry Point of the Patient Module of Netmed product
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(PatientModuleApplication.class, args);
    }

}
