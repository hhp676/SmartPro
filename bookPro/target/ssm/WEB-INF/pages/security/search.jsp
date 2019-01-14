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
      <span>用户管理页面</span>
    </div>
    <form id="searchForm" name="searchForm" method="post" action="<c:url value="/actions/security/account/search"/> ">
      <div class="search">
        <span>查询条件：</span>
        <select name="conditionValue" id="conditionValue">
          <option value="">--请选择--</option>
          <option value="nickname">用户昵称</option>
          <option value="loginName">登录名</option>
          <option value="idCard">身份证</option>
          <option value="cellPhone">手机号</option>
        </select>
        <input type="text" id="searchValue" name="searchValue" placeholder="请输入详细内容"/>
        <span>是否可用：</span>
        <select name="enabledValue" id="enabledValue">
          <option value="">--请选择--</option>
          <option value="false">不可用</option>
          <option value="true">可用</option>
        </select>
        <input type="button"  onclick="formSubmit()" value="搜 索"/>
        <a href="<c:url value="/actions/security/addAccount"/> ">添加用户</a>
      </div>
    </form>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
      <tr class="firstTr">
        <th width="5%">用户ID</th>
        <th width="10%">用户昵称</th>
        <th width="10%">登录名</th>
        <th width="20%">身份证</th>
        <th width="20%">手机号</th>
        <th width="10%">是否可用</th>
        <th width="25%">操作</th>
      </tr>
      <c:forEach items="${accounts}" var="account">
        <tr>
          <td>${account.id}</td>
          <td>${account.nickname}</td>
          <td>${account.loginName}</td>
          <td>${account.idCard}</td>
          <td>${account.cellPhone}</td>
          <td>
            <c:if test="${account.enabled}">
              可用
            </c:if>
            <c:if test="${!account.enabled}">
              不可用
            </c:if>
          </td>
          <td>
            <a href="<c:url value="/actions/security/resetPassword/${account.id}"/> "><img src="<c:url value="/img/mm.png"/>" alt="修改密码" title="修改密码"/></a>
            <a href="<c:url value="/actions/security/updateAccount/${account.id}"/> "><img src="<c:url value="/img/xiugai.png"/>" alt="修改" title="修改"/></a>
            <a href="#" class="removeBill"><img src="<c:url value="/img/schu.png"/> " alt="删除" title="删除"/></a>
          </td>
        </tr>
      </c:forEach>
      <!--这里没有分页代码 -->
    </table>
  </div>

</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
  <div class="removerChid">
    <h2>提示</h2>

    <div class="removeMain">
      <p>你确定要删除该用户吗？</p>
      <a href="#" id="yes">确定</a>
      <a href="#" id="no">取消</a>
    </div>
  </div>
</div>

<jsp:include page="../commons/footer.jsp"/>

</body>
</html>
<script>
  function formSubmit(){
    $("#searchForm").submit();
  }
</script>