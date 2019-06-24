package com.itlike.dao;

import com.itlike.domain.Category;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    /*分页查询*/
    List<Category> selectAll(QueryVo vo) throws SQLException;

    /*获取总记录数*/
    Long getCount() throws SQLException;

    /*更新商品*/
    void updateCategory(Category category) throws SQLException;

    /*添加商品*/
    void addCategory(Category category) throws SQLException;

    /*删除商品*/
    void deleteCategory(int id) throws SQLException;

    /*获取所有商品*/
    List<Category> allCategory() throws SQLException;

    /*获取所有一级菜单和二级菜单*/
    List<Category> getAllCategoryList() throws SQLException;

}
