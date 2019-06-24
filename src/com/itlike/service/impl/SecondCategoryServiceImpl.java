package com.itlike.service.impl;

import com.itlike.dao.SecondCategoryDao;
import com.itlike.dao.impl.SecondCategoryDaoImpl;
import com.itlike.domain.Category;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.service.SecondCategoryService;

import java.sql.SQLException;
import java.util.List;

public class SecondCategoryServiceImpl implements SecondCategoryService {
    private SecondCategoryDao secondCategoryDao = new SecondCategoryDaoImpl();

    public Long getCount() throws SQLException {
        return secondCategoryDao.getCount();
    }

    @Override
    public PageListRes getCategoryList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<SecondCategory> secondCategories = secondCategoryDao.selectAll(vo);
        pageListRes.setRows(secondCategories);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }

    @Override
    public void updateSecondCategory(SecondCategory secondCategory) throws SQLException {
        secondCategoryDao.updateSecondCategory(secondCategory);
    }

    @Override
    public void addSecondCategory(SecondCategory secondCategory) throws SQLException {
        secondCategoryDao.addSecondCategory(secondCategory);
    }

    @Override
    public List<SecondCategory> AllSecondCategory() throws SQLException {
        return secondCategoryDao.AllSecondCategory();
    }
}
