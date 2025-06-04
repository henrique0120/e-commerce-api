package com.henrique.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse (
        @JsonProperty("id")
        Long id,
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
){
}
