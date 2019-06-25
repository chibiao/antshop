package com.itlike.dao.impl;

import com.itlike.dao.ProductDao;
import com.itlike.domain.Cart;
import com.itlike.domain.Product;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Product> getProductList(QueryVo vo) throws SQLException {
        List<Product> productList = null;
        if(vo.getKeyword()!=null&&!"".equals(vo.getKeyword())){
            String sql ="select * from product where name like ? limit ?,?";
            productList = qr.query(sql, new BeanListHandler<>(Product.class),"%"+vo.getKeyword()+"%", vo.getPage() - 1, vo.getRows());
            for (Product product : productList) {
                String sql2="select * from secondcategory where id=?";
                product.setSecondCategory(qr.query(sql2,new BeanHandler<>(SecondCategory.class),product.getScid()));
            }
        }else {
            String sql = "select * from product limit ?,?";
            productList = qr.query(sql, new BeanListHandler<>(Product.class), vo.getPage() - 1, vo.getRows());
            for (Product product : productList) {
                String sql2="select * from secondcategory where id=?";
                product.setSecondCategory(qr.query(sql2,new BeanHandler<>(SecondCategory.class),product.getScid()));
            }
        }
        return productList;
    }

    @Override
    public Long getCount() throws SQLException {
        String sql = "select count(*) from product";
        return qr.query(sql, new ScalarHandler<>(1));
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "update product set name=?,isHot=?,scid=?,marketPrice=?,shopPrice=?,description=?,image=?,onlinetime=?,state=? where id=?";
        qr.update(sql,product.getName(), product.getIsHot(), product.getScid(), product.getMarketPrice(), product.getShopPrice(), product.getDescription(), product.getImage(),product.getOnlinetime(), product.getState(), product.getId());
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql="insert into product(name,isHot,scid,marketPrice,shopPrice,description,image,onlinetime,state) value(?,?,?,?,?,?,?,?,?)";
        qr.update(sql,product.getName(), product.getIsHot(), product.getScid(), product.getMarketPrice(), product.getShopPrice(), product.getDescription(), product.getImage(),product.getOnlinetime(), product.getState());
    }

    @Override
    public void upadateProductState(int id) throws SQLException {
        String sql ="update product set state=false where id=?";
        qr.update(sql,id);
    }

    @Override
    public Product selectProductById(Long id) throws SQLException {
        String sql="select * from product where id=?";
        return qr.query(sql,new BeanHandler<>(Product.class),id);
    }

    @Override
    public List<Product> getProductByCategory(int id,int page) throws SQLException {
        String sql="select * from product where scid in (select id from secondcategory where cid=?) and state=true limit ?,8";
        return qr.query(sql,new BeanListHandler<>(Product.class),id,(page-1)*8);
    }

    @Override
    public Long getCountByCategory(int id) throws SQLException {
        String sql="select count(*) from product where scid in (select id from secondcategory where cid=?) and state=true";
        return qr.query(sql,new ScalarHandler<>(1),id);
    }

    @Override
    public Long getCountBySecondCategory(int id) throws SQLException {
        String sql ="select count(*) from product where scid=? and state=true";
        return qr.query(sql,new ScalarHandler<>(1),id);
    }

    @Override
    public List<Product> getProductByCart(List<Cart> carts) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where id=?";
        for (Cart cart : carts) {
            products.add(qr.query(sql,new BeanHandler<>(Product.class),cart.getId()));
        }
        return products;
    }

    @Override
    public List<Product> getProductByUid(Long id) throws SQLException {
        String sql="select * from product where id in (select id from cart where uid=?)";
        return qr.query(sql,new BeanListHandler<>(Product.class),id);

    }

    @Override
    public List<Product> getHotProduct() throws SQLException {
        String sql ="select * from product where isHot=true limit 0,10";
        return qr.query(sql,new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> getProductBySecondCategory(int id, Integer page) throws SQLException {
        String sql ="select * from product where scid=? and state=true limit ?,8";
        return qr.query(sql,new BeanListHandler<>(Product.class),id,page-1);

    }
}
