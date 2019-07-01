<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <!--链接外部样式-->
    <link rel="stylesheet" href="style/headerStyle.css">
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="style/footerStyle.css">
    <link rel="stylesheet" href="style/detail.css">
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="js/jquery-1.11.0.js"></script>
    <script src="layui/layui.all.js" charset="utf-8"></script>
    <script src="js/header.js"></script>
	<script>
	function add(index){
	    document.getElementById(index+'num').value = document.getElementById(index+'num').value*1+1;

	  }
	function minx(index){
        if(document.getElementById(index+'num').value>1){
            document.getElementById(index+'num').value = document.getElementById(index+'num').value*1-1;
        }
	  }
	</script>
</head>
<body style="background-color: white">
<!-- 通过jsp指令导入头部 -->
<%@include file="header.jsp" %>

<!--详情展示-->
<div id="detail_show">
    <!--两个进行平分-->
    <input type="hidden" id="id" value="${product.id}">
    <div class="show_left">
        <img src="upload/image/${product.image}" alt="">
    </div>
    <div class="show_right">
        <h3 class="good_name">${product.name}</h3>
        <div class="goods_price">
            <p class="ori_price">原价：<i>￥</i><span>${product.marketPrice}</span><em></em></p>
            <p class="now_price">价格： <i>￥</i><span>${product.shopPrice}</span></p>
        </div>
        
        <div class="goods_count">
				<p><lable>购买数量</lable>
					<input type="button" id='1' width=50px value="-" onclick="minx(1)">
					<input type="text" id='1num' name="T1" size="20" value="1" disabled="disabled">
					<input type="button" id='1' width=50px value="+" onclick="add(1)">
				</p>
			</div>
        <div class="add_cart">
            <input type="submit" value="加入购物车" id="submit">
        </div>
    </div>
</div>

<!--商品详情-->
<div id="goods_detail">
    <div class="detail_header">商品详情</div>
    <div class="detail_body">
        ${product.description}
        <%--<img src="images/detail_pic1.png" alt="">
        <img src="images/detail_pic2.png" alt="">--%>
    </div>
</div>


<!--尾部-->
<div id="footer">
    <!--关于我们-->
    <div class="link">
        <a href="#">关于我们</a>
        |
        <a href="#">联系我们</a>
        |
        <a href="#">人才招聘</a>
        |
        <a href="#">商家入驻</a>
        |
        <a href="#">广告服务</a>
        |
        <a href="#">手机码蚁</a>
        |
        <a href="#">友情链接</a>
        |
        <a href="#">销售联盟</a>
        |
        <a href="#">码蚁社区
        </a>
        |
        <a href="#">码蚁公益</a>
    </div>
    <!--版权-->
    <div class="copyright">
        Copyright&nbsp;&copy;&nbsp;2017-2018&nbsp;&nbsp;码蚁My.com&nbsp;版权所有
    </div>
</div>
<script>
    $(function () {
        $("#submit").click(function () {
            var id=$("#id").val();
            var count=$("#1num").val();
            $.ajax({
                url: 'cartServlet',
                type: 'POST',
                data: {action: 'addToCart',id:id,count:count},
                success:function (data) {
                    data = $.parseJSON(data);
                    if (data.success){
                        layer.msg(data.msg);
                    }else {
                        layer.msg(data.msg);
                    }
                }
            })
        })
    })
</script>



</body>
</html>