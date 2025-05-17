package com.henrique.controller;

import com.henrique.controller.request.SaveProductRequest;
import com.henrique.controller.request.SaveUserRequest;
import com.henrique.controller.response.SaveUserResponse;
import com.henrique.mapper.UserMapper;
import com.henrique.model.UserEntity;
import com.henrique.service.IUserService;
import com.henrique.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    private final UserMapper mapper;
    private final UserService userService;

    @PostMapping
    SaveUserResponse createUser(@RequestBody @Valid final SaveUserRequest request){
        var entity = mapper.toEntity(request);
        userService.createUser(entity);
        return mapper.toSaveResponse(entity);
    }
}