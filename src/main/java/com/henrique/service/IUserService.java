package com.henrique.service;

import com.henrique.controller.request.LoginRequest;
import com.henrique.dto.response.Sessao;
import com.henrique.model.UserEntity;


public interface IUserService {

   void createUser(final UserEntity entity);

   Sessao checkUser(final LoginRequest loginRequest);
}
