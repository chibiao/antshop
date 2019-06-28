<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>码蚁商城结算</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link rel="stylesheet" href="style/common.css">
    <link rel="stylesheet" href="style/regStyle.css">
    <link rel="stylesheet" href="style/footerStyle.css">
    <script src="js/jquery-1.11.0.js"></script>
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="js/header.js"></script>
</head>
<body>
<div id="reg_header">
    <div class="reg_h_center">

        <img src="images/logo.png" alt="">
        <h3>我的订单</h3>

        <div class="reg_h_right">
            <span>继续购物</span><a href="index.jsp">返回首页</a>
        </div>
    </div>
</div>
<c:forEach items="${orders }" var="order">
    <div class="layui-form" style="width: 1200px;margin:0 auto">
        <table class="layui-table" lay-skin="line">
            <colgroup>
                <col width="150">
                <col width="350">
                <col width="150">
                <col width="200">
                <col width="150">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th><input type="checkbox" name="like1[write]  selectAll" lay-skin="primary">${order.ordertime}</th>
                <th>订单号${order.uuid}</th>
                <th>电脑办公</th>
                <th>和我联系</th>
                <th>总计</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:forEach items="${order.orderItems }" var="orderItem" varStatus="status">
            <tr>
                <th><img src="/upload/image/${orderItem.product.image}" alt="" width="50px" height="50px"></th>
                <th>
                    <div>
                        <a href="#" class="layui-a layui-a-normal">${orderItem.product.name}</a>
                    </div>
                </th>
                <th>
                    <span><del>￥${orderItem.product.marketPrice}</del></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1<br>
                    <span style="color: red">￥${orderItem.product.shopPrice}</span>
                </th>
                <th><a href="">投诉店家</a><br><a href="">退运费险</a></th>
                <c:if test="${status.index==0}">
                    <th rowspan="${fn:length(order.orderItems)+1}"><span>${order.totalPrice}</span></th>
                    <th rowspan="${fn:length(order.orderItems)+1}">
                        <span>
                            <a href="/orderServlet?action=updatePayState&orderId=${order.uuid}">
                                <c:if test="${order.payState==false}">待付款</c:if>
                                <c:if test="${order.payState==true&&order.sendState==false}">待发货</c:if>
                                <c:if test="${order.payState==true&&order.sendState==true}">已发货</c:if>
                            </a>
                        </span>
                        <br>
                        <span><a href="#" class="update" id="${order.uuid}">修改信息</a></span>
                    </th>
                </c:if>
            </tr>
            </c:forEach>
            <tr>
                <th colspan="2"><span>保险服务</span></th>
                <th colspan="2"><span>￥0</span></th>
            </tr>
        </table>
    </div>
</c:forEach>
<%@include file="footer.jsp" %>
<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var uuid;
        //表单初始赋值
        $(".update").click(function () {
            uuid=$(this).attr("id");
            layer.open({
                type: 2,
                content: 'updateOrder.jsp', //这里content是一个普通的String
                title:'修改收货信息',
                area:'500px',
                btn:'修改',
                yes: function(){
                    var pIframe = $('iframe')[0].contentWindow.document;
                    var name=$(pIframe).find("#name").val();
                    var addr=$(pIframe).find("#addr").val();
                    var phone=$(pIframe).find("#phone").val();
                    var uuid=$(pIframe).find("#uuid").val();
                    $.ajax({
                        url:"orderServlet",
                        data:{action:'updateMessage',uuid:uuid,name:name,addr:addr,phone:phone},
                        success:function (data) {
                            data = $.parseJSON(data);
                            if (data.success){
                                layer.msg(data.msg);
                            }
                        }
                    });
                    console.log(name,addr,phone,uuid);
                    layer.closeAll();
                },
                success:function (index, layero) {
                    var pIframe = $('iframe')[0].contentWindow.document;//找到上一级父页面
                    $.ajax({
                        url:"orderServlet",
                        data:{action:'getMessage',uuid:uuid},
                        success:function (data) {
                            data = $.parseJSON(data);
                            console.log(data);
                            $(pIframe).find("#name").val(data.name);
                            $(pIframe).find("#addr").val(data.addr);
                            $(pIframe).find("#phone").val(data.phone);
                            $(pIframe).find("#uuid").val(data.uuid);
                        }
                    });
                }
            });
        });
    });
</script>
</body>
</html>