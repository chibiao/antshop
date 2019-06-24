package com.itlike.service;

import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.User;

import java.sql.SQLException;

public interface UserService {
    PageListRes userList(QueryVo vo)throws SQLException;
    Long getCount() throws SQLException;

    void addUser(User user)throws SQLException;

    void updateUser(User user)throws SQLException;

    void updateUserState(int id)throws SQLException;

    User getUserByUsername(String username)throws SQLException;
}
