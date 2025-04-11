package com.henrique.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.config")
public class JWTConfig {
    private String KEY;
    private String PREFIX;
    private Long EXPIRATION;
}

