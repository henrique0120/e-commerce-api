package com.henrique.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private int stock;
    private String desc;
}
