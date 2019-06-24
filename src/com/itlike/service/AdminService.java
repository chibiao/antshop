package com.itlike.service;

import com.itlike.domain.Admin;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;

public interface AdminService {
    PageListRes adminList(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    void updateAdmin(Admin admin)throws SQLException;

    void addAdmin(Admin admin)throws SQLException;

    void deleteAdmin(int id) throws SQLException;

    Admin getAdminByName(String name)throws SQLException;
}
