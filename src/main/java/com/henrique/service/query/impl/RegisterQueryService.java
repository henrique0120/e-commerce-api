package com.henrique.service.query.impl;

import com.henrique.exception.EmailInUseException;
import com.henrique.repository.UserRepository;
import com.henrique.service.query.IRegisterQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterQueryService implements IRegisterQueryService {

    private final UserRepository repository;

    @Override
    public void verifyEmail(final String email){
        if(repository.existsByEmail(email)){
            var message = "O email " + email + " já está em uso";
            throw new EmailInUseException(message);
        }
    }





}
