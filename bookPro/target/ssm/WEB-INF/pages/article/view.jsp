<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/8
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客后台管理系统</title>
    <jsp:include page="../commons/style.jsp"/>
    <jsp:include page="../commons/header.jsp"/>

</head>
<body>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <nav>
                <ul class="list">
                    <c:forEach items="${menus}" var="menu">
                        <li>
                            <a href="<c:url value="${menu.uri}"/>">${menu.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </nav>
    </div>

    <div class="right">
        <p style="font-size: 16PX">作者:${article.author}</p>
        <p style="font-size: 16PX">发布日期:${article.time}</p>
        <p style="font-size: 16PX">文章标题:${article.title}</p>
        <p style="font-size: 16PX">点击量:${article.click}</p>
        <p style="font-size: 16PX">摘要:${article.brief}</p>
        <a href="javascript:history.go(-1)" style="font-size: 22px">返回文章列表</a>
        <br>
        <br>
        <a href="#top"></a>
        <br>
        <p>${article.content}</p>
        <br>
        <br>
        <a href="#top" style="font-size: 22px">返回顶部</a>
        <br>
        <br>
        <br>
    </div>

</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>

        <div class="removeMain">
            <p>你确定要删除该文章吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<jsp:include page="../commons/footer.jsp"/>

</body>
</html>

