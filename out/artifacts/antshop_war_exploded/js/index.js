$(function () {
    async1();
});
function async1(){
    $.ajax(
        {
            url: 'categoryServlet',
            type: 'POST',
            data: {action: 'allCategory'},
            success: function (datas) {
                if (datas) {
                    for (var i = 0; i < datas.length; i++) {
                        /*取出每一个菜单 */
                        var data = datas[i];
                        $("#nav_category").append("<li><a href='/productServlet?action=getProductByCategory&id="+data.id+"' name='category'>" + data.name + "</a></li>");
                    }
                    async2();
                } else {
                    alert("添加失败");
                }
            },
            error: function (response) {
                alert("error");
            },
            dataType: 'json'
        }
    );

}
function async2(){
//do sth...
    $.ajax(
        {
            url: 'categoryServlet',
            type: 'POST',
            data: {action: 'getCategoryList'},
            success: function (values) {
                if (values) {
                    console.log(values);
                    for (var i = 0; i < values.length; i++) {
                        /*取出每一个菜单 */
                        var value = values[i];
                        console.log(value.secondCategorylist);
                        console.log("aa");
                        $("#category_option").append('<li class="cat-item"><i class="icon i0"></i><a class="txt" href="/productServlet?action=getProductByCategory&id='+value.id+'">' + value.name + '</a></li>');
                        $("#category_option").append('<li class="cat-item " id="'+value.id+'"><i class="icon i1"></i></li>');
                        for (var j=0;j<value.secondCategorylist.length;j++){
                            var val=value.secondCategorylist[j];
                            $("#"+value.id).append('<a class="txt" href="/productServlet?action=getProductBySecondCategory&scid='+val.id+'">' + val.name + '</a>');
                        }
                        /**/
                    }
                    $.ajax({
                        url:"productServlet",
                        data:{action:'getHotProduct'},
                        dataType: 'json',
                        success:function (vals) {
                            console.log(vals);
                            console.log(vals.length);
                            if (vals) {
                                for (var i = 0; i < vals.length; i++) {
                                    /*取出每一个菜单 */
                                    var val = vals[i];
                                    $("#hot_goodsList").append('<li><a href="/productServlet?action=getProductdetail&id='+val.id+'"> <img src="/upload/image/'+val.image+'"> <p>'+val.name+'</p> <del>￥'+val.marketPrice+'</del> <br> <i class="yuan">￥</i> <span class="price">'+val.shopPrice+'</span> </a> </li>');
                                }
                            } else {
                                alert("添加失败");
                            }
                        }
                    })
                } else {
                    alert("添加失败");
                }
            },
            error: function (response) {
                alert("111")
            },
            dataType: 'json'
        }
    );
}