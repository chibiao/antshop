package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Menu {
    private Long id;

    private String text;

    private String url;

    private Long parent_id;

    private Long permission_id;

    private Menu parent;

    private Permission permission;

    private List<Menu> children = new ArrayList<>();
}