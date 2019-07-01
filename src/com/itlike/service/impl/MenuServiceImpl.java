package com.itlike.service.impl;

import com.itlike.dao.MenuDao;
import com.itlike.dao.impl.MenuDaoImpl;
import com.itlike.domain.*;
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
    public Long getCount() throws SQLException {
        return menuDao.getCount();
    }
    @Override
    public List<Menu> getTreeData(Admin admin) throws SQLException {
        List<Menu> treeData = menuDao.getTreeData();
        List<Permission> permissions=adminService.getAllPermissionById(admin.getId());
        checkPermission(treeData,admin,permissions);
        return treeData;
    }

    @Override
    public PageListRes menuList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Menu> menus = menuDao.menuList(vo);
        pageListRes.setRows(menus);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }

    @Override
    public List<Menu> parentMenuList() throws SQLException {

        return menuDao.parentMenuList();
    }

    @Override
    public AjaxRes saveMenu(Menu menu)  {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuDao.saveMenu(menu);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        } catch (SQLException e) {
            e.printStackTrace();
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes=new AjaxRes();
        /*判断你选择的父菜单是不是你自己的子菜单*/
        /*先取出父级菜单的id*/
        Long id = menu.getParent_id();
        if (id!=null){
            /*查询出该id对应的menu*/
            Menu parent=null;
            try {
                parent= menuDao.selectParentId(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(menu.getId()==parent.getId()){
                ajaxRes.setMsg("不能设置自己的子菜单为父菜单");
                ajaxRes.setSuccess(false);
                return ajaxRes;
            }
            id=parent.getId();
        }
        try {
            menuDao.updateMenu(menu);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteMenu(long id)  {
        AjaxRes ajaxRes=new AjaxRes();
        try {
            /*1.打破菜单关系*/
            menuDao.updateMenuRel(id);
            /*2.删除记录*/
            menuDao.deleteByPrimaryKey(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
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
