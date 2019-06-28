<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./static/common/common.jsp" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/admin/static/plugins/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/admin/static/plugins/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="/admin/static/js/product.js"></script>
</head>
<body>
<div id="tb">
    <c:forEach items="${admin.permissions}" var="permission">
        <c:if test="${permission.presource=='product:add' }">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
        </c:if>
    </c:forEach>
    <c:forEach items="${admin.permissions}" var="permission">
        <c:if test="${permission.presource=='product:edit' }">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
        </c:if>
    </c:forEach>
    <c:forEach items="${admin.permissions}" var="permission">
        <c:if test="${permission.presource=='product:delete' }">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="del">下架</a>
        </c:if>
    </c:forEach>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
    <input type="text" name="keyword" style="width: 200px; height: 30px;padding-left: 5px;">
    <a class="easyui-linkbutton" iconCls="icon-search" id="searchbtn">查询</a>
</div>
<table id="dg"></table>
<div id="dialog">
    <form id="myform" method="post">
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 0 10px">
            <tr>
                <td>商品名称:</td>
                <td><input type="text" name="name" id="name" class="easyui-validatebox" data-options="required:true"></td>
                <td>是否热门:</td>
                <td><input type="text" name="isHot" id="isHot" class="easyui-combobox" placeholder="是否热门"/></td>
            </tr>
            <tr>
                <td>市场价格:</td>
                <td><input type="text" id="marketPrice" name="marketPrice" class="easyui-validatebox" data-options="required:true"></td>
                <td>商店价格:</td>
                <td><input type="text" id="shopPrice" name="shopPrice" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr id="statebox">
                <td>是否上架</td>
                <td><input type="text" name="state" id="state" class="easyui-combobox" placeholder="是否上架"/></td>
            </tr>
            <tr>
                <td>所属分类</td>
                <td><input type="text" name="scid" id="secondCategory" class="easyui-combobox" placeholder="所属分类"/></td>
            </tr>
            <tr>
                <td>商品图片:</td>
                <td>
                    <input form="upload" type="file" name="photo" id="image" class="easyui-validatebox"/>
                </td>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td colspan="3">
                    <textarea id="editor_id" name="description" style="width:300px;height:300px;"
                              placeholder="这里输入内容...">
                    </textarea>
                </td>
            </tr>
        </table>
    </form>
    <form id="upload" enctype="multipart/form-data" method="post">
    </form>
</div>
</body>
</html>
