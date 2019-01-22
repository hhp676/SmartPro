<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <%-- <jsp:include page="common.jsp"></jsp:include>--%>
     <script src="<c:url value="/js/new/jquery.min.js"/>"></script>
</head>
<body>

<aside class="sidebar">
    <div class="fenlei">
        <ul class="flnav">
            <li class="flselect"><a href="#" title="最新文章" class="pall"></a></li>
            <li><a href="#" title="站长推荐" class="pgood"></a></li>
            <li><a href="#" title="点击排行" class="pbro"></a></li>
          <%--  <li><a href="#" title="头条关注" class="pfav"></a></li>--%>
        </ul>
    </div>
    <div class="newstw">
        <ul class="sidenews">
            <h2>最新文章</h2>
                <c:forEach var="hotArticle" items="${hotArticles}" varStatus="s">
                    <li>
                        <c:if test="${hotArticle.imgType== 0}">
                          <i><img src="<c:url value="/titleImg/${hotArticle.imgnum}.jpg"/> "/></i>
                        </c:if>
                        <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${hotArticle.id}"/> "> ${hotArticle.title} </a></p>
                    </li>
                </c:forEach>
        </ul>
        <ul class="sidenews">
            <h2>站长推荐</h2>
            <c:forEach var="hotArticle" items="${hotArticles}" varStatus="s">
                <li>
                    <c:if test="${hotArticle.imgType== 0}">
                        <i><img src="<c:url value="/titleImg/${hotArticle.imgnum}.jpg"/> "/></i>
                    </c:if>
                    <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${hotArticle.id}"/> "> ${hotArticle.title} </a></p>
                </li>
            </c:forEach>
        </ul>
        <ul class="sidenews">
            <h2>点击排行</h2>
            <c:forEach var="hotArticle" items="${hotArticles}" varStatus="s">
                <li>
                    <c:if test="${hotArticle.imgType== 0}">
                        <i><img src="<c:url value="/titleImg/${hotArticle.imgnum}.jpg"/> "/></i>
                    </c:if>
                    <p><a style="font-size:16px" href="<c:url value="/actions/article/readArticle/${hotArticle.id}"/> "> ${hotArticle.title} </a></p>
                </li>
            </c:forEach>
        </ul>
       <%-- <ul class="sidenews">
            <h2>头条关注</h2>
            <li> <i><img src="images/12.jpg"></i>
                <p><a href="/">个人博客《草根寻梦》—手机版模板</a></p>
                <span>2018-05-13</span> </li>
            <li> <i><img src="images/11.jpg"></i>
                <p><a href="/">安静地做一个爱设计的女子</a></p>
                <span>2018-05-13</span> </li>
            <li> <i><img src="images/10.jpg"></i>
                <p><a href="/">个人博客，属于我的小世界！</a></p>
                <span>2018-05-13</span> </li>
            <li> <i><img src="images/9.jpg"></i>
                <p><a href="/">作为一个设计师,如果遭到质疑你是否能恪守自己的原则?</a></p>
                <span>2018-05-13</span> </li>
            <li> <i><img src="images/8.jpg"></i>
                <p><a href="/">Come on,行动起来吧!我们和时间来一场赛跑!</a></p>
                <span>2018-05-13</span> </li>
        </ul>--%>
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
    <%--<div class="tjlm">
        <h2 class="hometitle">推荐栏目</h2>
        <ul>
            <li><a href="about.html">关于我</a></li>
            <li><a href="share.html">模板分享</a></li>
            <li><a href="list.html">博客日记</a></li>
            <li><a href="info.html">慢生活</a></li>
            <li><a href="time.html">时间轴</a></li>
            <li><a href="#">美文欣赏</a></li>
        </ul>
    </div>
    <div class="tpjx">
        <h2 class="hometitle">图片精选</h2>
        <ul>
            <li><a href="#"><i><img src="images/1.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
            <li><a href="#"><i><img src="images/2.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
            <li><a href="#"><i><img src="images/3.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
            <li><a href="#"><i><img src="images/4.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
            <li><a href="#"><i><img src="images/5.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
            <li><a href="#"><i><img src="images/6.jpg"></i><span>个人博客，属于我的小世界！</span></a></li>
        </ul>
    </div>
    <div class="links">
        <h2 class="hometitle">友情链接</h2>
        <ul>
            <li><a href="http://www.yangqq.com">杨青个人博客</a></li>
            <li><a href="http://www.searchtrip.cn">寻之旅</a></li>
            <li><a href="http://www.yangqq.com/download/">个人博客模板</a></li>
            <li><a href="http://www.yangqq.com/link.html">优秀个人博客</a></li>
        </ul>
    </div>--%>
   <%-- <div class="ad"> <img src="images/8.jpg"> </div>--%>
</aside>

</body>
</html>
