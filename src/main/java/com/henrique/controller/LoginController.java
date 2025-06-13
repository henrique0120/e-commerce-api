package com.henrique.controller;

import com.henrique.config.SecurityConfig;
import com.henrique.controller.request.LoginRequest;
import com.henrique.controller.response.Sessao;
import com.henrique.mapper.UserMapper;
import com.henrique.repository.UserRepository;
import com.henrique.security.JWTCreator;
import com.henrique.service.impl.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final PasswordEncoder encoder;
    private final SecurityConfig securityConfig;
    private final UserMapper mapper;
    private final UserRepository repository;
    private final LoginService service;
    private final JWTCreator jwtCreator;

    @PostMapping("/login")
    public Sessao logar(@RequestBody @Valid final LoginRequest request) {
        return service.checkUser(request);
    }

}
