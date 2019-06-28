package com.itlike.dao.impl;

import com.itlike.dao.PermissionDao;
import com.itlike.domain.Permission;
import com.itlike.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PermissionDaoImpl implements PermissionDao {
    private QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Permission> selectAll() throws SQLException {
        String sql ="select * from permission";
        return qr.query(sql,new BeanListHandler<>(Permission.class));
    }

    @Override
    public List<Permission> getPermissionByRid(long rid) throws SQLException {
        String sql = "SELECT * FROM permission WHERE pid in(SELECT pid FROM role_permission_rel where rid=?)";
        return qr.query(sql,new BeanListHandler<>(Permission.class),rid);
    }
}
