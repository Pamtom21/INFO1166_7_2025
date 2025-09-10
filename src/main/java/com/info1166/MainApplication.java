package com.info1166;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.login",
    "com.func_chat",
    "com.info1166"
})
@EntityScan({
    "com.login.model",
    "com.func_chat.model"
})
@EnableJpaRepositories({
    "com.login.repository", 
    "com.func_chat.repository"
})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
