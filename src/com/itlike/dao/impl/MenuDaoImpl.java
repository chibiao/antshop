package com.itlike.dao.impl;

import com.itlike.dao.MenuDao;
import com.itlike.domain.Admin;
import com.itlike.domain.Menu;
import com.itlike.domain.Permission;
import com.itlike.domain.QueryVo;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    @Override
    public Long getCount() throws SQLException {
        String sql="select count(*) from menu";
        return qr.query(sql,new ScalarHandler<>(1));

    }

    @Override
    public List<Menu> menuList(QueryVo vo) throws SQLException {
        String sql ="select * from menu limit ?,?";
        List<Menu> menus = qr.query(sql, new BeanListHandler<>(Menu.class), (vo.getPage() - 1) * vo.getRows(), vo.getRows());
        for (Menu menu : menus) {
            if(menu.getParent_id()!=null){
                String sql2="select * from menu where id=?";
                Menu query = qr.query(sql2, new BeanHandler<>(Menu.class), menu.getParent_id());
                menu.setParent(query);
            }
        }
        return menus;
    }

    @Override
    public List<Menu> parentMenuList() throws SQLException {
        String sql ="select * from menu";
        List<Menu> menus = qr.query(sql, new BeanListHandler<>(Menu.class));
        return menus;
    }

    @Override
    public void saveMenu(Menu menu) throws SQLException {
        String sql ="insert into menu(text,url,parent_id) value(?,?,?)";
        qr.update(sql,menu.getText(),menu.getUrl(),menu.getParent_id());
    }

    @Override
    public Menu selectParentId(Long id) throws SQLException {
        String sql="select * from  menu where id=?";
        return qr.query(sql,new BeanHandler<>(Menu.class),id);
    }

    @Override
    public void updateMenu(Menu menu) throws SQLException {
        String sql ="update menu set text=?,url=?,parent_id=? where id=?";
        qr.update(sql,menu.getText(),menu.getUrl(),menu.getParent_id(),menu.getId());
    }

    @Override
    public void updateMenuRel(long id) throws SQLException {
        String sql="update menu set parent_id = null  where parent_id = ?";
        qr.update(sql,id);
    }

    @Override
    public void deleteByPrimaryKey(long id) throws SQLException {
        String sql="delete from menu where id = ?";
        qr.update(sql,id);
    }
}
