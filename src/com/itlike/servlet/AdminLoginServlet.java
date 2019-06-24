package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.Admin;
import com.itlike.domain.AjaxRes;
import com.itlike.service.AdminService;
import com.itlike.service.impl.AdminServiceImpl;
import com.itlike.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        try {
            Admin admin=adminService.getAdminByName(name);
            if(admin!=null){
                String password = MD5Util.md5(pwd);
                if(admin.getPassword().equals(password)){
                    // 把用户保存到session
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", admin);
                    ajaxRes.setSuccess(true);
                    response.getWriter().print(JSON.toJSONString(ajaxRes));
                }else {
                    ajaxRes.setSuccess(false);
                    ajaxRes.setMsg("密码不正确");
                    response.getWriter().print(JSON.toJSONString(ajaxRes));
                }
            }else {
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名不存在");
                response.getWriter().print(JSON.toJSONString(ajaxRes));
            }
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("服务器内部错误");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "redirect:/admin/login.jsp";
    }

}
