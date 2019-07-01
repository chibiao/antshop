package com.itlike.dao;

import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    List<Role> getRoles(QueryVo vo) throws SQLException;

    Long getCount()throws SQLException;

    BigInteger insert(Role role)throws SQLException;

    void insertRoleAndPermissionRel(Long rid, Long pid)throws SQLException;

    void deleteRoleAndPermissionRel(Long rid)throws SQLException;

    void updateByPrimaryKey(Role role)throws SQLException;

    void deleteByPrimaryKey(long rid)throws SQLException;

    List<Role> getRoleList()throws SQLException;

    List<Role> getRoleByAid(Long id)throws SQLException;

    void deleteRoleAndAdminRel(long rid)throws SQLException;
}
