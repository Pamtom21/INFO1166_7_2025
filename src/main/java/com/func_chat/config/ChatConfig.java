package com.func_chat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.chat")
@Getter
@Setter
public class ChatConfig {
    private int maxMessageLength = 1000;
    private String allowedSenderTypes = "COMPANY,PERSON";
    private boolean persistenceEnabled = true;
    
    // Configuración de WebSocket
    private WebSocket websocket = new WebSocket();
    
    // Configuración JWT
    private Jwt jwt = new Jwt();
    
    @Getter
    @Setter
    public static class WebSocket {
        private String endpoint = "/ws-chat";
        private String broker = "/topic";
        private String appPrefix = "/app";
    }
    
    @Getter
    @Setter
    public static class Jwt {
        private String secret = "miClaveSecretaSuperSeguraParaJWT2025Info1166";
        private long expiration = 86400000;
    }
}