package com.itlike.service.impl;

import com.itlike.dao.UserDao;
import com.itlike.dao.impl.UserDaoImpl;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.User;
import com.itlike.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    public Long getCount() throws SQLException{
        return userDao.getCount();
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
    }

    @Override
    public void updateUserState(int id) throws SQLException {
        userDao.updateUserState(id);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public PageListRes userList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<User> users=userDao.userList(vo);
        pageListRes.setRows(users);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }
}
