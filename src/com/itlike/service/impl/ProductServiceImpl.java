package com.itlike.service.impl;

import com.itlike.dao.ProductDao;
import com.itlike.dao.impl.ProductDaoImpl;
import com.itlike.domain.*;
import com.itlike.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    public Long getCount() throws SQLException {
        return productDao.getCount();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productDao.updateProduct(product);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        productDao.addProduct(product);
    }

    @Override
    public void upadateProductState(int id) throws SQLException {
        productDao.upadateProductState(id);
    }

    @Override
    public Product selectProductById(Long id) throws SQLException {
        return productDao.selectProductById(id);
    }

    @Override
    public PageBean getProductByCategory(int id,int page) throws SQLException {
        List<Product> productByCategory = productDao.getProductByCategory(id,page);
        Long countByCategory = productDao.getCountByCategory(id);
        PageBean pageBean = new PageBean();
        pageBean.setProducts(productByCategory);
        pageBean.setTotalSize(countByCategory.intValue());
        return pageBean;
    }

    @Override
    public PageBean getProductBySecondCategory(int id, Integer page) throws SQLException {
        List<Product> productByCategory = productDao.getProductBySecondCategory(id,page);
        Long countByCategory = productDao.getCountBySecondCategory(id);
        PageBean pageBean = new PageBean();
        pageBean.setProducts(productByCategory);
        pageBean.setTotalSize(countByCategory.intValue());
        return pageBean;
    }

    @Override
    public List<Product> getProductByCart(List<Cart> carts) throws SQLException {
        return productDao.getProductByCart(carts);
    }

    @Override
    public List<Product> getProductByUid(Long id) throws SQLException {
        return productDao.getProductByUid(id);
    }

    @Override
    public List<Product> getHotProduct() throws SQLException {
        return productDao.getHotProduct();
    }

    @Override
    public PageListRes getProductList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Product> products = productDao.getProductList(vo);
        pageListRes.setRows(products);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }
}
