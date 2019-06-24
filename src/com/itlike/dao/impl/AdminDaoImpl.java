package com.itlike.dao.impl;

import com.itlike.dao.AdminDao;
import com.itlike.domain.Admin;
import com.itlike.domain.QueryVo;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Admin> adminList(QueryVo vo) throws SQLException {
        String sql = "select * from admin limit ?,?";
        return qr.query(sql,new BeanListHandler<>(Admin.class),vo.getPage()-1,vo.getRows());
    }

    @Override
    public Long getCount() throws SQLException {
        String sql ="select count(*) from admin";
        return qr.query(sql,new ScalarHandler<>(1));
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        String sql ="update admin set anum=?,name=?,password=? where id=?";
        qr.update(sql,admin.getAnum(),admin.getName(),admin.getPassword(),admin.getId());
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String sql ="insert into admin(anum,name,password) value(?,?,?)";
        qr.update(sql,admin.getAnum(),admin.getName(),admin.getPassword());
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        String sql="delete from admin where id=?";
        qr.update(sql,id);
    }

    @Override
    public Admin getAdminByName(String name) throws SQLException {
        String sql="select * from admin where name=?";
        return qr.query(sql,new BeanHandler<>(Admin.class),name);
    }
}
