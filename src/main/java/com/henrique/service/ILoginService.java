package com.henrique.service;

import com.henrique.controller.request.LoginRequest;
import com.henrique.controller.response.Sessao;

public interface ILoginService {

    Sessao checkUser(final LoginRequest loginRequest);

}
