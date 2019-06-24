package com.itlike.service.impl;

import com.itlike.dao.AdminDao;
import com.itlike.dao.impl.AdminDaoImpl;
import com.itlike.domain.Admin;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao= new AdminDaoImpl();
    public Long getCount()throws SQLException{
        return adminDao.getCount();
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        adminDao.updateAdmin(admin);
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        adminDao.addAdmin(admin);
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        adminDao.deleteAdmin(id);
    }

    @Override
    public Admin getAdminByName(String name) throws SQLException {
        return adminDao.getAdminByName(name);
    }

    @Override
    public PageListRes adminList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Admin> admins=adminDao.adminList(vo);
        pageListRes.setRows(admins);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }
}
