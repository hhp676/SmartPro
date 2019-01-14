<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/20
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="rightbox">
  <br>
  <div class="paihang">
    <h2 class="ab_title"><a href="/">热门文章</a></h2>
    <ul>
      <c:forEach var="hotArticle" items="${hotArticles}">
        <li>
          <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${hotArticle.id}"/> "> ${fn:substring(hotArticle.title,0,18)} </a></p>
        </li>
      </c:forEach>
    </ul>
    <div class="ad"><img width="300" height="200" src="<c:url value="/images/shuai.JPG"/> "></div>
  </div>
  <div class="paihang">
    <h2 class="ab_title"><a href="/">本栏推荐</a></h2>
    <ul>
        <c:forEach items="${recommandArticles}" var="recommandArticle">
            <li>
                <b><a href="<c:url value="/actions/article/readArticle/${recommandArticle.id}"/> " target="_blank">${recommandArticle.title}</a></b>
                <p>${fn:substring(recommandArticle.brief,0,16)}...</p>
            </li>
        </c:forEach>
    </ul>
    <div class="ad"><img width="300" height="200" src="<c:url value="/images/jiaban.jpg"/> "></div>
  </div>
  <div class="weixin">
    <h2 class="ab_title">微信关注</h2>
    <ul>
      <img  src="<c:url value="/images/vx.png"/> ">
    </ul>
  </div>
</div>
</body>
</html>
