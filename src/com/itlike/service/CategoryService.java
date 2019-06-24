package com.itlike.service;

import com.itlike.domain.Category;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    PageListRes getCategoryList(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    void updateCategory(Category category) throws SQLException;

    void addCategory(Category category) throws SQLException;

    void deleteCategory(int id) throws SQLException;

    List<Category> allCategory() throws SQLException;

    /*获取所有一级和二级菜单*/
    List<Category> getAllCategoryList() throws SQLException;
}
