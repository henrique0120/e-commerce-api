package com.henrique;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
		String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Chave gerada (Base64): " + base64Key);
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
