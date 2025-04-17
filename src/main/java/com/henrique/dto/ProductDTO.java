package com.henrique.dto;

import com.henrique.enums.Categories;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Categories category;
    private Double price;
    private String desc;
}
