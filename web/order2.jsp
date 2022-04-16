<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>order</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="css/mygxin.css"/>
</head>
<body><!----------------------------------------order------------------>
<div class="head ding">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <div class="fr clearfix" id="top1"><p class="fl"><a href="#" id="login">登录</a><a href="#" id="reg">注册</a>
            </p>
                <form action="searchProductServlet" method="post" class="fl"><input required type="text" name="key"/><input
                        type="submit" value=""/></form>
                <div class="btn fl clearfix"><a href="mygxin.html"><img src="img/grzx.png"/></a><a href="#" class="er1"><img
                        src="img/ewm.png"/></a><a href="cart.html"><img src="img/gwc.png"/></a>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="indexServlet">首页</a></li>
            <c:forEach var="f" items="${flist}">
                <li><a href="selectProductList?fid=${f.category_id}">${f.category_name}</a>
                    <div class="sList2">
                        <div class="clearfix">
                            <c:forEach var="c" items="${clist}">
                                <c:if test="${f.category_id == c.category_parentid}">
                                    <a href="selectProductList?cid=${c.category_id}">${c.category_name}</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="order cart mt"><!-----------------site------------------->
    <div class="site"><p class="wrapper clearfix"><span class="fl">订单确认</span><img class="top"
                                                                                   src="images/temp/cartTop02.png"></p>
    </div><!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <div class="orderL fl"><!--------h3----------------><h3>收件信息<a href="#" class="fr" id="addAddress">新增地址</a></h3>
            <!--------addres---------------->
            <div class="addres clearfix">

                <c:forEach var="a" items="${address}">
                    <label for="${a.address_id}">
                    <div class="addre fl" id="addid">
                        <input type="radio" name="a" value="${a.address_id}" id="${a.address_id}" style="display: none">
                        <div class="tit clearfix"><p class="fl">${a.user_name}</p>
                            <p class="fr" style="display: none"><a href="#">删除</a><span>|</span><a href="#" class="edit">编辑</a></p></div>
                        <div class="addCon"><p>${a.user_address}</p>
                            <p>${a.user_phone}</p></div>
                    </div>
                    </label>

                </c:forEach>

                    <script>
                        function js_method(b) {

                            if ($(".addres div").hasClass("on")) {
                                var a = 0;
                                $(".addres div").each(function(){
                                    if($(this).hasClass("on")){
                                        a = $(this).children("input").val();

                                    }
                                });
                                location.href='toPayServlet?oid='+b+'&aid='+a;
                                //
                                // $(".proIntro").css("border", "none");
                                // $(".num .please").hide()
                            } else {
                                alert("请确认收货信息！");
                            }

                        }
                    </script>


            </div>
            <h3>支付方式</h3><!--------way---------------->
            <div class="way clearfix"><img class="on" src="images/temp/way01.jpg"><img src="images/temp/way02.jpg"><img
                    src="images/temp/way03.jpg"><img src="images/temp/way04.jpg"></div>
            <h3>选择快递</h3><!--------dis---------------->
            <div class="dis clearfix"><span class="on">顺风快递</span><span>百世汇通</span><span>圆通快递</span><span>中通快递</span>
            </div>
        </div>
        <div class="orderR fr">
            <div class="msg"><h3>订单内容<a href="toCartServlet" class="fr">返回购物车</a></h3><!--------ul---------------->
                    <c:forEach items="${requestScope.order}" var="rs">
                <ul class="clearfix">
                        <li class="fl"><img width="100" height="100" src="${pageContext.request.contextPath}/upload/product/${rs.product_photo}"></li>
                        <li class="fl">
                            <p>颜色分类：${rs.product_name}</p>
                            <p>数量：</p>
                            <p>单价：</p></li>
                         <li class="fr">￥${rs.product_quantity}.00 <br>
                        ￥${rs.product_price}.00</li>
                    </ul>
                    </c:forEach>
            </div>
            <%--<div class="tips">--%>
                <%--<p><span class="fl">商品金额：</span><span class="fr">￥${rs.order_price}.00</span></p>--%>
                <%--<p><span class="fl">数量：</span><span class="fr">${rs.product_quantity}</span></p>--%>
                <%--<p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>--%>
                <%--<p><span class="fl">运费：</span><span class="fr">免运费</span></p>--%>
            <%--</div>--%>

            <div class="count tips"><p><span class="fl">合计：</span><span class="fr">￥${totalPrice}.00</span></p></div>
             <!--<input type="button" name="" value="去支付">--> <a class="pay" id="pay">去支付</a></div>

    </div>
</div><!--编辑弹框--><!--遮罩-->
<div class="mask"></div>
<div class="adddz editAddre" id="aaaa" style="top: 70%">
    <form action="addAddresssInorderServlet" method="get">
        <input type="text" name="username" placeholder="姓名" class="on"/>
        <input type="text" name="phone" placeholder="手机号"/>
        <div class="city"><select name="">
            <option value="省份/自治区">省份/自治区</option>
        </select><select>
            <option value="城市/地区">城市/地区</option>
        </select><select>
            <option value="区/县">区/县</option>
        </select><select>
            <option value="配送区域">配送区域</option>
        </select></div>
        <textarea  name="address" placeholder="详细地址"></textarea>
        <div class="bc"><input type="submit" value="保存"/><input type="button" value="取消"/></div>
    </form>
</div><!--返回顶部-->
<div class="gotop"><a href="toCartServlet">
    <dl  class="goCart">
        <dt><img src="img/gt1.png"/></dt>
        <dd>去购<br/>物车</dd>
        <span>${cartCount}</span></dl>
    </dl>
</a><a href="#" class="dh">
    <dl>
        <dt><img src="img/gt2.png"/></dt>
        <dd>联系<br/>客服</dd>
    </dl>
</a><a href="toUserInfoServlet">
    <dl>
        <dt><img src="img/gt3.png"/></dt>
        <dd>个人<br/>中心</dd>
    </dl>
</a><a href="#" class="toptop" style="display: none;">
    <dl>
        <dt><img src="img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
    </dl>
</a>
    <p>888-888-8888</p></div><!--footer-->
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span class="fl">7天无理由退货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span class="fl">15天免费换货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span class="fl">满599包邮</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span class="fl">手机特色服务</span>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
<script src="js/order2.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>