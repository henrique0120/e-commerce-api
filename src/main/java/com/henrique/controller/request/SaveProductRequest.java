package com.henrique.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.henrique.enums.Categories;

import java.math.BigDecimal;

public record SaveProductRequest(

        @JsonProperty("name")
        String name,
        @JsonProperty("category")
        Categories category,
        @JsonProperty("price")
        BigDecimal price,
        @JsonProperty("stock")
        int stock,
        @JsonProperty("description")
        String description
) {
}
