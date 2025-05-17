package com.henrique.dto.response;

import com.henrique.enums.Categories;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private BigDecimal price;
    private int stock;
    private Categories category;
    private String description;
}
