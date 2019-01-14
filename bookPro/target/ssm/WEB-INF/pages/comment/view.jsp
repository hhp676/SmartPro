<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/29
  Time: 11:16
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
      <span>评论管理页面</span>
    </div>

    <table class="providerTable" cellpadding="0" cellspacing="0">
      <tr class="firstTr">
        <th width="5%">评论ID</th>
        <th width="10%">评论者</th>
        <th width="40%">评论内容</th>
        <th width="20%">评论日期</th>
        <th width="25%">操作</th>
      </tr>
      <c:forEach items="${comments}" var="comment">
        <tr>
          <td>${comment.id}</td>
          <td>${comment.name}</td>
          <td>${comment.content}</td>
          <td>${comment.date}</td>
          <td>
            <a href="<c:url value="/actions//article/readArticle/${comment.articleId}"/> "><img src="<c:url value="/img/read.png"/>" alt="查看" title="查看"/></a>
            <a href="javascript:void(0)" onclick="removeComment(${comment.id})"><img src="<c:url value="/img/schu.png"/> " alt="删除" title="删除"/></a>
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="7">
          <!--下面的分页代码由Jay于18/3/26日修改，可以重用多次，请不要修改太多-->
          <div style="text-align: center">
            <ul class="pagination">
              <li
                      <c:if test="${pageInfo.pageNum==1}">
                        class="disabled"
                      </c:if>>
                <a href="<c:url value="/actions/commentManager?pageNo=1"/> ">&laquo;</a>
              </li>
              <li>
                <!--如果当前页数是第一页，那么点上一页仍然是当前页。 -->
                <a href="<c:url value="/actions/commentManager?pageNo=${pageInfo.pageNum==1?pageInfo.pageNum:pageInfo.pageNum-1}"/> ">上一页</a>
              </li>
              <!--判断最大页数是否超过X，如果超过X则是X，否则是最大页数。防止分页信息过长 -->
              <c:forEach begin="1" end="${pageInfo.pages>8?8:pageInfo.pages}" step="1" var="pageNo">
                <li
                        <c:if test="${pageInfo.pageNum==pageNo}">
                          class="active"
                        </c:if>>
                  <a href="<c:url value="/actions/commentManager?pageNo=${pageNo}"/> ">${pageNo}</a>
                </li>
              </c:forEach>
              <li>
                <!--如果当前页数是最后一页，那么点击下一页仍然是当前页。 -->
                <a href="<c:url value="/actions/commentManager?pageNo=${pageInfo.pageNum==pageInfo.pages?pageInfo.pageNum:pageInfo.pageNum+1}"/> ">下一页</a>
              </li>
              <li
                      <c:if test="${pageInfo.pageNum==pageInfo.pages}">
                        class="disabled"
                      </c:if>>
                <a href="<c:url value="/actions/commentManager?pageNo=${pageInfo.pages}"/> ">&raquo;</a>
              </li>
            </ul>
          </div>
        </td>
      </tr>
    </table>
  </div>

</section>
</body>
</html>>
<script>
  function removeComment(id){
    if(window.confirm("你真的要删除这个评论吗？")){
      window.alert("数据库操作中!")
      window.location.href="<c:url value="/actions/comment/deleteComment?commentId="/>"+id;
    }else{
      window.alert("取消删除!")
    }
  }
</script>