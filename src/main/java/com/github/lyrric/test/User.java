package com.github.lyrric.test;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private Boolean enabled;
}
