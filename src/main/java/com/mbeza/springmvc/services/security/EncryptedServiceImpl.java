package com.mbeza.springmvc.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

public class EncryptedServiceImpl implements EncryptionService{

    private StrongPasswordEncryptor passwordEncryptor;

    @Autowired
    public void setPasswordEncryptor(StrongPasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }


    @Override
    public String encryptString(String input) {
        return passwordEncryptor.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
