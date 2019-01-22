<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/img/ico/icon.ico"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="极简技术之道,极简技术,技术" />
    <meta name="description" content="极简技术之道是致力于前沿技术以及行业领域内的精彩软文的分享交流网站，并提供资源免费下载的原创网站" />
    <meta name="keywords2" content="原创,技术分享交流,技术原创网站" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>
    <title>${article.title}</title>
    <jsp:include page="header.jsp"/>
</header>
<article>
    <div class="leftbox">
        <div class="infos">
            <div class="newsview">
                <h2 class="intitle">您现在的位置是：<a href="<c:url value="/actions/article/list"/> ">网站首页</a>&nbsp;&gt;&nbsp;<a href="#">阅读文章</a></h2>

                <h3 class="news_title">${article.title}</h3>

                <div class="news_author"><span class="au01">${article.author}</span><span
                        class="au02">${article.time}</span><span class="au03">共<b>${article.click}</b>次浏览</span></div>
                <div class="tags">
                    <c:forEach items="${tags}" var="tag">
                        <a href="#">${tag.name}</a>
                    </c:forEach>
                </div>
                <div class="news_about"><strong>摘要</strong>${article.brief}</div>
                <div class="news_infos">
                    <p>${article.content}</p>
                </div>
            </div>
        </div>
        <div class="nextinfo">
            <p>上一篇：<a href="<c:url value="/actions/article/readArticle/${previous.id}"/> ">${previous.title}</a></p>

            <p>下一篇：<a href="<c:url value="/actions/article/readArticle/${next.id}"/> ">${next.title}</a></p>
        </div>
        <div class="otherlink">
            <h2>相关文章</h2>
            <ul>
                <c:forEach var="article1" items="${articles}">
                    <li>
                        <a href="<c:url value="/actions/article/readArticle/${article1.id}"/> ">${article1.title}</a><br>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="news_pl">
            <h2>文章评论</h2>
            <jsp:include page="../comment/comment.jsp"/>
        </div>
    </div>
    <jsp:include page="right.jsp"/>
</article>

<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>
<a href="#" class="cd-top">Top</a>
</body>
</html>
