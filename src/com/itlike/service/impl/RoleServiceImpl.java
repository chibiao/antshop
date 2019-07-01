package com.itlike.service.impl;

import com.itlike.dao.RoleDao;
import com.itlike.dao.impl.RoleDaoImpl;
import com.itlike.domain.*;
import com.itlike.service.RoleService;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();
    /*获取总记录数*/
    public Long getCount() throws SQLException {
        return roleDao.getCount();
    }
    /*保存角色*/
    @Override
    public AjaxRes saveRole(Role role) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*进行插入后回显插入数据的id*/
            BigInteger id=roleDao.insert(role);
            role.setRid(id.longValue());
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
            e.printStackTrace();
        }
        /*保存角色与权限之间的关系*/
        for (Permission permission : role.getPermissions()) {
            try {
                roleDao.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
            } catch (SQLException e) {
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("添加失败");
                e.printStackTrace();
            }
        }
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("添加成功");
        return ajaxRes;
    }
    /*更新角色*/
    @Override
    public AjaxRes updateRole(Role role) {
        AjaxRes ajaxRes = new AjaxRes();
        /*打破角色与权限之间的关系*/
        try {
            roleDao.deleteRoleAndPermissionRel(role.getRid());
        } catch (SQLException e) {
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
            e.printStackTrace();
        }
        /*更新角色*/
        try {
            roleDao.updateByPrimaryKey(role);
        } catch (SQLException e) {
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
            e.printStackTrace();
        }
        /*重新建立角色与权限之间的关系*/
        /*保存角色与权限之间的关系*/
        for (Permission permission : role.getPermissions()) {
            try {
                roleDao.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
            } catch (SQLException e) {
                ajaxRes.setMsg("修改失败");
                ajaxRes.setSuccess(false);
                e.printStackTrace();
            }
        }
        ajaxRes.setMsg("修改成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteRole(long rid) {
        AjaxRes ajaxRes = new AjaxRes();
        /*删除关系*/
        try {
            roleDao.deleteRoleAndPermissionRel(rid);
            roleDao.deleteRoleAndAdminRel(rid);
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            e.printStackTrace();
        }
        /*删除角色*/
        try {
            roleDao.deleteByPrimaryKey(rid);
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            e.printStackTrace();
        }
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("删除成功");
        return ajaxRes;
    }
    /*获取所有的角色列表*/
    @Override
    public List<Role> getRoleList() throws SQLException {
        return roleDao.getRoleList();
    }
    /*根据管理员的id查询角色*/
    @Override
    public List<Role> getRoleByAid(Long id) throws SQLException {
        return roleDao.getRoleByAid(id);
    }

    @Override
    public PageListRes getRoles(QueryVo vo) throws SQLException {
        List<Role> roles = roleDao.getRoles(vo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(getCount());
        pageListRes.setRows(roles);
        return pageListRes;
    }
}
