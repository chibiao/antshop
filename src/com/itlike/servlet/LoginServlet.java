package com.itlike.servlet;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.Cart;
import com.itlike.domain.User;
import com.itlike.service.CartService;
import com.itlike.service.UserService;
import com.itlike.service.impl.CartServiceImpl;
import com.itlike.service.impl.UserServiceImpl;
import com.itlike.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /*账号激活*/
    public void activate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            userService.updateUState(username);
            response.getWriter().print("账户激活成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*登录*/
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String remember = request.getParameter("remember");
        String password = request.getParameter("password");
        System.out.println("login");
        try {
            /*去查询用户是否存在*/
            User user = userService.getUserByUsername(username);
            /*如果用户存在 判断密码是否相同*/
            if (user != null && user.getPassword().equals(MD5Util.md5(request.getParameter("password"))) && user.getState().booleanValue() == true) {
                /*登录后写到session中*/
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                /*登录后遍历session中的购物数据 添加到数据库*/
                List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
                if (cartList != null && cartList.size() != 0) {
                    cartService.addToCart(user.getId(), cartList);
                }
                /*判断是否记住密码*/
                if ("true".equals(remember)){
                    Cookie ck1 = new Cookie("remember", remember);
                    ck1.setMaxAge(60*60);
                    response.addCookie(ck1);
                    Cookie ck2 = new Cookie("username", username);
                    ck2.setMaxAge(60*60);
                    response.addCookie(ck2);
                    Cookie ck3 = new Cookie("password", password);
                    ck3.setMaxAge(60*60);
                    response.addCookie(ck3);
                }
                return "redirect:index.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:index.jsp";
        }
        return "redirect:login.jsp";
    }

    /*退出登录*/
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*移除session中的用户*/
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index.jsp";
    }
}
