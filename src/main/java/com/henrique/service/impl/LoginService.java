package com.henrique.service.impl;

import com.henrique.controller.request.LoginRequest;

import com.henrique.controller.response.LoginResponse;
import com.henrique.model.UserEntity;
import com.henrique.security.JWTCreator;
import com.henrique.security.JWTObject;
import com.henrique.service.ILoginService;
import com.henrique.service.query.ILoginQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final ILoginQueryService service;
    private final JWTCreator jwtCreator;

    @Override
    public LoginResponse checkUser(LoginRequest loginRequest) {
        UserEntity user = service.verifyUser(loginRequest.email());
        service.verifyPassword(loginRequest.password(), user.getPassword(), user.getEmail());

        JWTObject jwtObject = new JWTObject();
        jwtObject.setSubject(user.getEmail());
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + 600_000));
        jwtObject.setRoles(user.getRoles());

        LoginResponse sessao = new LoginResponse();
        sessao.setLogin(user.getEmail());
        sessao.setToken(jwtCreator.createToken(jwtObject));

        return sessao;
    }
}
