package com.itlike.dao.impl;

import com.itlike.dao.CartDao;
import com.itlike.domain.Cart;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public void insert(Long uid, int pid, int count) throws SQLException {
        String sql = "insert into cart(uid,id,count) value(?,?,?)";
        qr.update(sql,uid,pid,count);
    }

    @Override
    public List<Cart> getCart(Long id) throws SQLException {
        String sql="select * from cart where uid=?";
        return qr.query(sql,new BeanListHandler<>(Cart.class),id);
    }

    @Override
    public List<Cart> getCartByUid(Long id) throws SQLException {
        String sql ="select * from cart where uid=?";
        return qr.query(sql,new BeanListHandler<>(Cart.class),id);
    }

    @Override
    public void deleteCart(Long uid, Long pid) throws SQLException {
        String sql ="delete from cart where uid=? and id=?";
        qr.update(sql,uid,pid);
    }

    @Override
    public Cart getCartByUidAndPid(Long id, int pid) throws SQLException {
        String sql ="select * from cart where uid=? and id=?";
        return qr.query(sql,new BeanHandler<>(Cart.class),id,pid);
    }
}
