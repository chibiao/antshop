package com.itlike.dao.impl;

import com.itlike.dao.OrderDao;
import com.itlike.domain.OrderItem;
import com.itlike.domain.Orders;
import com.itlike.domain.User;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public void addOrder(Orders order, User user) throws SQLException {
        String sql = "insert into orders value(?,?,?,?,?,?,?,?,?)";
        qr.update(sql,order.getUuid(),user.getName(),user.getPhone(),user.getAddr(),user.getId(),order.getTotalPrice(),order.getOrdertime(),order.getPayState(),order.getSendState());
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            String sql2="insert into orderItem(count,subtotal,productId,orderId) value(?,?,?,?)";
            qr.update(sql2,orderItem.getCount(),orderItem.getSubtotal(),orderItem.getProductId(),order.getUuid());
        }
    }

    @Override
    public List<Orders> getOrderListByUser(Long id) throws SQLException {
        String sql="select * from orders where uid=?";
        return qr.query(sql,new BeanListHandler<>(Orders.class),id);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(String orderId) throws SQLException {
        String sql ="select * from orderitem where orderId=?";
        return qr.query(sql,new BeanListHandler<>(OrderItem.class),orderId);
    }

    @Override
    public void updatePayState(String orderId) throws SQLException {
        String sql ="update orders set payState=true where uuid=?";
        qr.update(sql,orderId);
    }

    @Override
    public Orders getMessage(String uuid) throws SQLException {
        String sql = "select * from orders where uuid=?";
        return qr.query(sql,new BeanHandler<>(Orders.class),uuid);
    }

    @Override
    public void updateMessage(String uuid, String name, String phone, String addr) throws SQLException {
        String sql="update orders set name=?,phone=?,addr=? where uuid=?";
        qr.update(sql,name,phone,addr,uuid);
    }

    @Override
    public List<Orders> getAllOrders() throws SQLException {
        String sql="select * from orders";
        return qr.query(sql,new BeanListHandler<>(Orders.class));
    }

    @Override
    public void updateSendState(String uuid) throws SQLException {
        String sql ="update orders set sendState=true where uuid=?";
        qr.update(sql,uuid);
    }
}
