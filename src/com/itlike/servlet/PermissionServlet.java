package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Permission;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.service.PermissionService;
import com.itlike.service.impl.PermissionServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 */
@WebServlet("/permissionServlet")
public class PermissionServlet extends BaseServlet {
    private PermissionService permissionService = new PermissionServiceImpl();
    /*获取所有的权限列表*/
    public void permissionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Permission> permissions = permissionService.getPermissions();
            response.getWriter().print(JSON.toJSONString(permissions));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*根据角色查询出角色对应的权限*/
    public void getPermissionByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        System.out.println("getPermissionByRid");
        System.out.println(rid);
        try {
            List<Permission> permissions = permissionService.getPermissionByRid(Long.parseLong(rid));
            response.getWriter().print(JSON.toJSONString(permissions));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
