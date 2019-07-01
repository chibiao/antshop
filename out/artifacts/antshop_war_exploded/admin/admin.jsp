<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./static/common/common.jsp"%>
    <script type="text/javascript" src="/admin/static/js/admin.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="admin_datagrid">
</table>
<!-- 数据表格CRUD按钮 -->
<div id="admin_toolbar">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="reload">刷新</a>
    </div>
</div>
<div id="admin_dialog">
    <form id="admin_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id"><%--为了做编辑--%>
            <tr>
                <td>管理员编号</td>
                <td><input type="text" name="anum"></td>
            </tr>
            <tr>
                <td>管理员名称</td>
                <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" required="required"></td>
            </tr>
            <tr id="password">
                <td>管理员密码</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>角色:</td>
                <td><input id="role" name="role.rid" placeholder="请选择角色"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="admin_dialog_bt">
    <a id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
