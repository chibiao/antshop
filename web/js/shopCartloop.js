"use strict";
new Vue({
    el: '#app',
    data: {
        //购物车中是否有商品
        hasProduct: true,
        // 购物车中的数据
        shopListArr: [],
        // 是否全选
        isSelectedAll: false,
        // 所有商品的总价格
        totalPrice: 0,
        // 是否隐藏删除面板
        isHideDelPanel: true,
        // 当前要删除的一个商品
        currentDelShop: {}
    },
    // 组件已经加载完毕, 请求网络数据, 业务处理
    mounted(){
        // 请求本地的数据
        this.getLocalData();
    },

    // 过滤
    filters: {
        // 格式化金钱
        moneyFormat(money){
            return '¥' + money.toFixed(2);
        }
    },

    methods: {
        cartToConfirm(){
            /*提交订单*/
            if(this.totalPrice!=0){
               var val=$("#username").val();
               if(val!=null&&val!=''){
                   var orders={
                       "totalPrice":this.totalPrice,
                       "orderItems":[]
                   };
                   for (var i=0;i<this.shopListArr.length;i++){
                       if(this.shopListArr[i].isSelect){
                           var order={
                               "productId":this.shopListArr[i].item.shopId,
                               "subtotal":this.shopListArr[i].item.shopPrice*this.shopListArr[i].item.shopNumber,
                               "count":this.shopListArr[i].item.shopNumber
                           };
                           orders.orderItems.push(order);
                       }
                   }
                   $.ajax({
                       url:"orderServlet",
                       type:"POST",
                       data: {action:'submitOrders',orders:JSON.stringify(orders)},
                       success:function (data) {
                           if (data.success){
                               alert("提交成功");
                               window.location.href = "/orderServlet?action=orderList";
                           }
                       },
                       dataType: 'json'
                   })
               }else{
                   layer.alert('请先登录!');
                   window.location.href = "login.jsp";
               }
               console.log(val);
            }else{
                layer.alert('请至少选中一个商品结算!');
            }
        },
        // 1. 请求本地的数据
        getLocalData() {
            this.$http.post('/cartServlet?action=getCartList').then(response => {
                const res = response.body;
                if(res=="null"||res.length===0){
                    this.hasProduct=false;
                    return;
                }
                var isSelect =false;
                var shopListArr=[];
                var shop={};
                for (var i=0;i< res.length;i++){
                    shop={
                        item:res[i],
                        isSelect:isSelect
                    };
                    shopListArr.push(shop);
                }
                this.shopListArr=shopListArr;
            }, response => {
                alert('请求数据失败!');
            });
        },

        // 2. 单个商品的加减
        singerShopPrice(shop, flag){
            if(flag){ // 加
                shop.item.shopNumber += 1;
            }else { // 减
                if(shop.item.shopNumber <= 1){
                    shop.item.shopNumber = 1;
                    return;
                }
                shop.item.shopNumber -= 1;
            }
            // 计算总价
            this.getAllShopPrice();
        },

        // 3. 选中所有的商品
        selectedAll(flag){
            // 3.1 总控制
            this.isSelectedAll = !flag;
            // 3.2 遍历所有的商品数据
            this.shopListArr.forEach((value, index)=>{
                if(typeof value.isSelect === 'undefined'){
                    this.$set(value, 'checked', !flag);
                }else {
                    value.isSelect = !flag;
                }
            });

            // 3.3 计算总价格
            this.getAllShopPrice();
        },

        // 4. 计算商品的总价格
        getAllShopPrice(){
            let totalPrice = 0;
            // 4.1 遍历所有的商品
            this.shopListArr.forEach((value, index)=>{
                // 判断商品是否被选中
                if(value.isSelect){
                    totalPrice += value.item.shopPrice * value.item.shopNumber;
                }
            });

            this.totalPrice = totalPrice;
        },

        // 5. 单个商品的选中和取消
        singerShopSelected(shop){
            // 5.1 判断有没有这个属性
            if(typeof shop.isSelect === 'undefined'){
                this.$set(shop, 'checked', true);
            }else {
                shop.isSelect = !shop.isSelect;
            }

            // 5.2 计算总价
            this.getAllShopPrice();

            // 5.3 判断是否全选
            this.hasSelectedAll();
        },

        // 6. 判断是否全选
        hasSelectedAll(){
            let flag = true;
            this.shopListArr.forEach((value, index)=>{
                if(!value.isSelect){
                    flag = false;
                }
            });
            this.isSelectedAll = flag;
        },

        // 7. 点击垃圾篓
        clickTrash(shop){
            this.isHideDelPanel = false;
            this.currentDelShop = shop;
        },

        // 8. 删除当前的商品
        delShop(){
            // 8.1 隐藏面板
            this.isHideDelPanel = true;
            const index = this.shopListArr.indexOf(this.currentDelShop);
            this.shopListArr.splice(index, 1);
            console.log(this.shopListArr.length);
            if(this.shopListArr.length===0){
                this.hasProduct=false;
                console.log("进来了")
            }
            this.$http.post('/cartServlet?action=deleteProduct&id='+this.currentDelShop.item.shopId).then(response => {
                layer.alert('删除成功!');
            }, response => {
                alert('请求数据失败!');
            });
            // 8.2 计算总价格
            this.getAllShopPrice();
        }
    }
});