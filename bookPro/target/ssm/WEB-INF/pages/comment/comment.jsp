<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/22
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/css/baseFormStyle.css"/> " type="text/css"/>
</head>
<style>
    .comment {
        width: 60%;
        position: relative;
        left: 20%;
        border: 1px;
        text-align: center;
        margin: 9px;
        background-color: #fff;
        border-radius: 5px;
        transition: all .5s;
    }

</style>
<body>
<div class="form-horizontal" role="form" style="margin:10px">
    <% int i =1;%>
    <c:forEach items="${comments}" var="comment">
        <article class="comment">
            <section style="text-align:left">
                <p style="color: #c88326"><%= i++  %>楼&nbsp;&nbsp;${comment.name}&nbsp;&nbsp;${comment.date}</p>
                <p style="font-size: 20px">${comment.content}</p><br/>
            </section>
        </article>
    </c:forEach>
    <div class="form-group">
        <h3>评论</h3>
        <div class="col-sm-3">
            <textarea id="commentContent" class="form-control" rows="4" placeholder="来评论两句吧！"></textarea>
        </div>
    </div>
    <div class="form-group">
        <input type="hidden" id="articleId" class="form-control" value="${article.id}">
    </div>
    <p style="text-align:left">
        <button id="commentButton" class="myBtn" type="submit">提交</button>
    </p>
</div>
</body>
</html>
<script src="<c:url value="/js/jquery.js"/>"></script>
<script>
    //关注点11：使用ajax方式向后台发送评论信息
    $("#commentButton").click(function () {
        var commentContent = $("#commentContent").val();
        var articleId = $("#articleId").val();
        if(commentContent==null || commentContent==''){
            alert('请先填写你的评论在提交');
            return;
        }
        var varJson = {
            "content": commentContent,
            "articleId": articleId
        };

        $.ajax(
                {
                    type: "post",
                    url: "<c:url value="/actions/comment/insertComment"/>",
                    dataType: 'json',
                    data: varJson,
                    success: function (data) {
                        if (data.success) {
                            //刷新,重新加载本页面,本页面是readArticle请求
                            window.location.reload();
                        } else {
                            window.alert("评论失败,可能的原因是:"+data.reason);
                        }
                    },
                    error: function () {
                        //发送本请求需要登录
                        //本请求是ajax请求，所以会经过Shiro的拦截器
                        //目地是为了：必须保证用户是在登录情况下，才能够发送评论请求
                        //全局搜索  bean:myAjaxFilter  能够找到对应的Spring配置文件
                        //参考：https://blog.csdn.net/yanluandai1985/article/details/79728147
                        window.alert("您可能还没有登录")
                        window.location.href="<c:url value="/actions/security/login"/>";
                    }
                });

    })

</script>
