package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.OrderItem;
import com.itlike.domain.Orders;
import com.itlike.domain.User;
import com.itlike.service.CartService;
import com.itlike.service.OrderService;
import com.itlike.service.impl.CartServiceImpl;
import com.itlike.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /*进入订单页前判断是否登录*/
    public void orderIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user != null) {
            ajaxRes.setSuccess(true);
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } else {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("请先登录");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

    public String orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            List<Orders> orders=orderService.getOrderListByUser(user.getId());
            request.setAttribute("orders",orders);
            return "order.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void submitOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        String orders = request.getParameter("orders");
        User user = (User) request.getSession().getAttribute("user");
        /*解析复杂类型的json数据*/
        JSONObject jsonObject = JSON.parseObject(orders);
        System.out.println(jsonObject);
        String totalPrice = jsonObject.getString("totalPrice");
        JSONArray orderItems = jsonObject.getJSONArray("orderItems");
        Orders order = new Orders();
        order.setTotalPrice(BigDecimal.valueOf(Long.parseLong(totalPrice)));
        List<OrderItem> orderItemList = JSON.parseArray(orderItems.toJSONString(), OrderItem.class);
        order.setOrderItems(orderItemList);
        order.setUuid(UUID.randomUUID().toString().replace("-", ""));
        order.setPayState(false);
        order.setSendState(false);
        try {
            List<OrderItem> orderItems1 = order.getOrderItems();
            for (OrderItem orderItem : orderItems1) {
                cartService.deleteCart(user.getId(), orderItem.getProductId());
            }
            orderService.addOrder(order, user);
            ajaxRes.setMsg("下单成功");
            ajaxRes.setSuccess(true);
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String  updatePayState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        try {
            orderService.updatePayState(orderId);
            return "/orderServlet?action=orderList";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void getMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        try {
            Orders orders=orderService.getMessage(uuid);
            response.getWriter().print(JSON.toJSONString(orders));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("addr");
        try {
            orderService.updateMessage(uuid,name,phone,addr);
            ajaxRes.setMsg("修改成功");
            ajaxRes.setSuccess(true);
        } catch (SQLException e) {
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
        }
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }
}