<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link rel="stylesheet" href="style/common.css">
    <link rel="stylesheet" href="style/regStyle.css">
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="style/footerStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
</head>
<body>

<!--注册头部-->
<div id="reg_header">
    <div class="reg_h_center">

            <img src="images/logo.png" alt="">
            <h3>欢迎注册</h3>

            <div class="reg_h_right">
                <span>已有账户</span><a href="login.html">请登录</a>
            </div>
    </div>
</div>

<div id="reg_main">
        <div class="main_left">
            <form id="reg_form" method="post">
                <div>
                    <label>用户名</label>
                    <input id="username"  type="text" placeholder="请输入用户名..." name="username">
                </div>
                <div>
                    <label>密码</label>
                    <input id="pwd" type="password" placeholder="请输入密码.." name="password">
                </div>
                <div>
                    <label>确认密码</label>
                    <input id="pwd2" type="password" placeholder="请再次输入密码...">
                </div>
                <div>
                    <label>电话</label>
                    <input type="text" placeholder="请输入电话..." name="phone" id="phone">
                </div>
                <div class="check_box">
                    <label>验证码</label>
                    <input type="text" placeholder="请输入验证码..." name="code">
                    <img src="/CheckCodeServlet" onclick="change(this)">
                </div>
                <div class="submit_button">
                    <input type="button" value="立即注册" onclick="checkData()">
                </div>
            </form>
        </div>
        <div class="main_right">
             <img src="images/reg_right.png" alt="">
        </div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript">
        function change(obj) {
            obj.src = "/CheckCodeServlet?time=" + new Date().getTime();
        }
        function checkData() {
            //1.获取用户名， 密码   确认密码
            var username =  document.getElementById("username");
            var pwd =  document.getElementById("pwd");
            var pwd2 =  document.getElementById("pwd2");
            var phone = document.getElementById('phone').value;
            //2.判断输入的内容不能为空
            if(username.value==""){
                layer.alert("请输入用户名");
                return;
            }
            if(pwd.value==""){
                layer.alert("请输入密码");
                return;
            }
            if(pwd2.value==""){
                layer.alert("请再次输入密码");
                return;
            }
            if(!(/^1[3456789]\d{9}$/.test(phone))){
                layer.alert("手机号码有误，请重填");
                return;
            }

            //3。两次密码是否一样
            if(pwd.value == pwd2.value){
                //发送请求  form  获取form
                $.post("/registServlet",$("#reg_form").serialize(),function (data) {
                    /*把data  json格式的字符串  转成 json 数据*/
                    data = $.parseJSON(data);
                    if (data.success){
                        /*跳转到首页*/
                        layer.alert(data.msg);
                        window.location.href = "/login.jsp"
                    } else {
                        layer.alert(data.msg);
                    }
                });
            }else{
                layer.alert("两次输入的密码不一样");
            }
        }
</script>
</body>
</html>


