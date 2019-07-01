<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link rel="stylesheet" href="style/regStyle.css">
    <script src="layui/layui.all.js" charset="utf-8"></script>
    <script src="js/jquery-1.11.0.js"></script>
</head>

<body>
<div id="reg_header">
    <div class="reg_h_center">
        <img src="images/logo.png" alt="">
        <h3>修改个人信息</h3>
        <div class="reg_h_right">
            <span></span><a href="index.jsp">返回首页</a>
        </div>
    </div>
</div>
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-header">设置我的资料</div>
        <div class="layui-card-body" pad15="">
            <form method="post" action="/userServlet?action=updateUserDetail" class="layui-form" lay-filter="">
                <div class="layui-form-item">
                    <label class="layui-form-label">我的角色</label>
                    <div class="layui-input-inline">
                        <select name="role" lay-verify="">
                            <option value="1" selected="">买家</option>
                            <option value="2" disabled="">普通管理员</option>
                            <option value="3" disabled="">审核员</option>
                            <option value="4" disabled="">编辑人员</option>
                        </select><div class="layui-unselect layui-form-select"><div class="layui-select-title"><input type="text" placeholder="请选择" value="买家" readonly="" class="layui-input layui-unselect"><i class="layui-edge"></i></div><dl class="layui-anim layui-anim-upbit"><dd lay-value="1" class="layui-this">超级管理员</dd><dd lay-value="2" class=" layui-disabled">普通管理员</dd><dd lay-value="3" class=" layui-disabled">审核员</dd><dd lay-value="4" class=" layui-disabled">编辑人员</dd></dl></div>
                    </div>
                    <div class="layui-form-mid layui-word-aux">当前角色不可更改为其它角色</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" value="${user.username}" readonly="" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" value="${user.name}" lay-verify="name" autocomplete="off" placeholder="请输入昵称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" value="${user.phone}" lay-verify="phone" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" value="${user.email}" lay-verify="email" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">我的地址</label>
                    <div class="layui-input-inline">
                        <input type="text" name="addr" value="${user.addr}"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="setmyinfo">确认修改</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                    </div>
                </div>
            </form >
        </div>
    </div>
</div>
<script>
    $(function() {
    });
</script>
</body>

</html>