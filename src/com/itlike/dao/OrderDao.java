package com.itlike.dao;

import com.itlike.domain.OrderItem;
import com.itlike.domain.Orders;
import com.itlike.domain.QueryVo;
import com.itlike.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void addOrder(Orders order, User user) throws SQLException;

    List<Orders> getOrderListByUser(Long id) throws SQLException;

    List<OrderItem> getOrderItemByOrderId(String orderId) throws SQLException;

    void updatePayState(String orderId)throws SQLException;

    Orders getMessage(String uuid)throws SQLException;

    void updateMessage(String uuid, String name, String phone, String addr)throws SQLException;

    List<Orders> getAllOrders(QueryVo vo) throws SQLException;

    void updateSendState(String uuid)throws SQLException;

    void deleteOrder(String uuid) throws SQLException;

    void deleteOrderItem(String uuid) throws SQLException;

    Long getCount()throws SQLException;

}
