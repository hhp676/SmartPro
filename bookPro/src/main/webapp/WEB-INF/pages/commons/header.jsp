<%@ page import="java.text.DateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/8
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/css/public.css"/>"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
  <h1>博客后台管理系统</h1>

  <div class="publicHeaderR">
    <p><span>你好！</span><span style="color: #fff21b">${account.nickname}</span> , 欢迎你！</p>
    <a href="<c:url value="/actions/home/logout"/> ">退出</a>
  </div>
</header>
<!--时间-->
<section class="publicTime">
  <span id="time2">本次操作时间：<%=DateFormat.getDateTimeInstance().format(new java.util.Date())%></span>
  <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！</a>
</section>
</body>
</html>
