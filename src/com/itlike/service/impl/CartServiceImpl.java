package com.itlike.service.impl;

import com.itlike.dao.CartDao;
import com.itlike.dao.impl.CartDaoImpl;
import com.itlike.domain.Cart;
import com.itlike.service.CartService;

import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    /*添加到购物车*/
    @Override
    public void addToCart(Long id, List<Cart> cartList) throws SQLException {
        List<Cart> carts = cartDao.getCart(id);
        for (Cart cart : cartList) {
            cartDao.insert(id, cart.getId(), cart.getCount());
        }
    }
    /*根据user的id获取所有的cart信息*/
    @Override
    public List<Cart> getCartByUid(Long id) throws SQLException {
        return cartDao.getCartByUid(id);
    }
    /*删除购物车中的商品*/

    /**
     *
     * @param uid 用户id
     * @param pid 商品id
     * @throws SQLException
     */
    @Override
    public void deleteCart(Long uid, Long pid) throws SQLException {
        cartDao.deleteCart(uid, pid);
    }

}
