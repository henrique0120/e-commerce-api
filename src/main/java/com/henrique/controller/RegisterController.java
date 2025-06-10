package com.henrique.controller;

import com.henrique.controller.request.RegisterRequest;
import com.henrique.controller.response.RegisterResponse;
import com.henrique.mapper.UserMapper;
import com.henrique.service.IRegisterService;
import com.henrique.service.impl.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final IRegisterService service;
    private final UserMapper mapper;
    private final RegisterService userService;

    @PostMapping
    RegisterResponse createUser(@RequestBody @Valid final RegisterRequest request){
        var entity = mapper.toEntity(request);
        userService.createUser(entity);
        return mapper.toSaveResponse(entity);
    }
}