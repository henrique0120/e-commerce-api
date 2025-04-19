package com.henrique.controller;

import com.henrique.config.SecurityConfig;
import com.henrique.dto.response.Sessao;
import com.henrique.dto.response.UserDTO;
import com.henrique.model.UserEntity;
import com.henrique.repository.UserRepository;
import com.henrique.security.JWTObject;
import com.henrique.security.JWTCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final PasswordEncoder encoder;
    private final SecurityConfig securityConfig;
    private final UserRepository repository;
    private final JWTCreator jwtCreator;

    @PostMapping("/login")
    public Sessao logar(@RequestBody UserDTO userDTO){
        UserEntity user = repository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(user != null) {
            boolean passwordOk = encoder.matches(userDTO.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + userDTO.getUsername());
            }

            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setSubject(user.getUsername());
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + 600000));
            jwtObject.setRoles(user.getRoles());

            sessao.setToken(jwtCreator.createToken(jwtObject));

            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

}
