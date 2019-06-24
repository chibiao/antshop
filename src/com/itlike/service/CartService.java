package com.itlike.service;

import com.itlike.domain.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    void addToCart(Long id, List<Cart> cartList) throws SQLException;

    List<Cart> getCartByUid(Long id) throws SQLException;

    void deleteCart(Long uid,Long pid) throws SQLException;
}
