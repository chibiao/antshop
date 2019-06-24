<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>码蚁商城</title>
    <!--链接外部样式-->
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="style/index.css">
    <link rel="stylesheet" href="style/pageStyle.css">
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="js/header.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<!--热买商品-->
<div id="hot_goods">
    <h3 class="hot_goods_title">热卖商品</h3>
    <div class="hot_goods_body">
        <input type="hidden" id="id" value="${id}">
        <input type="hidden" id="scid" value="${scid}">
        <ul>
            <c:if test="${empty pageBean.products }">
                没有商品
            </c:if>
            <c:if test="${!empty pageBean.products }">
                <c:forEach items="${pageBean.products }" var="product">
                    <li>
                        <a href="/productServlet?action=getProductdetail&id=${product.id}">
                            <img src="/upload/image/${product.image }" alt="">
                            <p>${product.name }</p>
                            <del>￥${product.marketPrice}</del>
                            <br>
                            <i class="yuan">￥</i>
                            <span class="price">${product.shopPrice }</span>
                        </a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
    <!--分页-->
    <div id="page" class="page_div"></div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="js/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:${pageBean.pageNo},
        totalPage:${pageBean.totalPage},
        totalSize: ${pageBean.totalSize},
        callback: function (num) {
            var id = $("#id").val();
            var scid=$("#scid").val();
            if(id){
                $(window).attr('location', "/productServlet?action=getProductByCategory&pageNo=" + num + "&id=" + id);
            }
            if(scid){
                $(window).attr('location', "/productServlet?action=getProductBySecondCategory&pageNo=" + num + "&scid=" + scid);
            }
        }
    });
</script>
</body>
</html>


