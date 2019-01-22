<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<header>
  <%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="<c:url value="/css/new/base.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/new/index.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/new/m.css"/>" rel="stylesheet">
    &lt;%&ndash;<link href="<c:url value="/js/new/jquery.min.js"/>" rel="stylesheet">
    <link href="<c:url value="/js/new/comm.js"/>" rel="stylesheet">
    <link href="<c:url value="/js/new/hc-sticky.js"/>" rel="stylesheet">&ndash;%&gt;
    <script src="<c:url value="/js/new/jquery.min.js"/>"></script>
    <script src="<c:url value="/js/new/comm.js"/>"></script>
    <script src="<c:url value="/js/new/hc-sticky.js"/>"></script>
   &lt;%&ndash; <link href="<c:url value="/css/base.css"/>" rel="stylesheet">&ndash;%&gt;
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet">--%>
    <jsp:include page="common.jsp"></jsp:include>
</header>
<body>
<header id="header">
    <div class="navbar">
        <div class="topbox">
            <p class="welcome">您好，欢迎您访问极简技术之道，在这里，它不仅仅是记录技术的平台，更是一个记录世界、时代的平台！</p>
            <div class="searchbox">
                <div id="search_bar" class="search_bar">
                    <form  id="searchform" action="[!--news.url--]e/search/index.php" method="post" name="searchform">
                        <input class="input" placeholder="想搜点什么呢.." type="text" name="keyboard" id="keyboard">
                        <input type="hidden" name="show" value="title" />
                        <input type="hidden" name="tempid" value="1" />
                        <input type="hidden" name="tbname" value="news">
                        <input type="hidden" name="Submit" value="搜索" />
                        <p class="search_ico"> <span></span></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="header-navigation">
        <nav>
            <div class="logo">
               <%-- <a ><img class="icon" img src="<c:url value="/img/ico/icon.png"/> "></a>--%>
                <a class="iconfont" href="http://www.cnart001.cn">极简技术之道</a>
            </div>
            <h2 id="mnavh"><span class="navicon"></span></h2>
            <ul id="starlist">
                <li><a href="index.html">网站首页</a></li>
                <%--<li><a href="about.html">关于我</a></li>
                <li><a href="share.html">模板分享</a></li>
                <li><a href="list.html">博客日记</a></li>
                <li class="menu"><a href="fengmian.html">学无止境</a>
                    <ul class="sub">
                        <li><a href="#">慢生活</a></li>
                        <li><a href="#">美文欣赏</a></li>
                    </ul>
                </li>
                <li><a href="info.html">慢生活</a></li>
                <li><a href="time.html">时间轴</a></li>--%>
                <li><a href="<c:url value="/actions/home"/> ">管理中心</a></li>
                <li><a href="https://www.csdn.net" target="_blank">博客导航</a></li>
                <li><a href="#">留言</a></li>
                <c:if test="${account!=null}">
                    <li>
                        <a href="<c:url value="/actions/article/myBlogSpace"/> ">个人中心</a>
                    </li>
                </c:if>

                <li>
                    <c:choose>
                        <c:when test="${account==null}">
                            <a href="<c:url value="/actions/security/login"/> ">登录</a>
                        </c:when>
                        <c:otherwise>
                            <a href="#">${account.nickname},欢迎你</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${account==null}">
                            <a href="<c:url value="/actions/security/register"/> ">注册</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/actions/article/postedit"/> ">写文章</a>
                        </c:otherwise>
                    </c:choose>
                </li>

                <c:if test="${account!=null}">
                    <li><a href="<c:url value="/actions/home/logout"/> ">退出</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</body>
</html>
