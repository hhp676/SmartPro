<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/8
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客后台管理系统</title>
    <jsp:include page="../commons/style.jsp"/>
    <jsp:include page="../commons/header.jsp"/>

</head>
<body>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <nav>
                <ul class="list">
                    <c:forEach items="${menus}" var="menu">
                        <li>
                            <a href="<c:url value="${menu.uri}"/>">${menu.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>文章管理页面</span>
        </div>
        <form id="searchForm" name="searchForm" method="post" action="<c:url value="/actions/article/search"/> ">
            <div class="search">
                <span>查询条件：</span>
                <select name="conditionValue" id="conditionValue">
                    <option value="">--请选择--</option>
                    <option value="id">文章ID</option>
                    <option value="author">作者</option>
                </select>
                <input type="text" id="searchValue" name="searchValue" placeholder="请输入详细内容"/>
                <input type="button"  onclick="formSubmit()" value="搜 索"/>
            </div>
        </form>
        <!--账单表格 样式和供应商公用-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="5%">文章ID</th>
                <th width="20%">文章标题</th>
                <th width="10%">作者</th>
                <th width="20%">发布日期</th>
                <th width="20%">点击量</th>
                <th width="25%">操作</th>
            </tr>
            <c:forEach items="${articles}" var="article">
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>${article.author}</td>
                    <td>${article.time}</td>
                    <td>${article.click}</td>
                    <td>
                        <a href="<c:url value="/actions/article/view?articleId=${article.id}"/> "><img src="<c:url value="/img/read.png"/>" alt="查看" title="查看"/></a>
                        <a href="javascript:void(0)" onclick="removeArticle(${article.id})"><img src="<c:url value="/img/schu.png"/> " alt="删除" title="删除"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>

        <div class="removeMain">
            <p>你确定要删除该文章吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<jsp:include page="../commons/footer.jsp"/>

</body>
</html>
<script>
    function removeArticle(id){
        if(window.confirm("你真的要删除这篇文章吗？")){
            window.alert("数据库操作中!")
            window.location.href="<c:url value="/actions/article/deleteArticle?articleId="/>"+id;
        }else{
            window.alert("取消删除!")
        }
    }
</script>