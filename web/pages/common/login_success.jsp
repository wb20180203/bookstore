<%--
  Created by IntelliJ IDEA.
  User: 18312
  Date: 2020/11/12
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临帅哥哥书城</span>
    <a href="<%=request.getContextPath()%>/pages/order/order.jsp">我的订单</a>
    <a href="<%=request.getContextPath()%>/userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="<%=request.getContextPath()%>/index.jsp">返回</a>
</div>