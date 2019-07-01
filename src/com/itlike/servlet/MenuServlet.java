package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.*;
import com.itlike.service.MenuService;
import com.itlike.service.impl.MenuServiceImpl;
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

@WebServlet("/menuServlet")
public class MenuServlet extends BaseServlet {
    private MenuService menuService = new MenuServiceImpl();

    public String menuIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/menu.jsp";
    }
    public void parentMenuList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> menus= null;
        try {
            menus = menuService.parentMenuList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().print(JSON.toJSONString(menus));
    }
    public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        AjaxRes ajaxRes=menuService.deleteMenu(Long.parseLong(id));
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }
    public void saveMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
            if (map.getKey().equals("text")) {
                menu.setText(map.getValue()[0]);
            }
            if (map.getKey().equals("url")) {
                menu.setUrl(map.getValue()[0]);
            }
            if (map.getKey().contains("parent")) {
                menu.setParent_id(Long.parseLong(map.getValue()[0]));
            }
        }
        AjaxRes ajaxRes=menuService.saveMenu(menu);
        response.getWriter().print(JSON.toJSONString(ajaxRes));
        System.out.println(menu);
    }
    public void updateMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = new Menu();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
            if (map.getKey().equals("id")) {
                menu.setId(Long.parseLong(map.getValue()[0]));
            }
            if (map.getKey().equals("text")) {
                menu.setText(map.getValue()[0]);
            }
            if (map.getKey().equals("url")) {
                menu.setUrl(map.getValue()[0]);
            }
            if (map.getKey().contains("parent")) {
                menu.setParent_id(Long.parseLong(map.getValue()[0]));
            }
        }
        AjaxRes ajaxRes=menuService.updateMenu(menu);
        response.getWriter().print(JSON.toJSONString(ajaxRes));
        System.out.println(menu);
    }
    public void menuList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        try {
            BeanUtils.populate(vo, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            PageListRes pageListRes = menuService.menuList(vo);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
