package com.itlike.dao.impl;

import com.itlike.dao.UserDao;
import com.itlike.domain.Product;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.domain.User;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner qr =new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public Long getCount() throws SQLException {
        String sql="select count(*) from user";
        return qr.query(sql,new ScalarHandler<>(1));
    }

    @Override
    public List<User> userList(QueryVo vo) throws SQLException {
        if(vo.getKeyword()!=null&&!"".equals(vo.getKeyword())){
            String sql ="select * from user where name like ? limit ?,?";
            return qr.query(sql,new BeanListHandler<>(User.class),"%"+vo.getKeyword()+"%",(vo.getPage() - 1)*vo.getRows(),vo.getRows());
        }else {
            String sql="select * from user limit ?,?";
            return qr.query(sql,new BeanListHandler<>(User.class),(vo.getPage() - 1)*vo.getRows(),vo.getRows());
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql ="insert into user(username,password,name,email,phone,addr,state) value(?,?,?,?,?,?,?)";
        qr.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),user.getAddr(),user.getState());
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql="update user set username=?,name=?,email=?,phone=?,addr=?,state=? where id=?";
        qr.update(sql,user.getUsername(),user.getName(),user.getEmail(),user.getPhone(),user.getAddr(),user.getState(),user.getId());
    }

    @Override
    public void updateUserState(int id) throws SQLException {
        String sql ="update user set state=false where id=?";
        qr.update(sql,id);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String sql="select * from user where username=?";
        return qr.query(sql,new BeanHandler<>(User.class),username);
    }

    @Override
    public void updateUState(String username) throws SQLException {
        String sql ="update user set state=true where username=?";
        qr.update(sql,username);
    }
}
