<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/19
  Time: 15:46
  页面基础模版来源:http://www.yangqq.com/
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="gb2312">
    <title>个人中心</title>
    <jsp:include page="baseStyle.jsp"/>
    <link rel="stylesheet" href="<c:url value="/css/baseFormStyle.css"/> " type="text/css"/>
</head>
<script src="<c:url value="/js/jquery.js"/>"></script>

<style>
    a:hover{color:#0000FF}
</style>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<article>
    <div class="leftbox">
        <div class="infos">
            <div class="newsview">
                <h2 >您现在的位置是：个人中心</h2>
                <hr>
                <div class="news_infos m20">
                    <h1>我的文章列表</h1></p>
                    <c:forEach items="${articles}" var="article">
                        <a href="<c:url value="/actions/article/readArticle/${article.id}"/>">${article.title}</a><br>
                    </c:forEach>
                </div>
                <hr>
                <div class="news_infos m20">
                    <h1>我的文章阅读排行</h1></p>
                    <c:forEach items="${hotArticles}" var="article">
                        <a href="<c:url value="/actions/article/readArticle/${article.id}"/>">${article.title}(${article.click})</a><br>
                    </c:forEach>
                </div>
                <hr>
                <div class="news_infos m20">
                    <h1>我的最新评论</h1></p>
                    <c:forEach items="${models}" var="model" begin="0" end="5">
                        <p>
                            我评论文章： <a href="<c:url value="/actions/article/readArticle/${model.articleId}"/>">${model.articleTitle}</a><br>
                            <h3>评论是：&nbsp;&nbsp;${model.commentContent}</h3>
                            <br>
                        </p>
                    </c:forEach>
                </div>
                <hr>
            </div>

        </div>


    </div>
    <div class="rightbox">
        <div class="aboutme m20">
            <h2 class="ab_title">我的信息</h2>

            <div class="ab_con">
                <p>我的昵称：${account.nickname}</p>

                <p>我的登录名称：${account.loginName}</p>

                <p>我的手机号:${account.cellPhone}</p>

                <p>我的文章数量:${articles.size()}</p>

                <p>我的评论数量:${comments.size()}</p>

            </div>
        </div>
        <div class="weixin">
            <h2 class="ab_title">安全中心</h2>
            <h2>重置密码</h2>
            <form action="<c:url value="/actions/security/resetPasswordByOldPassword"/> " method="post"
                  onsubmit="return formSubmit()">
                <input type="hidden" name="loginName" value="${account.loginName}"><br>
                旧密码：  <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="旧密码"><br>
                新密码：<input type="password" name="newPassword" class="form-control"
                           id="newPassword" placeholder="新密码"><br>
                新密码确认：<input type="password" name="newPasswordPasswordConfirm" class="form-control"
                             id="newPasswordPasswordConfirm" placeholder="确认密码"><br>
                <input type="submit" class="myInput" value="重置密码">
                <input type="reset"  class="myInput" value="清空">
            </form>

        </div>
    </div>
</article>
<footer>
    <p>Design by <a href="#">开发者：Java程序员大宇</a></p>
</footer>
</body>
</html>
<script>
    function formSubmit(){
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var newPasswordPasswordConfirm = $("#newPasswordPasswordConfirm").val();
        if(oldPassword == null|| oldPassword==''){
            window.alert("请输入旧密码!")
            return false;
        }
        if(newPassword == null|| newPassword==''){
            window.alert("请输入新密码!")
            return false;
        }
        if(newPassword != newPasswordPasswordConfirm){
            window.alert("请确认好你的新密码!")
            return false;
        }
        return true;
    }
</script>
