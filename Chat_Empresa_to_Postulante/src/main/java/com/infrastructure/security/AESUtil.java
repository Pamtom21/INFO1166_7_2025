package com.infrastructure.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // 16 caracteres -> AES-128

    /**
     * Encripta un texto plano usando AES y devuelve Base64
     */
    public static String encriptar(String textoPlano) {
        if (textoPlano == null || textoPlano.isEmpty()) {
            throw new IllegalArgumentException("No se puede encriptar contenido nulo o vacío");
        }

        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(textoPlano.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            throw new RuntimeException("Error al encriptar el mensaje", e);
        }
    }

    /**
     * Desencripta un texto en Base64 usando AES
     */
    public static String desencriptar(String textoEncriptado) {
        if (textoEncriptado == null || textoEncriptado.isEmpty()) {
            throw new IllegalArgumentException("No se puede desencriptar contenido nulo o vacío");
        }

        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(textoEncriptado);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error al desencriptar: " + e.getMessage());
            throw new RuntimeException("Error al desencriptar el mensaje", e);
        }
    }
}
