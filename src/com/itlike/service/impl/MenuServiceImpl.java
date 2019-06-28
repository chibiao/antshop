package com.itlike.service.impl;

import com.itlike.dao.MenuDao;
import com.itlike.dao.impl.MenuDaoImpl;
import com.itlike.domain.Admin;
import com.itlike.domain.Menu;
import com.itlike.domain.Permission;
import com.itlike.service.AdminService;
import com.itlike.service.MenuService;

import javax.security.auth.Subject;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao = new MenuDaoImpl();
    private AdminService adminService =new AdminServiceImpl();
    /*获取菜单*/
    @Override
    public List<Menu> getTreeData(Admin admin) throws SQLException {
        List<Menu> treeData = menuDao.getTreeData();
        List<Permission> permissions=adminService.getAllPermissionById(admin.getId());
        checkPermission(treeData,admin,permissions);
        return treeData;
    }
    /*根据权限获取菜单   没有权限的菜单  移除*/
    private void checkPermission(List<Menu> menus,Admin admin,List<Permission> permissions){
        //遍历所有的菜单及子菜单
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            if (menu.getPermission() !=null){
                //判断当前menu是否有权限对象,如果说没有 当前遍历的菜单从集合当中移除
                Long permission_id = menu.getPermission_id();
                /*根据用户id查询出所有权限*/
                /*用于判断是否有权限*/
                boolean flag = true;
                for (Permission permission : permissions) {
                    if (permission.getPid().intValue()==permission_id.intValue()){
                        flag=false;
                        break;
                    }
                }
                if (flag){
                    //当前遍历的菜单从集合当中移除
                    iterator.remove();
                    continue;
                }
            }
            /*判断是否有子菜单  有子菜单也要做权限检验*/
            if (menu.getChildren().size() > 0){
                checkPermission(menu.getChildren(),admin,permissions);
            }
        }
    }
}
