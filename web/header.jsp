<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>码蚁商城</title>
    <!--链接外部样式-->
    <link rel="stylesheet" href="style/headerStyle.css">
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="style/footerStyle.css">
    <%--<link rel="stylesheet" href="./layui/css/layui.css">--%>
    <script src="js/jquery-1.11.0.js"></script>
    <%--<script src="layui/layui.all.js" charset="utf-8"></script>--%>
</head>
<body>
<!--头部-->
<div id="header">
    <!--头部登录，购物车-->
    <div class="header_top">
        <!--中部-->
        <div class="header_top_center">
            <!--中部左侧-->
            <div class="h_top_left">欢迎来到码蚁商城</div>
            <!--中部右侧-->
            <div class="h_top_right">
                <c:if test="${empty user }">
                    <a href="login.jsp">登录</a>
                    <a href="regist.jsp">免费注册</a>
                </c:if>
                <c:if test="${!empty user }">
                    欢迎:<span id="username">${user.username }</span>
                    <a href="/loginServlet?action=logout">退出</a>
                </c:if>
                <a href="shopCart.jsp">购物车</a>
                <c:if test="${!empty user }">
                    <a href="#" id="orders">我的订单</a>
                </c:if>
                <c:if test="${!empty user }">
                    <a href="userdetail.jsp" id="userdetail">我的个人信息</a>
                </c:if>
            </div>
        </div>
    </div>
    <!--中部搜索-->
    <div class="header_center">
        <!--版心-->
        <div class="h_c_center">

            <!--左侧logo-->
            <div class="h_c_logo">
                <img src="images/log.png" alt="">
            </div>

            <!--中部搜索-->
            <div class="h_c_search">

                <form action="/productServlet?action=getProductByName" method="post" id="inputForm">
                    <input type="text"   class="s_input" name="name" id="s_input">
                    <input type="submit" value="搜索" class="s_button" id="inputBtn">
                </form>

                <div class="hot">
                    <a href="#">新款连衣裙</a> <a href="#">四件套</a> <a href="#">潮流T恤</a> <a
                        href="#">时尚女鞋</a> <a href="#">短裤半身裙</a>
                </div>
            </div>
            <!--右部二维码-->
            <div class="h_c_code">
                <img src="images/pcode.png" alt="">
            </div>
        </div>
    </div>
    <!--导航-->
    <div id="nav">
        <ul id="nav_category">
            <li><a href="index.jsp">首页</a></li>
        </ul>
    </div>
</div>
<script>
    $(function () {
        $("#orders").click(function () {
            $.ajax({
                url:'/orderServlet',
                data:{action:'orderIndex'},
                success:function (data) {
                    var data=$.parseJSON(data);
                    console.log(data);
                    if(data.success){
                        window.location.href="/orderServlet?action=orderList"
                    }else {
                        alert("请先登录");
                        window.location.href="login.jsp"
                    }
                }
            })
        });
        /*$("#inputBtn").click(function () {
            /!*Ajax发送请求, 是没有办法跳转服务当中的请求
            * 只能通过在浏览器当中来跳转
            * *!/
            var name=$("#s_input").val();
            console.log(name);
            $.ajax({
                url:'/productServlet',
                type:'post',
                dataType:'json',
                data: {action:'getProductByName',name:name},
                success:function (data) {
                    window.location.href='product.jsp'
                }
            });
        });*/
    });
</script>