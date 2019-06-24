package com.itlike.service.impl;

import com.itlike.dao.CartDao;
import com.itlike.dao.impl.CartDaoImpl;
import com.itlike.domain.Cart;
import com.itlike.service.CartService;

import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();

    @Override
    public void addToCart(Long id, List<Cart> cartList) throws SQLException {
        List<Cart> carts = cartDao.getCart(id);
        for (Cart cart : cartList) {
            if(cartDao.getCartByUidAndPid(id,cart.getId())==null){
                cartDao.insert(id,cart.getId(),cart.getCount());
            }
        }
    }

    @Override
    public List<Cart> getCartByUid(Long id) throws SQLException {
        return cartDao.getCartByUid(id);
    }

    @Override
    public void deleteCart(Long uid, Long pid) throws SQLException {
        cartDao.deleteCart(uid, pid);
    }

}
