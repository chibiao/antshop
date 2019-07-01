package com.itlike.service.impl;

import com.itlike.dao.OrderDao;
import com.itlike.dao.ProductDao;
import com.itlike.dao.impl.OrderDaoImpl;
import com.itlike.dao.impl.ProductDaoImpl;
import com.itlike.domain.*;
import com.itlike.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    /*添加订单*/
    public Long getCount() throws SQLException {
        return orderDao.getCount();
    }
    /**
     * @param order 订单
     * @param user  用户
     * @throws SQLException
     */
    @Override
    public void addOrder(Orders order, User user) throws SQLException {
        orderDao.addOrder(order, user);
    }

    /**
     * 获取user的订单
     * @param id    user id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Orders> getOrderListByUser(Long id) throws SQLException {
        List<Orders> orders = orderDao.getOrderListByUser(id);
        for (Orders order : orders) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getUuid());
            for (OrderItem orderItem : orderItemList) {
                Product product = productDao.selectProductById(orderItem.getProductId());
                orderItem.setProduct(product);
            }
            order.setOrderItems(orderItemList);
        }
        return orders;
    }
    /*更新支付状态*/
    @Override
    public void updatePayState(String orderId) throws SQLException {
        orderDao.updatePayState(orderId);
    }

    @Override
    public Orders getMessage(String uuid) throws SQLException {
        return orderDao.getMessage(uuid);
    }

    @Override
    public void updateMessage(String uuid, String name, String phone, String addr) throws SQLException {
        orderDao.updateMessage(uuid, name, phone, addr);
    }

    @Override
    public PageListRes getAllOrders(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Orders> allOrders = orderDao.getAllOrders(vo);
        pageListRes.setRows(allOrders);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }

    @Override
    public void updateSendState(String uuid) throws SQLException {
        orderDao.updateSendState(uuid);
    }

    @Override
    public void deleteOrder(String uuid) {
        try {
            /*删除订单中的订单项*/
            orderDao.deleteOrderItem(uuid);
            /*删除订单*/
            orderDao.deleteOrder(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
