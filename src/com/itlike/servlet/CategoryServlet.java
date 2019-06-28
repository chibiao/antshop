package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Category;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.CategoryService;
import com.itlike.service.impl.CategoryServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseServlet {
    /*注入服务层*/
    private CategoryService categoryService = new CategoryServiceImpl();

    /*跳转到商品页*/
    public String categoryIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/category.jsp";
    }
    /*
    *获取所有商品列表
    * 根据分页
    * 还有模糊查询
    * */
    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        /*获取前端发送的所有数据  封装成一个map集合*/
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        try {
            /*通过map集合中的参数  封装成对象*/
            BeanUtils.populate(vo, parameterMap);
            /*返回查询出来的总数量和查询分页的数据条数
            * */
            PageListRes pageListRes = categoryService.getCategoryList(vo);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*查询所有*/
    public void allCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryService.allCategory();
            response.getWriter().print(JSON.toJSONString(categories));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*获取所有一级和二级菜单*/
    public void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategoryList();
            response.getWriter().print(JSON.toJSONString(categories));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*更新一级分类的信息*/
    public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        Category category = new Category();
        /*获取所有参数*/
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            /*根据参数  封装成category对象*/
            BeanUtils.populate(category, parameterMap);
            categoryService.updateCategory(category);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
            /*返回ajax的响应结果*/
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("修改失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    /*添加一级分类*/
    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        Category category = new Category();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(category, parameterMap);
            categoryService.addCategory(category);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
    /*删除一级分类*/
    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        String id = request.getParameter("id");
        try {
            /*根据id删除一级分类   如果下面有二级分类  无法删除  没有做级联删除*/
            categoryService.deleteCategory(Integer.parseInt(id));
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (SQLException e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

}
