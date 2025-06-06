package com.henrique.mapper;

import com.henrique.controller.request.LoginRequest;
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

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(final @Valid SaveUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserEntity toEntityLogin(final @Valid LoginRequest request);
}
