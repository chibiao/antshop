package com.itlike.service;

import com.itlike.domain.Permission;

import java.sql.SQLException;
import java.util.List;

public interface PermissionService {
    List<Permission> getPermissions()throws SQLException;

    List<Permission> getPermissionByRid(long rid)throws SQLException;
}
