package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.Admin;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.AdminService;
import com.itlike.service.impl.AdminServiceImpl;
import com.itlike.util.MD5Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/adminServlet")
public class AdminServlet extends BaseServlet {
    private AdminService adminService=new AdminServiceImpl();
    public String adminIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/admin.jsp";
    }

    public void adminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo=new QueryVo();
        try {
            BeanUtils.populate(vo, parameterMap);
            PageListRes pageListRes = adminService.adminList(vo);
            System.out.println(pageListRes);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        AjaxRes ajaxRes = new AjaxRes();
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, parameterMap);
            if(adminService.getAdminByName(admin.getName())!=null&&adminService.getAdminByName(admin.getName()).getId().intValue()!=admin.getId()){
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名已存在");
                response.getWriter().print(JSON.toJSONString(ajaxRes));
                return;
            }
            adminService.updateAdmin(admin);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("修改失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        AjaxRes ajaxRes = new AjaxRes();
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, parameterMap);
            if(adminService.getAdminByName(admin.getName())!=null){
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名已存在");
                response.getWriter().print(JSON.toJSONString(ajaxRes));
                return;
            }
            admin.setPassword(MD5Util.md5(admin.getPassword()));
            adminService.addAdmin(admin);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    public void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        AjaxRes ajaxRes = new AjaxRes();
        String id = request.getParameter("id");
        try {
            adminService.deleteAdmin(Integer.parseInt(id));
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
        }

    }
}
