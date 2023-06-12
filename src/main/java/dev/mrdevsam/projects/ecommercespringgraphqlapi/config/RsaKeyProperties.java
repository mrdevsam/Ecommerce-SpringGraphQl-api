package dev.mrdevsam.projects.ecommercespringgraphqlapi.config;

import java.security.interfaces.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {}
// externalizing rsa keys
