<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("#clearCart").click(function (){
				return confirm("你确定要清空购物车吗");
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="book">
				<tr>
					<td>${book.value.name}</td>
					<td><a href="<%=request.getContextPath()%>/cartServlet?action=update&id=${book.value.id}&count=${book.value.count-1}"
						   style="text-decoration:none">-</a>
							${book.value.count}
						<a href="<%=request.getContextPath()%>/cartServlet?action=update&id=${book.value.id}&count=${book.value.count+1}"
						   style="text-decoration:none">+</a>
					</td>
					<td>${book.value.price}</td>
					<td>${book.value.totalPrice}</td>
					<td><a href="<%=request.getContextPath()%>/cartServlet?action=deleteBook&id=${book.value.id}">删除</a></td>
				</tr>
			</c:forEach>

		</table>
		<c:choose>
			<c:when test="${not empty sessionScope.cart.items}">
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
					<span class="cart_span"><a id="clearCart" href="<%=request.getContextPath()%>/cartServlet?action=clear">清空购物车</a></span>
					<span class="cart_span"><a href="<%=request.getContextPath()%>/orderServlet?action=saveOrder">去结账</a></span>
				</div>
			</c:when>
			<c:otherwise>
				<a style="text-align: center;color: darkcyan" href="../../index.jsp"><center>您购物车还没有宝贝哦，快去选购吧！</center></a>
			</c:otherwise>
		</c:choose>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>