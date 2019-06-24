package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.*;
import com.itlike.service.CartService;
import com.itlike.service.ProductService;
import com.itlike.service.impl.CartServiceImpl;
import com.itlike.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /*获取购物车的数据*/
    public void getCartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                List<Product> products = productService.getProductByUid(user.getId());
                List<Cart> cartList = cartService.getCartByUid(user.getId());
                List<CartProduct> cartProducts = new ArrayList<>();
                for (Product product : products) {
                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setShopId(product.getId());
                    cartProduct.setShopPrice(product.getShopPrice());
                    cartProduct.setShopName(product.getName());
                    cartProduct.setShopImage(product.getImage());
                    for (Cart cart : cartList) {
                        if (cart.getId() == product.getId()) {
                            cartProduct.setShopNumber(cart.getCount());
                        }
                    }
                    cartProducts.add(cartProduct);
                }
                response.getWriter().print(JSON.toJSONString(cartProducts));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
            if (cartList == null) {
                response.getWriter().print(cartList);
            } else {
                try {
                    List<Product> products = productService.getProductByCart(cartList);
                    List<CartProduct> cartProducts = new ArrayList<>();
                    for (Product product : products) {
                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setShopId(product.getId());
                        cartProduct.setShopPrice(product.getShopPrice());
                        cartProduct.setShopName(product.getName());
                        cartProduct.setShopImage(product.getImage());
                        for (Cart cart : cartList) {
                            if (cart.getId() == product.getId()) {
                                cartProduct.setShopNumber(cart.getCount());
                            }
                        }
                        cartProducts.add(cartProduct);
                    }
                    response.getWriter().print(JSON.toJSONString(cartProducts));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*添加到购物车的数据*/
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Cart cart = new Cart();
        AjaxRes ajaxRes = new AjaxRes();
        try {
            BeanUtils.populate(cart, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            List<Cart> carts=new ArrayList<>();
            carts.add(cart);
            try {
                cartService.addToCart(user.getId(),carts);
                ajaxRes.setSuccess(true);
                ajaxRes.setMsg("添加成功");
            } catch (SQLException e) {
                ajaxRes.setMsg("已有此商品");
                ajaxRes.setSuccess(false);
            }
            response.getWriter().print(JSON.toJSONString(ajaxRes));
            return;
        }
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        if (cartList != null) {
            boolean flag = true;
            for (Cart cart1 : cartList) {
                if (cart1.getId() == cart.getId()) {
                    flag = false;
                }
            }
            if (flag) {
                cartList.add(cart);
                session.setAttribute("cartList", cartList);
                ajaxRes.setSuccess(true);
                ajaxRes.setMsg("添加成功");
            } else {
                ajaxRes.setMsg("购物车中已存在");
                ajaxRes.setSuccess(false);
            }
        } else {
            List<Cart> carts = new ArrayList<>();
            carts.add(cart);
            session.setAttribute("cartList", carts);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }
        response.getWriter().print(JSON.toJSONString(ajaxRes));
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            try {
                cartService.deleteCart(user.getId(),Long.parseLong(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        List<Cart> newCartList = new ArrayList<>();
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        for (Cart cart : cartList) {
            if (cart.getId() == Integer.parseInt(id)) {
                continue;
            }
            newCartList.add(cart);
        }
        session.setAttribute("cartList", newCartList);
    }
}
