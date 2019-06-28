package com.itlike.dao.impl;

import com.itlike.dao.MenuDao;
import com.itlike.domain.Admin;
import com.itlike.domain.Menu;
import com.itlike.domain.Permission;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Menu> getTreeData() throws SQLException {
        /*查询根菜单*/
        String sql = "select * from menu where parent_id is null";
        List<Menu> parentMenuList = qr.query(sql, new BeanListHandler<>(Menu.class));
        /*查询对应的子菜单*/
        for (Menu menu : parentMenuList) {
            String sql2="select * from menu where parent_id=?";
            List<Menu> childMenus = qr.query(sql2, new BeanListHandler<>(Menu.class), menu.getId());
            /*查询子菜单对应的权限*/
            for (Menu childMenu : childMenus) {
                String sql3="select * from permission where pid=?";
                Permission permission = qr.query(sql3, new BeanHandler<>(Permission.class), childMenu.getPermission_id());
                childMenu.setPermission(permission);
            }
            menu.setChildren(childMenus);
        }
        return parentMenuList;
    }
}
