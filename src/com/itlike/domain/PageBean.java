package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter@Setter@ToString
public class PageBean {
    // 当前是那一页
    private Integer pageNo;
    // 共多少页
    private Integer totalPage;
    // 多少条记录
    private Integer totalSize;
    // 当前商品
    private List<Product> products = new ArrayList<>();
}
