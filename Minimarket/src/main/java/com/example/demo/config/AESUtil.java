package com.example.demo.config;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";

    // Encriptar
    public String encriptar(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Desencriptar
    public String desencriptar(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] originalData = cipher.doFinal(decodedData);
        return new String(originalData);
    }

    // Generar una clave fija (o clave fija para desarrollo, reemplazar con clave
    // segura)
    public SecretKey obtenerClaveFija() {
        byte[] key = "1234567890123456".getBytes(); // Clave de 16 bytes
        return new SecretKeySpec(key, ALGORITHM);
    }
}
