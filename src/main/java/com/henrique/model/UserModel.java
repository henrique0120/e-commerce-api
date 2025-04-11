package com.henrique.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private Number phone;

    private List<String> roles = new ArrayList<>();
}
