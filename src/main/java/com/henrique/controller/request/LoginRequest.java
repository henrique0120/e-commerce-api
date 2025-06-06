package com.henrique.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record LoginRequest (
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
) {}