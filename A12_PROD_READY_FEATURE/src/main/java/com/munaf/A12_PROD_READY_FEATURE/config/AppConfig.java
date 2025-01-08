package com.munaf.A12_PROD_READY_FEATURE.config;

import com.munaf.A12_PROD_READY_FEATURE.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImpl();
    }


}