package com.mbeza.springmvc.config;

import com.mbeza.springmvc.services.security.EncryptedServiceImpl;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfig {

    @Bean
    public StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }

    @Bean
    public EncryptedServiceImpl encryptedService(){
        EncryptedServiceImpl encryptedService = new EncryptedServiceImpl();
        return encryptedService;
    }

}
