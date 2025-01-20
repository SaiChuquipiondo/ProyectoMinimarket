package com.example.demo.config;

import javax.crypto.SecretKey;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AESPasswordEncoder implements PasswordEncoder {

    private final AESUtil aesUtil;
    private final SecretKey secretKey;

    public AESPasswordEncoder() {
        this.aesUtil = new AESUtil();
        this.secretKey = aesUtil.obtenerClaveFija(); // Generar clave fija (reemplazar con una más segura en producción)
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return aesUtil.encriptar(rawPassword.toString(), secretKey);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            String decryptedPassword = aesUtil.desencriptar(encodedPassword, secretKey);
            return rawPassword.toString().equals(decryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar la contraseña", e);
        }
    }
}