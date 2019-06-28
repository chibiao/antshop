package com.itlike.service;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    PageListRes getRoles(QueryVo vo) throws SQLException;

    Long getCount() throws SQLException;

    AjaxRes saveRole(Role role);

    AjaxRes updateRole(Role role);

    AjaxRes deleteRole(long rid);

    List<Role> getRoleList()throws SQLException;

    List<Role> getRoleByAid(Long id)throws SQLException;
}
