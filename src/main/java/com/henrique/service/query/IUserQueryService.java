package com.henrique.service.query;

import com.henrique.model.UserEntity;

public interface IUserQueryService {

    void verifyEmail(final String email);

    UserEntity verifyUser(final String email);

    void verifyPassword(final String rawPassword, final String encodedPassword, final String email);

}
