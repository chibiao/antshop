package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Admin {
    private Long id;
    /*管理员编号*/
    private String anum;

    private String name;

    private String password;
}