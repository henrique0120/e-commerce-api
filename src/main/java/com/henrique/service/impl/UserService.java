package com.henrique.service.impl;

import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.service.IUserService;
import com.henrique.service.query.IUserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final IUserQueryService service;
    private final PasswordEncoder encoder;

    @Override
    public void createUser(UserEntity user){
        service.verifyEmail(user.getEmail());
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }

}
