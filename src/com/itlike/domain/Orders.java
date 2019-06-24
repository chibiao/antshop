package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter@Setter@ToString
public class Orders {
	//订单号
	private String uuid;
	//收货人的姓名
	private String name;
	//收货人电话
	private String phone;
	//收货人地址
	private String addr;
	//账户id
	private Long uid;
	//总价格
	private BigDecimal totalPrice;
	//下单时间
	private String ordertime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	 /*订单的付款状态*/
    private Boolean payState;
    private Boolean sendState;
	/*订单中的订单项*/
	private List<OrderItem> orderItems=new ArrayList<>();
}
