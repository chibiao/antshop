package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Admin {
    private Long id;
    /*管理员编号*/
    private String anum;

    private String name;

    private String password;

    private List<Role> roles =new ArrayList<>();

    private List<Permission> permissions = new ArrayList<>();
}