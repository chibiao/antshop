package com.itlike.service.impl;

import com.itlike.dao.AdminDao;
import com.itlike.dao.impl.AdminDaoImpl;
import com.itlike.domain.*;
import com.itlike.service.AdminService;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao= new AdminDaoImpl();
    /*获取总的记录条数*/
    public Long getCount()throws SQLException{
        return adminDao.getCount();
    }
    /*更新管理员的信息*/
    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        /*打破员工和角色的关系*/
        adminDao.deleteAdminAndRoleRel(admin.getId());
        /*保存员工*/
        adminDao.updateAdmin(admin);
        /*保存员工和 角色 关系*/
        for (Role role : admin.getRoles()) {
            adminDao.insertAdminAndRoleRel(admin.getId(),role.getRid());
        }

    }
    /*添加管理员*/
    @Override
    public void addAdmin(Admin admin) throws SQLException {
        BigInteger id = adminDao.addAdmin(admin);
        admin.setId(id.longValue());
        for (Role role : admin.getRoles()) {
            adminDao.insertAdminAndRoleRel(admin.getId(),role.getRid());
        }
    }
    /*删除管理员*/
    @Override
    public void deleteAdmin(Long id) throws SQLException {
        adminDao.deleteAdminAndRoleRel(id);
        adminDao.deleteAdmin(id);
    }
    /*根据管理员的名称获取管理员*/
    @Override
    public Admin getAdminByName(String name) throws SQLException {
        return adminDao.getAdminByName(name);
    }
    /*根据管理员的id获取所有的权限(去除所有重复的权限)*/
    @Override
    public List<Permission> getAllPermissionById(Long id) {
        return adminDao.getAllPermissionById(id);
    }
    /*获取管理员列表  分页*/
    @Override
    public PageListRes adminList(QueryVo vo) throws SQLException {
        PageListRes pageListRes = new PageListRes();
        List<Admin> admins=adminDao.adminList(vo);
        pageListRes.setRows(admins);
        pageListRes.setTotal(getCount());
        return pageListRes;
    }
}
