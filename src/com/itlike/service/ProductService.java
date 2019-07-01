package com.itlike.service;

import com.itlike.domain.*;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    PageListRes getProductList(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    void updateProduct(Product product)throws SQLException;

    void addProduct(Product product)throws SQLException;

    void upadateProductState(int id)throws SQLException;

    Product selectProductById(Long id)throws SQLException;

    PageBean getProductByCategory(int id, int page)throws SQLException;

    PageBean getProductBySecondCategory(int id, Integer page)throws SQLException;

    List<Product> getProductByCart(List<Cart> carts)throws SQLException;

    List<Product> getProductByUid(Long id) throws SQLException;

    List<Product> getHotProduct()throws SQLException;

    List<Product> getProductByName(String name)throws SQLException;
}
