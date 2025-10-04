package com.example.Chat_Empresa_to_Postulante.model;

public enum TipoUsuario {
    EMPRESA,
    POSTULANTE;

    /**
     * Convierte un String en TipoUsuario de forma segura.
     * @param value valor a convertir
     * @return TipoUsuario correspondiente
     * @throws IllegalArgumentException si el valor no es válido
     */
    public static TipoUsuario fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("El tipo de usuario no puede ser nulo.");
        }
        try {
            return TipoUsuario.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de usuario inválido. Debe ser EMPRESA o POSTULANTE.");
        }
    }
}
