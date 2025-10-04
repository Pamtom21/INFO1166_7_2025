package com.domain.model;

/**
 * Enum que representa los tipos de usuario dentro del sistema.
 */
public enum TipoUsuario {
    EMPRESA,
    POSTULANTE;

    /**
     * Convierte un String a TipoUsuario de forma segura.
     * 
     * @param tipo el tipo como String
     * @return TipoUsuario correspondiente
     * @throws IllegalArgumentException si el String no coincide con ningún TipoUsuario
     */
    public static TipoUsuario fromString(String tipo) {
        for (TipoUsuario t : TipoUsuario.values()) {
            if (t.name().equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("TipoUsuario desconocido: " + tipo);
    }
}
