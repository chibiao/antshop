package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    private Long id;

    private String name;

    private Boolean isHot;

    private Long scid;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private String image;

    private String onlinetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private Boolean state;

    private String description;

    private SecondCategory secondCategory;/*所属二级菜单*/

}