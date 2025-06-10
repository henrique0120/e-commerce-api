package com.henrique.service.query.impl;

import com.henrique.exception.InvalidPasswordException;
import com.henrique.exception.UserDontFindException;
import com.henrique.mapper.ProductMapper;
import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.service.query.ILoginQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginQueryService implements ILoginQueryService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final ProductMapper productMapper;

    @Override
    public UserEntity verifyUser(final String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserDontFindException("Usuário não encontrado"));
    }

    @Override
    public void verifyPassword(final String rawPassword, final String hashedPassword, final String email) {
        boolean passwordOk = encoder.matches(rawPassword, hashedPassword);
        if (!passwordOk) {
            var message = "Senha inválida para o login: " + email;
            throw new InvalidPasswordException(message);
        }
    }
}
