package com.itlike.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String desPath = null;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		// 接收参数
		String action = request.getParameter("action");
		//System.out.println(action);
		try {
			// 获取当前字节码对象
			Class<? extends BaseServlet> clazz = this.getClass();
			// 获取方法 获取的必须是公有方法
			Method method = clazz.getMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			// 判断有没有传入的方法
			if (method != null) {
				// 有就调用
				desPath =  (String) method.invoke(this, request, response);
				// 转发
				if (desPath != null) {
					if(desPath.contains("redirect")){
						//请求重定向
						response.sendRedirect(request.getContextPath()
								+ desPath.substring(9));
					}else{
						//请求转发
						request.getRequestDispatcher(desPath).forward(request,
								response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
