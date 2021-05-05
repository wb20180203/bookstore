<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 18312
  Date: 2020/11/15
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%@include file="/pages/common/face.jsp"%>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="<%=request.getContextPath()%>/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <c:if test="${empty sessionScope.cart.items}">
            <h3 style="text-align: center">您购物车还没有宝贝</h3>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </div>
        </c:if>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" style="width: 150px; height: 170px" alt="" src="${book.img_path}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
<%--                        <button class="addToCart" onclick="window.location.href('http://localhost:8080/book/cartServlet?action=addBook')">加入购物车</button>--%>
                        <a href="<%=request.getContextPath()%>/cartServlet?action=addBook&id=${book.id}">加入购物车</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
