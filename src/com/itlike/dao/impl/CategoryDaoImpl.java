package com.itlike.dao.impl;

import com.itlike.dao.CategoryDao;
import com.itlike.domain.Category;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Category> selectAll(QueryVo vo) throws SQLException {
        String sql = "select * from category limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Category.class), vo.getPage() - 1, vo.getRows());
    }

    @Override
    public Long getCount() throws SQLException {
        String sql = "select count(*) from category";
        return qr.query(sql, new ScalarHandler<>(1));
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        String sql = "update category set name=? where id=?";
        qr.update(sql, category.getName(), category.getId());
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        String sql = "insert into category(name) value(?)";
        qr.update(sql, category.getName());
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
        String sql = "delete from category where id=?";
        qr.update(sql, id);
    }

    @Override
    public List<Category> allCategory() throws SQLException {
        String sql = "select * from category";
        return qr.query(sql, new BeanListHandler<>(Category.class));
    }

    @Override
    public List<Category> getAllCategoryList() throws SQLException {
        String sql = "select * from category";
        List<Category> categories = qr.query(sql, new BeanListHandler<>(Category.class));
        for (Category category : categories) {
            String sql2 = "select * from secondcategory where cid=?";
            List<SecondCategory> secondCategories = qr.query(sql2, new BeanListHandler<>(SecondCategory.class), category.getId());
            category.setSecondCategorylist(secondCategories);
        }
        return categories;
    }
}
