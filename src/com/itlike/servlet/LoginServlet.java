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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private CartService cartService=new CartServiceImpl();

    /*登录*/
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            User user = userService.getUserByUsername(username);
            if (user != null && user.getPassword().equals(MD5Util.md5(request.getParameter("password"))) && user.getState().booleanValue() == true) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
                if(cartList!=null&&cartList.size()!=0){
                    cartService.addToCart(user.getId(),cartList);
                }
                return "redirect:index.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:login.jsp";
        }
        return "redirect:login.jsp";
    }

    /*退出登录*/
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index.jsp";
    }
}
