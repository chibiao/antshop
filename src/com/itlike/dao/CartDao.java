package com.itlike.dao;

import com.itlike.domain.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    void insert(Long uid, int pid, int count) throws SQLException;

    List<Cart> getCart(Long id) throws SQLException;

    List<Cart> getCartByUid(Long id) throws SQLException;

    void deleteCart(Long uid, Long pid) throws SQLException;

    Cart getCartByUidAndPid(Long id, int pid)throws SQLException;
}
