$(function () {
    $("#admin_datagrid").datagrid({
        url: "/adminServlet?action=adminList",
        columns: [[
            {field: 'anum', title: '管理员编号', width: 100, align: 'center'},
            {field: 'name', title: '管理员名称', width: 100, align: 'center'}
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#admin_toolbar'
    });
    /*
     * 初始化新增/编辑对话框
     */
    $("#admin_dialog").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#admin_dialog_bt'
    });
    /*监听添加按钮的点击*/
    $("#add").click(function () {
        $("#admin_dialog").dialog("open");
        $("#admin_dialog").dialog("setTitle", "添加管理员");
        $("#admin_dialog").form("clear");
        $("#password").show();
        $("[name='password']").validatebox({required: true});
    });
    /*监听编辑按钮的点击*/
    $("#edit").click(function () {
        $("#admin_form").form("clear");
        $("#password").hide();
        /*获取当前选中的行*/
        var rowData = $("#admin_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }

        $("#admin_dialog").dialog("setTitle", "修改管理员");
        $("#admin_dialog").dialog("open");
        /*选中的数据回显*/
        $("#admin_form").form("load", rowData);
    });
    /*监听保存按钮*/
    $("#save").click(function () {
        /*判断当前是添加 还是编辑 编辑操作带有id*/
        var id = $("[name='id']").val();
        var url;
        if (id) {
            /*编辑*/
            url = "/adminServlet?action=updateAdmin";
        } else {
            /*添加*/
            url = "/adminServlet?action=addAdmin";
        }
        /*提交表单*/
        $("#admin_form").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg);
                    /*关闭对话框*/
                    $("#admin_dialog").dialog("close");
                    /*重新加载数据*/
                    $("#admin_datagrid").datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", data.msg);
                }
            }
        });
    });
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#admin_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否做删除操作", function (res) {
            if (res) {
                $.get("/adminServlet?action=deleteAdmin&id=" + rowData.id, function (data) {
                    /*get请求返回的是json数据  不需要解析*/
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*重新加载数据表格*/
                        $("#admin_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
    $("#cancel").click(function () {
        $("#admin_dialog").dialog("close");
    });
    /*监听刷新按钮的点击*/
    $("#reload").click(function () {
        /*重新加载*/
        $("#admin_datagrid").datagrid("load", {})
    });
});