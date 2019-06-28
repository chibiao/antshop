package com.itlike.service.impl;

import com.itlike.dao.PermissionDao;
import com.itlike.dao.impl.PermissionDaoImpl;
import com.itlike.domain.Permission;
import com.itlike.service.PermissionService;

import java.sql.SQLException;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao = new PermissionDaoImpl();
    /*查询所有权限*/
    @Override
    public List<Permission> getPermissions() throws SQLException {
        return permissionDao.selectAll();
    }
    /*获取权限通过角色*/
    @Override
    public List<Permission> getPermissionByRid(long rid) throws SQLException {
        return permissionDao.getPermissionByRid(rid);
    }
}
