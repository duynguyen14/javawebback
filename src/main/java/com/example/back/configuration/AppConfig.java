package com.example.back.configuration;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    @PostConstruct
    public void loadEnv() {
//        Dotenv dotenv = Dotenv.load();
//        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
//        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
    }

}
