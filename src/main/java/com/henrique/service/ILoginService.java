package com.henrique.service;

import com.henrique.controller.request.LoginRequest;
import com.henrique.dto.response.Sessao;

public interface ILoginService {

    Sessao checkUser(final LoginRequest loginRequest);
}
