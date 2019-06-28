package com.itlike.dao;

import com.itlike.domain.Admin;
import com.itlike.domain.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDao {
    List<Menu> getTreeData() throws SQLException;
}
