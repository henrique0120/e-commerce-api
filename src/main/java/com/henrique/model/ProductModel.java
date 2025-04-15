package com.henrique.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

    private Long id;
    private String name;
    private String type;
    private Number price;
    private int stock;
    private String desc;

}
