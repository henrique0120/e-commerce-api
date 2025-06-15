package com.henrique.service;

import com.henrique.controller.request.LoginRequest;
import com.henrique.controller.response.LoginResponse;

public interface ILoginService {

    LoginResponse checkUser(final LoginRequest loginRequest);

}
