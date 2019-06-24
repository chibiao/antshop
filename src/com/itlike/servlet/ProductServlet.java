package com.itlike.servlet;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.*;
import com.itlike.service.ProductService;
import com.itlike.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@WebServlet("/productServlet")
@MultipartConfig
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private static String filename = null;

    public String productIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/product.jsp";
    }

    public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        QueryVo vo = new QueryVo();
        try {
            BeanUtils.populate(vo, parameterMap);
            PageListRes pageListRes = productService.getProductList(vo);
            response.getWriter().print(JSON.toJSONString(pageListRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*根据商品分类查询商品*/
    public String getProductByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");
        PageBean pageBean = new PageBean();
        if (pageNo == null) {
            pageBean.setPageNo(1);
        } else {
            pageBean.setPageNo(Integer.parseInt(pageNo));
        }
        int pageCurrent = pageBean.getPageNo();
        try {
            pageBean = productService.getProductByCategory(Integer.parseInt(id), pageBean.getPageNo());
            pageBean.setPageNo(pageCurrent);
            double totalPage = Math.ceil(1.0 * pageBean.getTotalSize() / 8);
            pageBean.setTotalPage((int) totalPage);
            request.setAttribute("pageBean", pageBean);
            /*一级分类的id*/
            request.setAttribute("id", id);
            return "product.jsp";
        } catch (SQLException e) {
            return null;
        }
    }

    /*根据商品二级分类查询商品*/
    public String getProductBySecondCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("scid");
        String pageNo = request.getParameter("pageNo");
        PageBean pageBean = new PageBean();
        if (pageNo == null) {
            pageBean.setPageNo(1);
        } else {
            pageBean.setPageNo(Integer.parseInt(pageNo));
        }
        int pageCurrent = pageBean.getPageNo();
        try {
            pageBean = productService.getProductBySecondCategory(Integer.parseInt(id), pageBean.getPageNo());
            pageBean.setPageNo(pageCurrent);
            double totalPage = Math.ceil(1.0 * pageBean.getTotalSize() / 8);
            pageBean.setTotalPage((int) totalPage);
            request.setAttribute("pageBean", pageBean);
            /*二级级分类的id*/
            request.setAttribute("sid", id);
            return "product.jsp";
        } catch (SQLException e) {
            return null;
        }
    }
    /*获取商品详情*/
    public String getProductdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Product product = productService.selectProductById(Long.parseLong(id));
            request.setAttribute("product",product);
            return "detail.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*更新商品的状态*/
    public void upadateProductState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        AjaxRes ajaxRes = new AjaxRes();
        try {
            productService.upadateProductState(Integer.parseInt(id));
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        Product product = new Product();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(product, parameterMap);
            /*设置商品图片为之前的图片 如果没有上传图片*/
            if (filename == null) {
                Product oldProduct = productService.selectProductById(product.getId());
                product.setImage(oldProduct.getImage());
            } else {
                product.setImage(filename);
            }
            productService.updateProduct(product);
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

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        Product product = new Product();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(product, parameterMap);
            product.setState(true);
            product.setImage(filename);
            productService.addProduct(product);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            Part part = request.getPart("photo");
            String header = part.getHeader("Content-Disposition");
            String imageName = header.substring(header.indexOf("filename=") + 10, header.length() - 1);
            /*获取图片的扩展名*/
            String[] split = imageName.split("\\.");
            String extension = split[1];
            filename = UUID.randomUUID().toString().replace("-", "") + "." + extension;
            String realPath = request.getServletContext().getRealPath("/upload/image/");
            File dir = new File(realPath);
            //6.判断文件夹是否存在
            if (!dir.exists()) {
                //7.不存在就创建
                dir.mkdirs();
            }
            try {
                //8.向目标文件写内容
                part.write(realPath + filename);
                ajaxRes.setSuccess(true);
                response.getWriter().print(JSON.toJSONString(ajaxRes));
            } catch (Exception e) {
                ajaxRes.setSuccess(false);
                response.getWriter().print(JSON.toJSONString(ajaxRes));
            }
        } catch (Exception e) {
            ajaxRes.setSuccess(true);
            filename = null;
            response.getWriter().print(JSON.toJSONString(ajaxRes));
        }
    }
}
