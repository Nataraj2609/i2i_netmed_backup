package com.netmed.patientmodule.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Configuration class for RabbitMq middleware - Setting JsonMessageConverter(Serializer)
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "new_user_notify_queue";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
