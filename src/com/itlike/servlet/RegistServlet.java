package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.AjaxRes;
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


@WebServlet(value = "/registServlet")
public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        // 设置请求编码
        request.setCharacterEncoding("utf-8");
        // 设置响应编码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        // 获取验证码
        String code = request.getParameter("code");
        String word = (String) this.getServletContext().getAttribute(
                "checkCode");
        System.out.println(word);
        System.out.println(code);
        // System.out.println(word);

        // 判断验证码
        if (code.equals(word)) {
            // 接收所有参数
            Map<String, String[]> map = request.getParameterMap();
            // 封装User对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            User checkUser = null;
            try {
                //判断用户名是否存在
                checkUser=userService.getUserByUsername(user.getUsername());
                if(checkUser==null){
                    /*添加用户*/
                    user.setState(true);
                    user.setPassword(MD5Util.md5(user.getPassword()));
                    userService.addUser(user);
                    ajaxRes.setSuccess(true);
                    ajaxRes.setMsg("注册成功");
                    //跳转到登录页面
                    response.getWriter().print(JSON.toJSONString(ajaxRes));
                }else {
                    ajaxRes.setMsg("用户名已存在");
                    ajaxRes.setSuccess(false);
                    response.getWriter().print(JSON.toJSONString(ajaxRes));
                }
            } catch (SQLException e) {
                ajaxRes.setMsg("服务器异常");
                ajaxRes.setSuccess(false);
                response.getWriter().print(JSON.toJSONString(ajaxRes));
            }
        } else {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("验证码错误");
            //跳转到登录页面
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
}