package com.itlike.dao.impl;

import com.itlike.dao.AdminDao;
import com.itlike.domain.Admin;
import com.itlike.domain.Permission;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Admin> adminList(QueryVo vo) throws SQLException {
        String sql = "select * from admin limit ?,?";
        return qr.query(sql,new BeanListHandler<>(Admin.class),(vo.getPage() - 1)*vo.getRows(),vo.getRows());
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
    public BigInteger addAdmin(Admin admin) throws SQLException {
        String sql ="insert into admin(anum,name,password) value(?,?,?)";
        qr.update(sql,admin.getAnum(),admin.getName(),admin.getPassword());
        return (BigInteger)qr.query("SELECT LAST_INSERT_ID()", new ScalarHandler(1));
    }

    @Override
    public void deleteAdmin(Long id) throws SQLException {
        String sql="delete from admin where id=?";
        qr.update(sql,id);
    }

    @Override
    public Admin getAdminByName(String name) throws SQLException {
        String sql="select * from admin where name=?";
        Admin admin = qr.query(sql, new BeanHandler<>(Admin.class), name);
        if(admin!=null){
            String sql2 = "select * from role where rid in (select rid from admin_role_rel where aid=?)";
            List<Role> roles = qr.query(sql2, new BeanListHandler<>(Role.class), admin.getId());
            admin.setRoles(roles);
        }
        return admin;
    }

    @Override
    public void deleteAdminAndRoleRel(Long id) throws SQLException {
        String sql="delete from admin_role_rel where aid=?";
        qr.update(sql,id);
    }

    @Override
    public void insertAdminAndRoleRel(Long id, Long rid) throws SQLException {
        String sql="insert into admin_role_rel(aid,rid) values (?,?)";
        qr.update(sql,id,rid);
    }

    @Override
    public List<Permission> getAllPermissionById(Long id) {
        String sql="SELECT DISTINCT * FROM role_permission_rel as rp LEFT JOIN permission as p ON rp.pid=p.pid WHERE rid in(SELECT rid FROM admin_role_rel where aid=?)";
        try {
            return qr.query(sql,new BeanListHandler<>(Permission.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
