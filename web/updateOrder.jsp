<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="js/jquery-1.11.0.js"></script>
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="js/header.js"></script>
</head>
<body>
<form class="layui-form" action="" id="form">
    <input type="hidden" id="uuid">
    <div class="layui-form-item">
        <label class="layui-form-label">收货人姓名:</label>
        <div class="layui-input-block">
            <input type="text" name="name" id="name" placeholder="请输入姓名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">收货地址:</label>
        <div class="layui-input-block">
            <input type="text" name="addr" id="addr" placeholder="请输入收货地址" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话:</label>
        <div class="layui-input-block">
            <input type="text" name="phone" id="phone" placeholder="请输入联系电话" class="layui-input">
        </div>
    </div>
</form>
<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
    });
</script>
</body>
</html>