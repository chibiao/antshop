package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class SecondCategory {
    public Integer id;
    public String name;
    public Integer cid;
    public Category parent; //父级菜单
}
