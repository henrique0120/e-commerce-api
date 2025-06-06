package com.henrique.mapper;

import com.henrique.controller.request.SaveUserRequest;
import com.henrique.controller.response.LoginResponse;
import com.henrique.controller.response.SaveProductResponse;
import com.henrique.controller.response.SaveUserResponse;
import com.henrique.dto.response.UserDTO;
import com.henrique.model.UserEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(UserEntity entity);

    SaveUserResponse toSaveResponse(final UserEntity entity);

    LoginResponse toLoginResponse(final UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(final @Valid SaveUserRequest request);
}
