$(function () {
    $("#user_datagrid").datagrid({
        url: "/orderServlet?action=orderAdminList",
        columns: [[
            {field: 'uuid', title: '订单号', width: 100, align: 'center'},
            {field: 'name', title: '真实姓名', width: 100, align: 'center'},
            {field: 'phone', title: '电话', width: 100, align: 'center'},
            {field: 'addr', title: '住址', width: 100, align: 'center'},
            {field: 'ordertime', title: '下单时间', width: 100, align: 'center'},
            {field: 'payState', title: '付款状态', width: 100, align: 'center',formatter: function(value,row,index){
                    if(row.payState){
                        return "已付款";
                    }else {
                        return "<font color='red'>待付款</font>";
                    }
            }},
            {field: 'sendState', title: '发货状态', width: 100, align: 'center',formatter: function(value,row,index){
                    if(row.sendState){
                        return "已发货";
                    }else {
                        return "<font color='red'>待发货</font>";
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
            if (!rowData.payState||rowData.sendState) {
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
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#user_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行发货");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否发货", function (res) {
            if (res) {
                $.get("/orderServlet?action=updateSendState&uuid=" + rowData.uuid, function (data) {
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
    /*监听刷新按钮的点击*/
    $("#reload").click(function () {
        /*清空搜索框*/
        /*重新加载*/
        $("#user_datagrid").datagrid("load")
    });
});