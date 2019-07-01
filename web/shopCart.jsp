<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/shopCart.css">
    <link rel="stylesheet" href="./layui/css/layui.css">
</head>
<body>
<div id="app">
    <input type="hidden" id="username" value="${user.username}">
    <!--头部区域-->
    <div class="header">
        <a href="index.jsp" class="icon_back"></a>
        <h3>购物车</h3>
        <a href="" class="icon_menu"></a>
    </div>
    <div class="total">
        <div class="cart-no-good" :class="hasProduct?'isHidden':''">
            <div class="opps">
                <img src="images/pay_pop_img_loading_fail.png" alt>
            </div>
            <p class="empty-title">您的购物车中没有商品，请先去挑选心爱的商品吧！</p>
            <div class="go-shop">
                <a href="index.jsp"  class="go-shop">去逛逛</a>
            </div>
        </div>
        <div :class="hasProduct?'':'isHidden'">
            <!--安全提示-->
            <div class="jd_safe_tip">
                <p class="tip_word">
                    您正在安全购物环境中，请放心购物
                </p>
            </div>
            <!--中间的列表-->
            <main class="jd_shopCart_list">
                <section v-for="(shop, index) in shopListArr">
                    <div class="shopCart_list_title">
                        <span class="cart_title">码蚁自营</span>
                        <span class="cart_sale">满100元免运费服务</span>
                    </div>
                    <div class="shopCart_list_con">
                        <div class="list_con_left">
                            <a href="javascript:;" class="cart_check_box" :checked="shop.isSelect"
                               @click="singerShopSelected(shop)"></a>
                        </div>
                        <div class="list_con_right">
                            <div class="shop_img">
                                <img :src="'/upload/image/'+shop.item.shopImage" alt="shop.shopName">
                            </div>
                            <div class="shop_con">
                                <a href="" v-text="shop.item.shopName"></a>
                                <div class="shop_price">
                                    <div class="singer">{{shop.item.shopPrice | moneyFormat(shop.item.shopPrice)}}</div>
                                    <div class="total">{{shop.item.shopNumber * shop.item.shopPrice |
                                        moneyFormat(shop.item.shopNumber * shop.item.shopPrice)}}
                                    </div>
                                </div>
                                <div class="shop_deal">
                                    <div class="shop_deal_left">
                                        <span @click="singerShopPrice(shop, false)">-</span>
                                        <input type="tel" disabled="disabled" value="shop.item.shopNumber" v-model="shop.item.shopNumber">
                                        <span @click="singerShopPrice(shop, true)">+</span>
                                    </div>
                                    <div class="shop_deal_right" @click="clickTrash(shop)">
                                        <span></span>
                                        <span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
            <!--底部通栏-->
            <div id="tab_bar">
                <div class="tab-bar-left">
                    <a href="javascript:;" class="cart_check_box" :checked="isSelectedAll"
                       @click="selectedAll(isSelectedAll)"></a>
                    <span style="font-size: 16px;">全选</span>
                    <div class="select-all">
                        合计：<span class="total-price">{{totalPrice |  moneyFormat(totalPrice)}}</span>
                    </div>
                </div>
                <div class="tab-bar-right">
                    <button class="pay" @click="cartToConfirm">去结算</button>
                </div>
            </div>
        </div>
    </div>
    <!--弹出面板-->
    <div class="panel" :class="{'panel_is_show': isHideDelPanel}">
        <div class="panel_content">
            <div class="panel_title">您确认删除这个商品吗?</div>
            <div class="panel_footer">
                <button class="cancel" @click="isHideDelPanel = true">取消</button>
                <button class="submit" @click="delShop()">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-1.11.0.js"></script>
<script src="lib/vue.js"></script>
<script src="lib/vue-resource.js"></script>
<script src="js/base.js"></script>
<script src="js/shopCart.js"></script>
<script src="js/shopCartloop.js"></script>
<script src="layui/layui.all.js" charset="utf-8"></script>

</body>
<style>
    .isHidden {
        display: none;
    }
    .cart-no-good {
        padding-top: 10px;
        padding-bottom: 69px;
        margin-top: 0px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }
    .cart-no-good.isHidden {
        display: none;
    }
    .opps {
        height: 325px;
        width: 325px;
    }
    .opps img {
        width: 100%;
    }
    .empty-title {
        margin-top: 13px;
        font-size: 14px;
    }
    .go-shop {
        margin-top: 30px;
        width: 120px;
        height: 60px;
        background: #f60;
        color: #fff;
        text-align: center;
        line-height: 60px;
        border-radius: 20px;
    }
</style>
</html>