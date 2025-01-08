package com.munaf.A12_PROD_READY_FEATURE.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        we get username after this steps :
//        Get Security Contexts
//        Get Authentications
//        Get The Principles
//        Get The Username
        return Optional.of("Munaf Badarpura");
    }
}
