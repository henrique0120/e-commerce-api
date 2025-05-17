package com.henrique.service.query.impl;

import com.henrique.controller.response.SaveUserResponse;
import com.henrique.exception.EmailInUseException;
import com.henrique.mapper.ProductMapper;
import com.henrique.repository.UserRepository;
import com.henrique.service.query.IUserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQueryService implements IUserQueryService {

    private UserRepository repository;
    private ProductMapper productMapper;

    @Override
    public void verifyEmail(final String email){
        if(repository.existsByEmail(email)){
            var message = "O email " + email + " já está em uso";
            throw new EmailInUseException(message);
        }
    }

}
