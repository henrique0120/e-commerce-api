package com.henrique.service.impl;

import com.henrique.controller.request.LoginRequest;
import com.henrique.dto.response.Sessao;
import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.security.JWTCreator;
import com.henrique.security.JWTObject;
import com.henrique.service.IUserService;
import com.henrique.service.query.IUserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final IUserQueryService service;
    private final PasswordEncoder encoder;
    private final JWTCreator jwtCreator;

    @Override
    public void createUser(UserEntity user){
        service.verifyEmail(user.getEmail());
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }

    @Override
    public Sessao checkUser(LoginRequest loginRequest) {
        UserEntity user = service.verifyUser(loginRequest.email());
        service.verifyPassword(loginRequest.password(), user.getPassword(), user.getEmail());

        JWTObject jwtObject = new JWTObject();
        jwtObject.setSubject(user.getEmail());
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + 600_000));
        jwtObject.setRoles(user.getRoles());

        Sessao sessao = new Sessao();
        sessao.setLogin(user.getEmail());
        sessao.setToken(jwtCreator.createToken(jwtObject));

        return sessao;
    }


}
