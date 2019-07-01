package com.itlike.dao;

import com.itlike.domain.Cart;
import com.itlike.domain.Product;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> getProductList(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void addProduct(Product product) throws SQLException;

    void upadateProductState(int id) throws SQLException;

    Product selectProductById(Long id) throws SQLException;

    List<Product> getProductByCategory(int id, int page) throws SQLException;

    Long getCountByCategory(int id) throws SQLException;

    List<Product> getProductBySecondCategory(int id, Integer page) throws SQLException;

    Long getCountBySecondCategory(int id) throws SQLException;

    List<Product> getProductByCart(List<Cart> carts) throws SQLException;

    List<Product> getProductByUid(Long id) throws SQLException;

    List<Product> getHotProduct()throws SQLException;

    List<Product> getProductByName(String name)throws SQLException;
}
