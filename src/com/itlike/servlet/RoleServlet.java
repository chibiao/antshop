package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.*;
import com.itlike.service.RoleService;
import com.itlike.service.impl.RoleServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/roleServlet")
public class RoleServlet extends BaseServlet {
    private RoleService roleService = new RoleServiceImpl();

    /*角色主页*/
    public String roleIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/role.jsp";
    }
    /*获取所有的角色*/
    public void roleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Role> roles = roleService.getRoleList();
            response.getWriter().print(JSON.toJSONString(roles));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*获取分页角色数据*/
    public void getRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        try {
            BeanUtils.populate(vo, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PageListRes roles = roleService.getRoles(vo);
            response.getWriter().print(JSON.toJSONString(roles));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        AjaxRes ajaxRes = roleService.deleteRole(Long.parseLong(rid));
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }

    /*更新角色信息*/
    public void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        Role role = new Role();
        List<Permission> permissions = new ArrayList<>();
        /*封装role*/
        for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
            if (map.getKey().equals("rid")) {
                role.setRid(Long.parseLong(map.getValue()[0]));
            }
            if (map.getKey().equals("rname")) {
                role.setRname(map.getValue()[0]);
            }
            if (map.getKey().equals("rnum")) {
                role.setRnum(map.getValue()[0]);
            }
            if (map.getKey().contains("permission")) {
                Permission permission = new Permission();
                permission.setPid(Long.parseLong(map.getValue()[0]));
                permissions.add(permission);
            }
        }
        role.setPermissions(permissions);
        AjaxRes ajaxRes = roleService.updateRole(role);
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }

    /*保存角色信息*/
    public void saveRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("saveRole");
        Role role = new Role();
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Permission> permissions = new ArrayList<>();
        for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
            if (map.getKey().equals("rname")) {
                role.setRname(map.getValue()[0]);
            }
            if (map.getKey().equals("rnum")) {
                role.setRnum(map.getValue()[0]);
            }
            if (map.getKey().contains("permission")) {
                Permission permission = new Permission();
                permission.setPid(Long.parseLong(map.getValue()[0]));
                permissions.add(permission);
            }
        }
        role.setPermissions(permissions);
        AjaxRes ajaxRes = roleService.saveRole(role);
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }
    /*根据管理员编号查询角色编号*/
    public void getRoleByAid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        /*查询对应的角色*/
        try {
            List<Role> roleByAid = roleService.getRoleByAid(Long.parseLong(id));
            response.getWriter().print(JSON.toJSONString(roleByAid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
