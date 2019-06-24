package com.itlike.dao.impl;

import com.itlike.dao.SecondCategoryDao;
import com.itlike.domain.Category;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class SecondCategoryDaoImpl implements SecondCategoryDao {
    private QueryRunner qr=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public Long getCount() throws SQLException {
        String sql="select count(*) from secondcategory";
        return qr.query(sql,new ScalarHandler<>(1));
    }

    @Override
    public List<SecondCategory> selectAll(QueryVo vo) throws SQLException {
        String sql="select * from secondcategory limit ?,?";
        List<SecondCategory> secondCategories = qr.query(sql, new BeanListHandler<>(SecondCategory.class), vo.getPage() - 1, vo.getRows());
        for (SecondCategory secondCategory : secondCategories) {
            String sql2="select * from category where id=?";
            secondCategory.setParent(qr.query(sql2,new BeanHandler<>(Category.class),secondCategory.getCid()));
        }
        return secondCategories;
    }

    @Override
    public void updateSecondCategory(SecondCategory secondCategory) throws SQLException {
        String sql = "update secondcategory set name=?,cid=? where id=?";
        qr.update(sql,secondCategory.getName(),secondCategory.getCid(),secondCategory.getId());
    }

    @Override
    public void addSecondCategory(SecondCategory secondCategory) throws SQLException {
        String sql ="insert into secondcategory(name,cid) value(?,?)";
        qr.update(sql,secondCategory.getName(),secondCategory.getCid());
    }

    @Override
    public List<SecondCategory> AllSecondCategory() throws SQLException {
        String sql="select * from secondcategory";
        return qr.query(sql, new BeanListHandler<>(SecondCategory.class));
    }
}
