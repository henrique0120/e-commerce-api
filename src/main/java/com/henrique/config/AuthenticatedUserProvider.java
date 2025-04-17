package com.henrique.config;

import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.security.JWTCreator;
import com.henrique.security.JWTObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    private final JWTCreator jwtCreator;
    private final UserRepository userRepository;

    public AuthenticatedUserProvider(JWTCreator jwtCreator, UserRepository userRepository) {
        this.jwtCreator = jwtCreator;
        this.userRepository = userRepository;
    }

    public UserEntity getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT não encontrado");
        }

        String token = authHeader.substring(7);
        JWTObject jwt = jwtCreator.parseToken(token);
        String username = jwt.getSubject();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }
}

