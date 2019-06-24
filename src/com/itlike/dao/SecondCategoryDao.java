package com.itlike.dao;

import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;

import java.sql.SQLException;
import java.util.List;

public interface SecondCategoryDao {
    Long getCount() throws SQLException;

    List<SecondCategory> selectAll(QueryVo vo) throws SQLException;

    void updateSecondCategory(SecondCategory secondCategory)throws SQLException;

    void addSecondCategory(SecondCategory secondCategory)throws SQLException;

    List<SecondCategory> AllSecondCategory()throws SQLException;
}
