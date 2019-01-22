<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <link rel="shortcut icon" href="<c:url value="/img/ico/icon.ico"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="极简技术之道,极简技术,技术" />
    <meta name="description" content="极简技术之道是致力于前沿技术以及行业领域内的精彩软文的分享交流网站，并提供资源免费下载的原创网站" />
    <meta name="keywords2" content="原创,技术分享交流,技术原创网站" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--360收录标签--%>
    <meta name="360-site-verification" content="8510aa2cbbb515daae7b11ef7218368d" />

    </head>
    <body>
    <jsp:include page="header.jsp"/>
        <div class="wrapper">
            <main>
                <!--banner begin-->
                <div class="banner">
                    <div id="banner" class="fader">
                        <c:forEach var="banner" items="${bannerList}" varStatus="s">
                            <li class="slide" ><a href="#" title="${banner.title}" target="_blank"><img src="<c:url value="${banner.imgurl}"/>"><span class="imginfo">${banner.text}</span></a></li>
                        </c:forEach>
                        <div class="fader_controls">
                            <div class="page prev" data-target="prev">&lsaquo;</div>
                            <div class="page next" data-target="next">&rsaquo;</div>
                            <ul class="pager_list">
                            </ul>
                        </div>
                    </div>
                </div>
                <!--banner end-->
                <div class="bloglist">
                    <ul>
                        <c:forEach var="article" items="${articles}" varStatus="s">
                            <li>
                                <h3 class="blogtitle"><a href="<c:url value="/actions/article/readArticle/${article.id}"/> "
                                                         target="_blank">${article.title}</a></h3>

                                <div class="bloginfo">
                                    <c:if test="${article.imgType== 0}">
                                        <span class="blogpic">
                                        <a href="#" title="">
                                            <img src="<c:url value="/titleImg/${article.imgnum}.jpg"/> "/>
                                        </a>
                                    </span>
                                    </c:if>
                                    <p>
                                        <a href="<c:url value="/actions/article/readArticle/${article.id}"/> " class="more"
                                           target="_blank">${article.brief}
                                        </a>
                                    </p>
                                </div>
                                <div class="autor"><span class="lm f_l"><a href="#">${article.author}</a></span><span
                                        class="dtime f_l">${article.time}</span><span class="viewnum f_l">浏览（<a
                                        href="#">${article.click}</a>）</span><span class="pingl f_l">评论（<a
                                        href="#">${article.comments.size()}</a>）</span><span class="f_r">
                                    <!-- 关注点8:发送动态URL-->
                                    <a href="<c:url value="/actions/article/readArticle/${article.id}"/> " class="more"
                                       target="_blank">阅读原文>></a></span></div>
                            </li>
                        </c:forEach>

                    </ul>
                    <!--下面的分页代码由Jay于18/3/26日修改，可以重用多次，请不要修改太多-->
                    <div style="text-align: center">
                        <ul class="pagination">
                            <li
                                <c:if test="${pageInfo.pageNum==1}">
                                    class="disabled"
                                </c:if>>
                                <a href="<c:url value="/actions/article/list?pageNo=1"/> ">&laquo;</a>
                            </li>
                            <li>
                                <!--如果当前页数是第一页，那么点上一页仍然是当前页。 -->
                                <a href="<c:url value="/actions/article/list?pageNo=${pageInfo.pageNum==1?pageInfo.pageNum:pageInfo.pageNum-1}"/> ">上一页</a>
                            </li>
                            <!--判断最大页数是否超过X，如果超过X则是X，否则是最大页数。防止分页信息过长 -->
                            <c:forEach begin="1" end="${pageInfo.pages>8?8:pageInfo.pages}" step="1" var="pageNo">
                                <li
                                        <c:if test="${pageInfo.pageNum==pageNo}">
                                            class="active"
                                        </c:if>>
                                    <a href="<c:url value="/actions/article/list?pageNo=${pageNo}"/> ">${pageNo}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <!--如果当前页数是最后一页，那么点击下一页仍然是当前页。 -->
                                <a href="<c:url value="/actions/article/list?pageNo=${pageInfo.pageNum==pageInfo.pages?pageInfo.pageNum:pageInfo.pageNum+1}"/> ">下一页</a>
                            </li>
                            <li
                                <c:if test="${pageInfo.pageNum==pageInfo.pages}">
                                    class="disabled"
                                </c:if>>
                                <a href="<c:url value="/actions/article/list?pageNo=${pageInfo.pages}"/> ">&raquo;</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <!--bloglist end-->

            </main>
            <jsp:include page="right.jsp"/>
            <!--sidebar end-->
        </div>

    <!--wrapper end-->
            <jsp:include page="footer.jsp"></jsp:include>
        <!--footer end -->
    </body>
</html>
