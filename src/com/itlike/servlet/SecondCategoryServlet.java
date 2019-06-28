package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.SecondCategory;
import com.itlike.service.SecondCategoryService;
import com.itlike.service.impl.SecondCategoryServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/secondCategoryServlet")
public class SecondCategoryServlet extends BaseServlet {
    private SecondCategoryService secondCategoryService = new SecondCategoryServiceImpl();

    public String secondCategoryIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/secondcategory.jsp";
    }

    public void secondCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        try {
            BeanUtils.populate(vo, parameterMap);
            PageListRes pageListRes = secondCategoryService.getCategoryList(vo);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AllSecondCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        List<SecondCategory> secondCategories = null;
        try {
            secondCategories = secondCategoryService.AllSecondCategory();
            response.getWriter().print(JSON.toJSONString(secondCategories));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSecondCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        SecondCategory secondCategory = new SecondCategory();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(secondCategory, parameterMap);
            System.out.println(secondCategory);
            secondCategoryService.updateSecondCategory(secondCategory);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("修改失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

    public void addSecondCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        SecondCategory secondCategory = new SecondCategory();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(secondCategory, parameterMap);
            secondCategoryService.addSecondCategory(secondCategory);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    public void deleteSecondCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        String id = request.getParameter("id");
        System.out.println("deleteSecondCategory");
        try {
            secondCategoryService.deleteSecondCategory(Integer.parseInt(id));
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (SQLException e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
}