package com.example.mockblog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtConfig {
    private String secret;
    private Long expiration;
    private String prefix;
}
