package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter@Setter@ToString
public class OrderItem {
	//主键
	private int id;
	//商品数量
	private int count;
	//商品小计
	private BigDecimal subtotal;
	//商品的id
	private Long productId;
	//订单的id
	private String orderId;
	//商品
	private Product product;
}
