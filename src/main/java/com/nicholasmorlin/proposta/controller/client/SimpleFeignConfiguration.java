package com.nicholasmorlin.proposta.controller.client;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleFeignConfiguration {

    @Bean
    public Logger.Level simpleFeignLogger () {
        return Logger.Level.FULL;
    }
}
