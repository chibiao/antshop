<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./static/common/common.jsp"%>
    <script type="text/javascript" src="./admin/static/js/order.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="user_datagrid">
</table>
<!-- 数据表格CRUD按钮 -->
<div id="user_toolbar">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">发货</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="reload">刷新</a>
    </div>
</div>
<div id="user_dialog">
    <form id="user_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id"><%--为了做编辑--%>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
                <td name="passwordbox">密码</td>
                <td name="passwordbox"><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="name"></td>
                <td>电话</td>
                <td><input type="tel" name="phone"></td>
            </tr>
            <tr>
                <td>电子邮箱</td>
                <td><input type="email" name="email"></td>
                <td>住址</td>
                <td><input type="text" name="addr"></td>
            </tr>
            <tr>
                <td>是否可用</td>
                <td colspan="2"><input type="text" name="state" id="state" class="easyui-combobox" placeholder="是否禁用"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="user_dialog_bt">
    <a id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>

