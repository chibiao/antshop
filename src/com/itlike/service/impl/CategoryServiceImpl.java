package com.itlike.service.impl;

import com.itlike.dao.CategoryDao;
import com.itlike.dao.impl.CategoryDaoImpl;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Category;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private AjaxRes ajaxRes = new AjaxRes();
    /*获取总记录数*/
    @Override
    public Long getCount() throws SQLException {
        return categoryDao.getCount();
    }
    /*更新一级菜单*/
    @Override
    public void updateCategory(Category category) throws SQLException {
        categoryDao.updateCategory(category);
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        categoryDao.addCategory(category);
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
        categoryDao.deleteCategory(id);
    }

    @Override
    public List<Category> allCategory() throws SQLException {
        return categoryDao.allCategory();
    }

    /*获取所有一级和二级菜单*/
    @Override
    public List<Category> getAllCategoryList() throws SQLException {
        return categoryDao.getAllCategoryList();
    }

    /**
     * 获取一级分类列表  分页查询
     * @param vo 分页
     * @return 分类条数和分类列表
     * @throws SQLException
     */
    @Override
    public PageListRes getCategoryList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Category> categories = categoryDao.selectAll(vo);
        pageListRes.setRows(categories);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }
}
