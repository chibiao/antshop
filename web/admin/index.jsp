<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>权限管理系统</title>
    <%@include file="./static/common/common.jsp"%>
    <script type="text/javascript" src="/admin/static/js/index.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px;background: #6b9cde; padding: 20px 20px">
    <img src="admin/static/images/main_logo.png" alt="">
    <div style="position: absolute; right: 50px; top: 30px;">
        <img src="admin/static/images/user.png" style="vertical-align: middle; margin-right: 10px;" >
        <%--显示当前登录用户名--%>
        <span style="color: white; font-size: 20px; margin-right: 5px;">${admin.name}</span>
        <%--取消认证  跳转到 登录页面  在shiro配置文件当中  配置   /logout = logout --%>
        <a style="font-size: 18px; color: white;text-decoration: none;" href="/adminLoginServlet?action=logout">注销</a>
    </div>
</div>
<div data-options="region:'south'" style="height:50px; border-bottom: 3px solid #6b9cde">
    <p align="center" style="font-size: 14px">唐山学院</p>
</div>
<div data-options="region:'west',split:true" style="width:200px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div title="菜单" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
            <!--tree-->
            <ul id="tree"></ul>
        </div>
        <div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;">
            content2
        </div>
        <div title="Title3">
            content3
        </div>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <!--标签-->
    <div id="tabs" style="overflow: hidden">
    </div>
</div>
</body>
</html>
