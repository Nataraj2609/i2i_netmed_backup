package com.netmed.usermodule.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UtilConfig provides beans for Utility classes
 *
 * @author Nataraj m
 * @created 07/02/20
 */
@Configuration
public class UserConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
