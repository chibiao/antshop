package com.itlike.filter;

import com.itlike.domain.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/admin/*", initParams = { @WebInitParam(name = "EXCLUDED_PAGES", value = "admin/login.jsp") })
public class AdminLoginFilter implements Filter {
    private String excludedPages;
    private String[] excludedPageArray;
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {// 遍历例外url数组
            // 判断当前URL是否与例外页面相同
            if (request.getServletPath().substring(1).equals(page)||request.getServletPath().substring(1).contains("admin/static")) { // 从第2个字符开始取（把前面的/去掉）
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {// 在过滤url之外
            chain.doFilter(request, response);
        }else {// 不在过滤url之外
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath()
                        + "/admin/login.jsp");
                return;
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages = filterConfig.getInitParameter("EXCLUDED_PAGES");
        if (null != excludedPages && excludedPages.length() != 0) { // 例外页面不为空
            excludedPageArray = excludedPages.split(String.valueOf(';'));
        }
    }

    @Override
    public void destroy() {
        this.excludedPages = null;
        this.excludedPageArray = null;
    }

}
