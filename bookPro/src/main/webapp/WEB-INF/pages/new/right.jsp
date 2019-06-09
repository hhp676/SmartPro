<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
     <script src="<c:url value="/js/new/jquery.min.js"/>"></script>
    <script type="text/javascript">
        debugger
        (function() {
            var s = "_" + Math.random().toString(36).slice(2);
            document.write('<div style="" id="' + s + '"></div>');
            (window.slotbydup = window.slotbydup || []).push({
                id: "u3964403",
                container:  s
            });
        })();
        window.Console("sssss" +s);
    </script>
    <!-- 多条广告如下脚本只需引入一次 -->
    <script type="text/javascript" src="//cpro.baidustatic.com/cpro/ui/i.js" async="async" defer="defer" ></script>
</head>
<body>

<aside class="sidebar">
    <div class="fenlei">
        <ul class="flnav">
            <li class="flselect"><a href="#" title="最新文章" class="pall"></a></li>
            <li><a href="#" title="站长推荐" class="pgood"></a></li>
            <li><a href="#" title="点击排行" class="pbro"></a></li>
        </ul>
    </div>
    <div class="newstw">
        <ul class="sidenews" title="最新文章">
            <c:forEach var="newArticle" items="${newArticles}" varStatus="s">
                <li>
                    <c:if test="${newArticle.imgType== 0}">
                      <i><img src="<c:url value="/titleImg/${newArticle.imgnum}.jpg"/> "/></i>
                    </c:if>
                    <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${newArticle.id}"/> "> ${newArticle.title} </a></p>
                </li>
            </c:forEach>
        </ul>
        <ul class="sidenews" title="站长推荐">
            <c:forEach var="recomArticle" items="${recomArticles}" varStatus="s">
                <li>
                    <c:if test="${recomArticle.imgType== 0}">
                        <i><img src="<c:url value="/titleImg/${recomArticle.imgnum}.jpg"/> "/></i>
                    </c:if>
                    <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${recomArticle.id}"/> "> ${recomArticle.title} </a></p>
                </li>
            </c:forEach>
        </ul>
        <ul class="sidenews" title="点击排行">
            <c:forEach var="hotArticle" items="${hotArticles}" varStatus="s">
                <li>
                    <c:if test="${hotArticle.imgType== 0}">
                        <i><img src="<c:url value="/titleImg/${hotArticle.imgnum}.jpg"/> "/></i>
                    </c:if>
                    <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${hotArticle.id}"/> "> ${hotArticle.title} </a></p>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="ad"> <img src="<c:url value="/img/new/7.jpg"/> "></a> </div>
    <div class="about">
        <h2 class="hometitle">关于本站</h2>
        <ul>
            <div class="avatar"> <img src="<c:url value="/img/new/icon.png"/> "></div>
            <p class="abname">极简技术之道</p>
            <p class="abtext">欢迎您访问极简技术之道，在这里，它不仅仅是记录技术的平台，更是一个记录世界、时代的平台！</p>
            <li><a class="xlwb" href="#" target="_blank"></a></li>
            <li><a class="txqq" href="" target="_blank"></a></li>
            <li><a class="rss" href="" target="_blank"></a></li>
            <li class="wxpic"><a class="wx" href=""></a><img src="<c:url value="/img/new/vx.png"/> "></li>
        </ul>
    </div>

</aside>

</body>
</html>
