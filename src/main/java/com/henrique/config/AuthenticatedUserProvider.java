package com.henrique.config;

import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.security.JWTCreator;
import com.henrique.security.JWTObject;
import com.henrique.service.query.impl.LoginQueryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthenticatedUserProvider {

    private final JWTCreator jwtCreator;
    private final UserRepository userRepository;
    private final LoginQueryService service;

    public UserEntity getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT não encontrado");
        }

        String token = authHeader.substring(7);
        JWTObject jwt = jwtCreator.parseToken(token);
        String email = jwt.getSubject();

        return service.verifyUser(email);

    }
}

