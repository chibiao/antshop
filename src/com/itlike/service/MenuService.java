package com.itlike.service;


import com.itlike.domain.*;

import java.sql.SQLException;
import java.util.List;

public interface MenuService {
    List<Menu> getTreeData(Admin admin) throws SQLException;

    PageListRes menuList(QueryVo vo)throws SQLException;

    List<Menu> parentMenuList()throws SQLException;

    AjaxRes saveMenu(Menu menu);

    AjaxRes updateMenu(Menu menu);

    AjaxRes deleteMenu(long id);
}
