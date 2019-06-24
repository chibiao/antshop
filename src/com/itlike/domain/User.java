package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class User {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String addr;

    private Boolean state;

}