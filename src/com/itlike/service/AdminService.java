package com.itlike.service;

import com.itlike.domain.Admin;
import com.itlike.domain.PageListRes;
import com.itlike.domain.Permission;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    PageListRes adminList(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    void updateAdmin(Admin admin)throws SQLException;

    void addAdmin(Admin admin)throws SQLException;

    void deleteAdmin(Long id) throws SQLException;

    Admin getAdminByName(String name)throws SQLException;

    List<Permission> getAllPermissionById(Long id);
}
