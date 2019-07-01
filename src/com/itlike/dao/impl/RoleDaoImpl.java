package com.itlike.dao.impl;

import com.itlike.dao.RoleDao;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private QueryRunner qr=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Role> getRoles(QueryVo vo) throws SQLException {
        String sql ="select * from role limit ?,?";
        return qr.query(sql,new BeanListHandler<>(Role.class),(vo.getPage() - 1)*vo.getRows(),vo.getRows());
    }

    @Override
    public Long getCount() throws SQLException {
        String sql ="select count(*) from role";
        return qr.query(sql,new ScalarHandler<>(1));
    }

    @Override
    public BigInteger insert(Role role) throws SQLException {
        String sql="insert into role(rnum,rname) value(?,?)";
        qr.update(sql,role.getRnum(),role.getRname());
        return (BigInteger) qr.query("SELECT LAST_INSERT_ID()", new ScalarHandler(1));
    }

    @Override
    public void insertRoleAndPermissionRel(Long rid, Long pid) throws SQLException {
        String sql="insert into role_permission_rel(rid,pid) values(?,?)";
        qr.update(sql,rid,pid);
    }

    @Override
    public void deleteRoleAndPermissionRel(Long rid) throws SQLException {
        String sql="delete from role_permission_rel where rid=?";
        qr.update(sql,rid);
    }

    @Override
    public void updateByPrimaryKey(Role role) throws SQLException {
        String sql="update role  set rnum = ?, rname = ? where rid = ?";
        qr.update(sql,role.getRnum(),role.getRname(),role.getRid());
    }

    @Override
    public void deleteByPrimaryKey(long rid) throws SQLException {
        String sql="delete from role where rid=?";
        qr.update(sql,rid);
    }

    @Override
    public List<Role> getRoleList() throws SQLException {
        String sql ="select * from role";
        return qr.query(sql,new BeanListHandler<>(Role.class));
    }

    @Override
    public List<Role> getRoleByAid(Long id) throws SQLException {
        String sql=" select rid from admin_role_rel where aid=?";
        return qr.query(sql,new BeanListHandler<>(Role.class),id);
    }

    @Override
    public void deleteRoleAndAdminRel(long rid) throws SQLException {
        String sql="delete from admin_role_rel where rid=?";
        qr.update(sql,rid);
    }
}
