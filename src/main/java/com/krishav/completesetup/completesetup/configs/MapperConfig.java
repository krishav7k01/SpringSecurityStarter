package com.krishav.completesetup.completesetup.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModalMapper()
    {
        return new ModelMapper();
    }



}
