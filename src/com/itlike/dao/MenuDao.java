package com.itlike.dao;

import com.itlike.domain.Admin;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Menu;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface MenuDao {
    List<Menu> getTreeData() throws SQLException;

    Long getCount() throws SQLException;

    List<Menu> menuList(QueryVo vo)throws SQLException;

    List<Menu> parentMenuList()throws SQLException;

    void saveMenu(Menu menu)throws SQLException;

    Menu selectParentId(Long id) throws SQLException;

    void updateMenu(Menu menu)throws SQLException;

    void updateMenuRel(long id)throws SQLException;

    void deleteByPrimaryKey(long id)throws SQLException;
}
