<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/19
  Time: 14:55
  页面基础模版来源:http://www.yangqq.com/
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>极简技术之道</title>
    <jsp:include page="baseStyle.jsp"/>
    <jsp:include page="header.jsp"/>
</head>
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
<body>

<article>
    <div class="leftbox">
        <div class="newblogs">
            <h2 class="hometitle">最新文章列表</h2>
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
    </div>
    <jsp:include page="right.jsp"/>
</article>
<footer>
    <p>Design by <a href="#">极简技术之道</a></p>
</footer>
</body>
</html>
