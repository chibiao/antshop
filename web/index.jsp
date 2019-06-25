<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>码蚁商城</title>
    <!--链接外部样式-->
    <!--设置标签图标-->
    <link rel="stylesheet" href="style/index.css">
    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<!-- 通过jsp指令导入头部 -->
<%@include file="header.jsp" %>
    <!-- 中部设计 -->
 <div class="header">
	<div class="menu-bar">
		<div class="view">
			<div class="category">
				<h2>商品分类</h2>
				<ul class="category-option" id="category_option">
				</ul>
			</div>
		</div>
	</div>
</div>
<!--轮播-->
<div class="scroll-banner">
	<ul class="scroll-content">
		<li class="scroll-item" style="background:white;display: block;">
			<div class="scroll-index">
				<a href="#">
					<img class="sc-big fadeInR" src="./images/index/banner03.jpg">
				</a>
			</div>
		</li>
		<li class="scroll-item" style="background:white">
			<div class="scroll-index">
				<a href="#">
					<img class="sc-big fadeInR" src="./images/index/banner04.jpg">
				</a>
									
			</div>
		</li>
		<li class="scroll-item" style="background:white">
			<div class="scroll-index">
				<a href="#">
					<img class="sc-big fadeInR" src="./images/index/1444876754.9431_副本.jpg">
				</a>
								
			</div>
		</li>
		<li class="scroll-item" style="background:white">
			<div class="scroll-index">
				<a href="#">
					<img class="sc-big fadeInR" src="./images/index/ad44d0d7d97b3de4da9a7c0dd43bc81f_副本.jpg">
				</a>
								
			</div>
		</li>
		<li class="scroll-item" style="background:white">
			<div class="scroll-index">
				<a href="#">
					<img class="sc-big fadeInR" src="./images/index/5adfca5fcf774f81b227533f2fb62239_副本.jpg">
				</a>
									
			</div>
		</li>
	</ul>
	<div class="scroll-btn">
		<span class="current"></span>
		<span></span>
		<span></span>
		<span></span>
		<span></span>
	</div>
</div>
<script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script src="./js/jquery.SuperSlide.2.1.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	//左侧分类导航
	$('.category-option .cat-item').hover(function(){$(this).toggleClass('hover')})
	//图片轮播
	jQuery(".scroll-banner").slide({mainCell:".scroll-content",titCell:".scroll-btn span",titOnClassName:"current",effect:"fold",autoPlay:true,delayTime:1000,interTime:3500});
</script>
 
<!--广告页-->

<!--秒杀-->
<div id="ms">
    <div class="ms_title">
        <span>码蚁秒杀</span>
        <span>总有你想不到的低价</span>
    </div>

    <div class="ms_body">
        <ul>
            <li>
                <a href="detail.html">
                    <img src="images/goods/good1.png" alt="">
                    <p>小米（MI）小米净化器2智能家用卧室空气净化器除甲醛雾霾P</p>
                    <i class="yuan">￥</i><span class="price">599</span>
                </a>
            </li>
            <li>
                <a href="">
                    <img src="images/goods/goods2.png" alt="">
                    <p>小米（MI）小米净化器2智能家用卧室空气净化器除甲醛雾霾P</p>
                    <i class="yuan">￥</i><span class="price">599</span>
                </a>
            </li>
            <li>
                <a href="">
                    <img src="images/goods/goods3.png" alt="">
                    <p>小米（MI）小米净化器2智能家用卧室空气净化器除甲醛雾霾P</p>
                    <i class="yuan">￥</i><span class="price">599</span>
                </a>
            </li>
            <li>
                <a href="">
                    <img src="images/goods/goods4.png" alt="">
                    <p>小米（MI）小米净化器2智能家用卧室空气净化器除甲醛雾霾P</p>
                    <i class="yuan">￥</i><span class="price">599</span>
                </a>
            </li>
            <li>
                <a href="">
                    <img src="images/goods/goods5.png" alt="">
                    <p>小米（MI）小米净化器2智能家用卧室空气净化器除甲醛雾霾P</p>
                    <i class="yuan">￥</i><span class="price">599</span>
                </a>
            </li>
        </ul>
    </div>
</div>

<!--热买商品-->
<div id="hot_goods">
    <h3 class="hot_goods_title">热卖商品</h3>
    <div class="hot_goods_body">
        <ul id="hot_goodsList">
            <%--<c:if test="${empty products }">
                没有商品
            </c:if>
            <c:if test="${!empty products }">
                <c:forEach items="${products }" var="product">
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
            </c:if>--%>
        </ul>

    </div>
</div>
<%@include file="footer.jsp" %>
</body>
<script>
    var currentlidli =null;
    function mouse(lid,id,flag){
        var lidli =document.getElementById(lid);
        var subleft1 =document.getElementById('subleft1');
        var dis=document.getElementById(id);
        console.log(lidli);
        console.log(currentlidli);
        if(flag==1){
            subleft1.style.display="block";
            if(dis){
                subleft1.innerHTML=dis.innerHTML;
                lidli.style.backgroundColor ="#001";
                currentlidli =lidli;
            }
            if(!lidli){
                currentlidli.style.backgroundColor ="red";
            }
        }else{
            subleft1.style.display="none";
            currentlidli.style.backgroundColor ="";
            if(lidli){
                lidli.style.backgroundColor ="";
            }
        }
    }
</script>

</html>


