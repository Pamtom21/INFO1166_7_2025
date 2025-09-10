package com.func_chat.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatUtil {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(FORMATTER);
    }
    
    public static String getTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        
        LocalDateTime now = LocalDateTime.now();
        long minutes = java.time.Duration.between(dateTime, now).toMinutes();
        
        if (minutes < 1) {
            return "Ahora mismo";
        } else if (minutes < 60) {
            return "Hace " + minutes + " min";
        } else if (minutes < 1440) {
            long hours = minutes / 60;
            return "Hace " + hours + " h";
        } else {
            long days = minutes / 1440;
            return "Hace " + days + " días";
        }
    }
    
    public static boolean isValidMessageContent(String content) {
        return content != null && !content.trim().isEmpty() && content.length() <= 1000;
    }
}