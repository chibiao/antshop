<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
        <link rel="stylesheet" href="style/footerStyle.css">
	</head>
	<script type="text/javascript" src="js/jquery-1.8.2.min.js" ></script>
    <style>
        #reg_header{
            width: 60%;
            height: 110px;
            margin: auto;
            background: white;
            box-shadow: 5px 0px 15px #ccc;
            overflow: hidden;
        }
        #reg_header .reg_h_center{
            width: 1210px;
            height: 110px;
            margin: 0 auto;

        }
        #reg_header .reg_h_center img{
            width: 160px;
            height: 100px;
            float: left;
        }
        #reg_header h3{
            float: left;
            font-size: 25px;
            font-weight: 400;
            color: #333;
            margin-top: 50px;
        }

        #reg_header .reg_h_right{
            width: 200px;
            height: 110px;
            float: right;
            line-height: 110px;
            padding-top: 20px;
        }
        #reg_header .reg_h_right span{
            color: #6C6C6C;
            font-size: 15px;
            margin-right: 10px;
            float: left;
        }

        #reg_header .reg_h_right a{
            color: #333;
            font-size: 15px;
            display: inline-block;
            float: left;
        }
    </style>
	<style>
		*{margin: 0;padding: 0;text-decoration: none;list-style-type: none;font-family: Times New Roman;}
		img{border:none;}
		a{border:none;text-decoration: none;color:#111;}
		.border{border:1px solid red;}
		.fl{float: left;}
		.fr{float: right;}
		.clear{clear:both;}
		body{
			background-color: ghostwhite;
			width:100%
		}
		
		.shop{
			width: 60%;
			/*height: 100px;*/
			margin: auto;
			
			background-color: white;	
			padding-top: 40px;
			padding-bottom: 40px;	
			overflow: hidden;
		}
		.shop-left{
			width: 720px;
			margin-left: 40px;	
			float: left;	
			padding: 0,20px;
			border: 1px solid #eaeaea;
			
			/*border-top: 1px solid #eaeaea;
			border-left: 1px solid #eaeaea;
			border-bottom: 1px solid #eaeaea;*/
		}
		.shop-left-head{
			width: 760px;
		}
		.shop-left-foot{
			height: 270px;
			width: 760px;
			overflow: hidden;
			
		}
		
		.info{
			width: 60%;
			height: 250px;
			/*height: 100px;*/
			margin: auto;
			background-color: rgba(255,255,255);
			margin-top: 30px;
			text-align: right;
		}
		#pro{
			height: 140px;
			width: 60%;
			padding: 20px 0;
			font-size: 14px;
			border-bottom: 1px solid #eaeaea;
		}
		.price{
			height: 110px;
			width: 210px;
			margin-left: 90px;
			margin-top: 250px;
			text-align: center;
		}
	</style>
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
		<div class="container">
			<div class="shop">
				<div class="shop-left">
					<div class="shop-left-head">
						<div id="pro">
							<table>
								<tr>
									<td width="130px" style="text-align: left;"><img src="img/shop/4.png" style="width: 80px;height: 100px;"/></td>
									<td width="530px" style="text-align: left;">HUAWEI P30 Pro 麒麟980 超感光徕卡四摄 屏内指纹 双景录像 8GB+128GB 全网通版（天空之境）</td>
									<td width="70px" style="text-align: center;">×1</td>
									<td width="200px" style="text-align: center;">¥ 5488.00</td>
								</tr>
							</table>
						</div>
						<div id="pro">
							<table>
								<tr>
									<td width="130px" style="text-align: left;"><img src="img/shop/4.png" style="width: 80px;height: 100px;"/></td>
									<td width="530px" style="text-align: left;">HUAWEI P30 Pro 麒麟980 超感光徕卡四摄 屏内指纹 双景录像 8GB+128GB 全网通版（天空之境）</td>
									<td width="70px" style="text-align: center;">×1</td>
									<td width="200px" style="text-align: center;">¥ 5488.00</td>
								</tr>
							</table>
						</div>
						<div id="pro">
							<table>
								<tr>
									<td width="130px" style="text-align: left;"><img src="img/shop/4.png" style="width: 80px;height: 100px;"/></td>
									<td width="530px" style="text-align: left;">HUAWEI P30 Pro 麒麟980 超感光徕卡四摄 屏内指纹 双景录像 8GB+128GB 全网通版（天空之境）</td>
									<td width="70px" style="text-align: center;">×1</td>
									<td width="200px" style="text-align: center;">¥ 5488.00</td>
								</tr>
							</table>
						</div>
						<div id="pro">
							<table>
								<tr>
									<td width="130px" style="text-align: left;"><img src="img/shop/4.png" style="width: 80px;height: 100px;"/></td>
									<td width="530px" style="text-align: left;">HUAWEI P30 Pro 麒麟980 超感光徕卡四摄 屏内指纹 双景录像 8GB+128GB 全网通版（天空之境）</td>
									<td width="70px" style="text-align: center;">×1</td>
									<td width="200px" style="text-align: center;">¥ 5488.00</td>
								</tr>
							</table>
						</div>
					</div>
					
				</div>
				
				
					<div class="price">
						
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="info">
				<form action="" method="post">
					<span style="line-height: 60px;margin-right: 40px;font-size: 25px;">应付总额:
						<font color="#CA141D" style="font-size: 45px;"><span id="total">￥99999</span></font></span><br />
					<span style="line-height: 40px;margin-right: 40px;font-size: 15px;">可获得积分:300</span><br />
					<span style="line-height: 50px;margin-right: 40px;font-size: 20px;">配送至:
						<font style="font-size: 15px;"> <input type="text" name="addr" style="width: 250px;height: 20px;" value="河北省唐山市华岩北路唐山学院南校区"/></font></span><br />
					收件人:<input type="text" name="name" value="猪八戒" style="width: 50px;height: 20px;"/>电话:<input type="text" name="phone" value="10086"style="width: 80px;height: 20px;margin-right: 40px;"/><br />
					<input type="submit" style="height: 50px;width: 170px;margin-top: 10px;margin-right: 40px;"/><br />					
				</form>
			
		</div>
		<%@include file="footer.jsp"%>
	</body>
</html>
