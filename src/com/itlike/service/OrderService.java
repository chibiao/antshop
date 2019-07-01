package com.itlike.service;

import com.itlike.domain.Orders;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void addOrder(Orders order, User user) throws SQLException;

    List<Orders> getOrderListByUser(Long id) throws SQLException;

    void updatePayState(String orderId)throws SQLException;

    Orders getMessage(String uuid)throws SQLException;

    void updateMessage(String uuid, String name, String phone, String addr)throws SQLException;

    PageListRes getAllOrders(QueryVo vo)throws SQLException;

    void updateSendState(String uuid)throws SQLException;

    void deleteOrder(String uuid);
}
