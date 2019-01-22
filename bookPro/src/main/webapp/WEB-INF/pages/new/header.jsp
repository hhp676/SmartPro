<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<header>
    <jsp:include page="common.jsp"></jsp:include>
    <title>极简技术之道</title>
    <style>
        .pagination {
            display: inline-block;
            padding-left: 0;
            margin: 20px 0;
            border-radius: 4px
        }

        .pagination > li {
            display: inline
        }

        .pagination > li > a, .pagination > li > span {
            position: relative;
            float: left;
            padding: 6px 12px;
            margin-left: -1px;
            line-height: 1.42857143;
            color: #337ab7;
            text-decoration: none;
            background-color: #fff;
            border: 1px solid #ddd
        }

        .pagination > li:first-child > a, .pagination > li:first-child > span {
            margin-left: 0;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px
        }

        .pagination > li:last-child > a, .pagination > li:last-child > span {
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px
        }

        .pagination > li > a:focus, .pagination > li > a:hover, .pagination > li > span:focus, .pagination > li > span:hover {
            z-index: 2;
            color: #23527c;
            background-color: #eee;
            border-color: #ddd
        }

        .pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
            z-index: 3;
            color: #fff;
            cursor: default;
            background-color: #337ab7;
            border-color: #337ab7
        }

        .pagination > .disabled > a, .pagination > .disabled > a:focus, .pagination > .disabled > a:hover, .pagination > .disabled > span, .pagination > .disabled > span:focus, .pagination > .disabled > span:hover {
            color: #777;
            cursor: not-allowed;
            background-color: #fff;
            border-color: #ddd
        }
    </style>
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
                    <a href="http://www.cnart001.cn">极简技术之道</a>
                </div>
                <h2 id="mnavh"><span class="navicon"></span></h2>
                <ul id="starlist">
                    <li><a href="http://www.cnart001.cn">网站首页</a></li>
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
    </header>
</body>
</html>
