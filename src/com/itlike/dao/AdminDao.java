package com.itlike.dao;

import com.itlike.domain.Admin;
import com.itlike.domain.QueryVo;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    /*分页查询*/
    List<Admin> adminList(QueryVo vo) throws SQLException;

    /*获取总记录数*/
    Long getCount() throws SQLException;

    /*更新admin*/
    void updateAdmin(Admin admin) throws SQLException;

    /*添加admin*/
    void addAdmin(Admin admin) throws SQLException;

    /*删除管理员*/
    void deleteAdmin(int id) throws SQLException;

    /*根据名称查询*/
    Admin getAdminByName(String name) throws SQLException;
}
