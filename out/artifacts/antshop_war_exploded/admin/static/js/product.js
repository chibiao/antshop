$(function () {
    $("#dg").datagrid({
        url: "/productServlet?action=productList",
        columns: [[
            {
                field: 'image', title: '商品图片', width: 100, align: 'center', formatter: function (value, row, index) {
                    return "<img src='/upload/image/" + row.image + "' style=width:80px;height:80px>";
                }
            },
            {field: 'name', title: '商品名称', width: 100, align: 'center'},
            {field: 'marketPrice', title: '商品价格', width: 100, align: 'center'},
            {
                field: 'isHot', title: '是否热门', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.isHot) {
                        return "<font color='red'>是</font>";
                    } else {
                        return "否";
                    }
                }
            },
            {field: 'onlinetime', title: '上架时间', width: 100, align: 'center'},
            {
                field: 'secondCategory',
                title: '所属分类',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.state) {
                        return "在线";
                    } else {
                        return "<font color='red'>已下架</font>";
                    }
                }
            }
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        pagination: true
        /*onClickRow: function (rowIndex, rowData) {
            /!*判断当前行是否是离职状态*!/
            if (!rowData.state) {
                /!*离职,把离职按钮禁用*!/
                $("#del").linkbutton("disable");
            } else {
                /!*离职,把离职按钮启用*!/
                $("#del").linkbutton("enable");
            }
        }*/
    });
    /*对话框 */
    $("#dialog").dialog({
        fit: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                /*区分提交地址url*/
                var id = $("[name='id']").val();
                var url;
                if (id) {
                    url = "/productServlet?action=updateProduct";
                } else {
                    url = "/productServlet?action=addProduct";
                }
                $("#upload").form("submit", {
                    url: "/productServlet?action=upload",
                    success: function (d) {
                        d = $.parseJSON(d);
                        if (d.success) {
                            /*提交表单*/
                            $("#myform").form("submit", {
                                url: url,
                                success: function (data) {
                                    /*解析成json*/
                                    data = $.parseJSON(data);
                                    if (data.success) {
                                        $.messager.alert("温馨提示", data.msg);
                                        $("#dg").datagrid("reload");
                                        $("#dialog").dialog("close");
                                    } else {
                                        $.messager.alert("温馨提示", data.msg);
                                    }
                                }
                            });
                        } else {
                            alert("上传失败")
                        }
                    }
                });

            }
        }, {
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function () {
                $("#dialog").dialog("close");
            }
        }],
        closed: true
    });
    /*监听添加按钮的点击*/
    $("#add").click(function () {
        $("#dialog").dialog("open");
        $("#dialog").dialog("setTitle", "添加商品");
        $("#statebox").hide();
        KindEditor.html("#editor_id", "");
        $("#dialog").form("clear");
    });
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行下架");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否做下架操作", function (res) {
            if (res) {
                $.get("/productServlet?action=upadateProductState&id=" + rowData.id, function (data) {
                    /*get请求返回的是json数据  不需要解析*/
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
    $("#edit").click(function () {
        $("#myform").form("clear");
        /*获取当前选中的行*/
        $("#statebox").show();
        var rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        if (rowData.secondCategory) {
            /*回显父级菜单*/
            rowData["secondCategory.id"] = rowData.secondCategory.id;
        } else {/*回显placeholder*/
            $("#secondCategory").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
        rowData["isHot"] = rowData["isHot"] + "";
        rowData["state"] = rowData["state"] + "";
        $("#dialog").dialog("setTitle", "编辑菜单");
        $("#dialog").dialog("open");
        /*选中的数据回显*/
        KindEditor.html("#editor_id", rowData["description"]);
        $("#name").val(rowData["name"]);
        $("#marketPrice").val(rowData["marketPrice"]);
        $("#shopPrice").val(rowData["shopPrice"]);
        $("#myform").form("load", rowData);
    });

    /*父菜单 下拉列表*/
    $("#secondCategory").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "/secondCategoryServlet?action=AllSecondCategory",
        textField: 'name',
        valueField: 'id',
        onLoadSuccess: function () { /*数据加载完毕之后回调*/
            $("#secondCategory").each(function (i) {
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
        $("#dg").datagrid("load", {keyword: keyword})
    });
    /*管理员选择 下拉列表*/
    $("#isHot").combobox({
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
            $("#isHot").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*管理员选择 下拉列表*/
    $("#state").combobox({
        width: 150,
        panelHeight: 'auto',
        valueField: 'value',
        textField: 'text',
        editable: false,
        data: [{
            text: '是',
            value: 'true'
        }, {
            text: '否',
            value: 'false'
        }],
        onLoadSuccess: function () { /*数据加载完毕之后回调*/
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                console.log(targetInput);
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    $("#reload").click(function () {
        /*清空搜索框*/
        var keyword = $("[name='keyword']").val('');
        /*重新加载*/
        $("#dg").datagrid("load", {})
    });
});
KindEditor.ready(function (K) {
    window.editor = K.create('#editor_id', {
        resizeType: 0,
        allowPreviewEmoticons: true,
        uploadJson: 'admin/static/plugins/kindeditor/jsp/upload_json.jsp',
        //uploadJson : "ajax/uploadImg.do",//需要加上basePath, 不然批量上传调用action时会出现路径问题
        fileManagerJson: 'admin/static/plugins/kindeditor/jsp/file_manager_json.jsp',
        allowImageUpload: true,
        allowFileManager: true,
        afterChange: function () {
            this.sync();
        }
    });
});