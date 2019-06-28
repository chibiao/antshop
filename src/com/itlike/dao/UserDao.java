package com.itlike.dao;

import com.itlike.domain.QueryVo;
import com.itlike.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    Long getCount() throws SQLException;

    List<User> userList(QueryVo vo)throws SQLException;

    void addUser(User user)throws SQLException;

    void updateUser(User user)throws SQLException;

    void updateUserState(int id)throws SQLException;

    User getUserByUsername(String username)throws SQLException;
    /*更新用户状态*/
    void updateUState(String username)throws SQLException;
}
