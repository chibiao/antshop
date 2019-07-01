package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.User;
import com.itlike.service.UserService;
import com.itlike.service.impl.UserServiceImpl;
import com.itlike.util.MD5Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Description TODO
 * @ClassName ${NAME}
 * @Author 迟彪
 * @Date 2019/6/20 上午 10:09
 * @Version V1.0
 */
@WebServlet(value = "/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public String userIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/user.jsp";
    }
    /*更新个人信息*/
    public void updateUserDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String addr=request.getParameter("addr");
        user.setName(name);
        user.setAddr(addr);
        user.setPhone(phone);
        user.setEmail(email);
        try {
            userService.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("user",user);
        response.getWriter().print("<script type='text/javascript'>alert('修改成功');</script>");
        response.setHeader("refresh", "1;url=/index.jsp");
    }
    /*分页查询user*/
    public void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryVo vo = new QueryVo();
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(vo, parameterMap);
            PageListRes pageListRes = userService.userList(vo);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*添加user*/
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            if(userService.getUserByUsername(user.getUsername())!=null){
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名已存在");
                response.getWriter().print(JSON.toJSONString(ajaxRes));
                return;
            }
            user.setPassword(MD5Util.md5(user.getPassword()));
            userService.addUser(user);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
        }
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }
    /*后台更新用户的信息*/
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            if(userService.getUserByUsername(user.getUsername())!=null&&userService.getUserByUsername(user.getUsername()).getId().intValue()!=user.getId().intValue()){
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名已存在");
                response.getWriter().print(JSON.toJSONString(ajaxRes));
                return;
            }
            userService.updateUser(user);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("修改失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    /*更新角色的是否可用状态*/
    public void updateUserState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        // 1.获取所有的参数
        String id = request.getParameter("id");
        User user = new User();
        try {
            userService.updateUserState(Integer.parseInt(id));
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

}