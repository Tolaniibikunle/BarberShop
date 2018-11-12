package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.entity.AuthenticationIdentifier;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AuthenticationFactory {

    public AuthenticationIdentifier createNewAuthenticationId(String email, String password) {
        AuthenticationIdentifier authenticationIdentifier = new AuthenticationIdentifier();
        authenticationIdentifier.setEmailAddress(email);
        authenticationIdentifier.setGeneratedSalt(createSalt());
        authenticationIdentifier.setHashedPassword(hashPassword(authenticationIdentifier.getGeneratedSalt(), password));
        return authenticationIdentifier;
    }

    private String createSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPassword(String salt, String password) {
        String passwordToHash = salt + password;
        return Hashing.sha256().hashString(passwordToHash, StandardCharsets.UTF_8).toString();
    }

}
