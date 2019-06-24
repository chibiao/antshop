$(function () {
    $("#category_datagrid").datagrid({
        url: "/secondCategoryServlet?action=secondCategoryList",
        columns: [[
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {
                field: 'parent', title: '父菜单', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            }
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#category_toolbar'
    });
    /*
     * 初始化新增/编辑对话框
     */
    $("#category_dialog").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#category_dialog_bt'
    });
    /*监听添加按钮的点击*/
    $("#add").click(function () {
        $("#category_dialog").dialog("open");
        $("#category_dialog").dialog("setTitle", "添加商品分类");
        $("#category_dialog").form("clear");
    });
    /*监听编辑按钮的点击*/
    $("#edit").click(function () {
        $("#category_form").form("clear");
        /*获取当前选中的行*/
        var rowData = $("#category_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        if(rowData.parent){
            /*回显父级菜单*/
            rowData["parent.id"] = rowData.parent.id;
        }else {/*回显placeholder*/
            $("#parentCategory").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
        $("#category_dialog").dialog("setTitle", "编辑菜单");
        $("#category_dialog").dialog("open");
        /*选中的数据回显*/
        $("#category_form").form("load",rowData);
    });
    /*父菜单 下拉列表*/
    $("#parentCategory").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "/categoryServlet?action=allCategory",
        textField: 'name',
        valueField: 'id',
        onLoadSuccess: function () { /*数据加载完毕之后回调*/
            $("#parentCategory").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#category_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否做删除操作", function (res) {
            if (res) {
                $.get("/categoryServlet?action=deleteCategory&id=" + rowData.id, function (data) {
                    /*get请求返回的是json数据  不需要解析*/
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*重新加载数据表格*/
                        $("#category_datagrid").datagrid("reload");
                        $("#parentCategory").combobox("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
    /*监听保存按钮*/
    $("#save").click(function () {
        /*判断当前是添加 还是编辑 编辑操作带有id*/
        var id = $("[name='id']").val();
        var url;
        if (id) {
            /*编辑*/
            url = "/secondCategoryServlet?action=updateSecondCategory";
        } else {
            /*添加*/
            url = "/secondCategoryServlet?action=addSecondCategory";
        }
        /*提交表单*/
        $("#category_form").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg);
                    /*关闭对话框*/
                    $("#category_dialog").dialog("close");
                    /*重新加载数据*/
                    $("#category_datagrid").datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", data.msg);
                }
            }
        });
    });
    $("#reload").click(function () {
        /*重新加载*/
        $("#category_datagrid").datagrid("load")
    });
    $("#cancel").click(function () {
        $("#category_dialog").dialog("close");
    });
});