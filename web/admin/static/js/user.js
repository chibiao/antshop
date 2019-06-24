$(function () {
    $("#user_datagrid").datagrid({
        url: "/userServlet?action=userList",
        columns: [[
            {field: 'username', title: '用户名', width: 100, align: 'center'},
            {field: 'name', title: '真实姓名', width: 100, align: 'center'},
            {field: 'email', title: '电子邮箱', width: 100, align: 'center'},
            {field: 'phone', title: '电话', width: 100, align: 'center'},
            {field: 'addr', title: '住址', width: 100, align: 'center'},
            {field: 'state', title: '是否可用', width: 100, align: 'center',formatter: function(value,row,index){
                    if(row.state){
                        return "是";
                    }else {
                        return "<font color='red'>否</font>";
                    }
            }}
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#user_toolbar',
        onClickRow: function (rowIndex, rowData) {
            /*判断当前行是否是离职状态*/
            if (!rowData.state) {
                /*离职,把离职按钮禁用*/
                $("#del").linkbutton("disable");
            } else {
                /*离职,把离职按钮启用*/
                $("#del").linkbutton("enable");
            }
        }
    });
    /*
     * 初始化新增/编辑对话框
     */
    $("#user_dialog").dialog({
        width: 500,
        height: 300,
        closed: true,
        buttons: '#user_dialog_bt'
    });
    /*监听添加按钮的点击*/
    $("#add").click(function () {
        $("#user_dialog").dialog("open");
        $("#user_dialog").dialog("setTitle", "添加用户");
        $("#user_dialog").form("clear");
        $("[name='passwordbox']").show();
        $("[name='password']").validatebox({required: true});
        $("[name='email']").validatebox({required: true,validType: 'email'});
    });
    /*监听编辑按钮的点击*/
    $("#edit").click(function () {
        $("#user_form").form("clear");
        /*获取当前选中的行*/
        $("[name='password']").validatebox({required: false});
        $("[name='passwordbox']").hide();
        var rowData = $("#user_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        rowData["state"] = rowData["state"] + "";
        $("#user_dialog").dialog("setTitle", "修改用户");
        $("#user_dialog").dialog("open");
        /*选中的数据回显*/
        $("#user_form").form("load",rowData);
    });
    /*监听保存按钮*/
    $("#save").click(function () {
        /*判断当前是添加 还是编辑 编辑操作带有id*/
        var id = $("[name='id']").val();
        var url;
        if (id) {
            /*编辑*/
            url = "/userServlet?action=updateUser";
        } else {
            /*添加*/
            url = "/userServlet?action=addUser";
        }
        /*提交表单*/
        $("#user_form").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg);
                    /*关闭对话框*/
                    $("#user_dialog").dialog("close");
                    /*重新加载数据*/
                    $("#user_datagrid").datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", data.msg);
                }
            }
        });
    });
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#user_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否做删除操作", function (res) {
            if (res) {
                $.get("/userServlet?action=updateUserState&id=" + rowData.id, function (data) {
                    /*get请求返回的是json数据  不需要解析*/
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*重新加载数据表格*/
                        $("#user_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
    /*管理员选择 下拉列表*/
    $("#state").combobox({
        width: 150,
        panelHeight: 'auto',
        valueField: 'value',
        textField: 'label',
        editable: false,
        data: [{
            label: '是',
            value: 'true'
        }, {
            label: '否',
            value: 'false'
        }],
        onLoadSuccess: function () { /*数据加载完毕之后回调*/
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*监听搜索按钮点击*/
    $("#searchbtn").click(function () {
        /*获取搜索框的内容*/
        var keyword = $("[name='keyword']").val();
        /*重新加载列表 把参数传过去*/
        $("#user_datagrid").datagrid("load", {keyword: keyword})
    });
    /*监听刷新按钮的点击*/
    $("#reload").click(function () {
        /*清空搜索框*/
        var keyword = $("[name='keyword']").val('');
        /*重新加载*/
        $("#user_datagrid").datagrid("load", {})
    });
    $("#cancel").click(function () {
        $("#user_dialog").dialog("close");
    });
});