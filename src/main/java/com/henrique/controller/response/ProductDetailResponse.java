package com.henrique.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.henrique.enums.Categories;

import java.math.BigDecimal;

public record ProductDetailResponse(

        @JsonProperty("id")
        Long id,
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
