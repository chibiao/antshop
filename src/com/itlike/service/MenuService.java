package com.itlike.service;


import com.itlike.domain.Admin;
import com.itlike.domain.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuService {
    List<Menu> getTreeData(Admin admin) throws SQLException;
}
