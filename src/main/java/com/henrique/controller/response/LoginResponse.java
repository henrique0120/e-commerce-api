package com.henrique.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record LoginResponse (
        @JsonProperty("id")
        Long id,
        @JsonProperty("login")
        String login,
        @JsonProperty("token")
        String token
){
}
