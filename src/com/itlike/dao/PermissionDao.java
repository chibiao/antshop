package com.itlike.dao;

import com.itlike.domain.Permission;

import java.sql.SQLException;
import java.util.List;

public interface PermissionDao {
    List<Permission> selectAll()throws SQLException;

    List<Permission> getPermissionByRid(long rid)throws SQLException;
}
