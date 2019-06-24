package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Category {
    public Integer id;
    public String name;
    List<SecondCategory> secondCategorylist = new ArrayList<>();
}
