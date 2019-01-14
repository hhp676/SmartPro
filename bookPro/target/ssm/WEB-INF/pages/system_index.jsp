<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/8
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="commons/header.jsp"/>
    <title>博客管理系统</title>
</head>
<body>
<!--主体内容-->
<section class="publicMian">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <c:forEach items="${menus}" var="menu">
                    <li>
                        <a href="<c:url value="${menu.uri}"/>">${menu.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
    <div class="right">
        <img class="wColck" src="<c:url value="/img/clock.jpg"/>" alt=""/>
        <div class="wFont">
            <h2>${account.nickname}</h2>
            <p>欢迎来到博客后台管理系统!</p>
        </div>
    </div>
</section>
<jsp:include page="commons/footer.jsp"/>

</body>
</html>
