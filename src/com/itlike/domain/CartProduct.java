package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/*购物车中的商品信息*/
@Getter@Setter@ToString
public class CartProduct {
    private Long shopId;
    private String shopName;
    private BigDecimal shopPrice;
    private int shopNumber;
    private String shopImage;
}
