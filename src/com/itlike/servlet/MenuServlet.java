package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.Admin;
import com.itlike.domain.Menu;
import com.itlike.service.MenuService;
import com.itlike.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/menuServlet")
public class MenuServlet extends BaseServlet {
    private MenuService menuService = new MenuServiceImpl();
    /*根据权限获取菜单列表*/
    public void getTreeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        try {
            List<Menu> menus = menuService.getTreeData(admin);
            response.getWriter().print(JSON.toJSONString(menus));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
