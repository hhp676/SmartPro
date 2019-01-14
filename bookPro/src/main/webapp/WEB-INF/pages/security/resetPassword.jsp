<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/9
  Time: 14:12
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
            <span>用户管理页面 >> 用户密码重置页面</span>
        </div>
        <div class="providerAdd">
            <form action="#">
                <div class="">
                    <label for="password">新密码：</label>
                    <input type="password" name="password" id="password" required/>
                    <span>*请输入用户新密码</span>
                </div>
                <div class="">
                    <label for="passwordConfirm">确认新密码：</label>
                    <input type="password" name="passwordConfirm" id="passwordConfirm" required/>
                    <span>*请确认用户新密码</span>
                </div>

                <div class="providerAddBtn">
                    <input type="button" value="保存" onclick= "return formSubmit()"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>

            </form>
        </div>

    </div>
</section>
<jsp:include page="../commons/footer.jsp"/>
</body>

<script type="text/javascript" >
    function formSubmit(){
        var password = $("#password").val();
        var passwordConfirm = $("#passwordConfirm").val();

        if (password == '' || password == null) {
            window.alert("请输入登录密码！");//
            return ;
        }
        if (passwordConfirm == '' || passwordConfirm == null) {
            window.alert("请确认登录密码！");//
            return ;
        }

        if(password != passwordConfirm){
            window.alert("两次密码输入不一致！");//
            return ;
        }

        var varJson = {
            "password": password, "accountId": ${account.id}
        };

        $.ajax(
                {
                    type: "post",
                    url: "<c:url value="/actions/security/resetPassword"/>",
                    dataType: 'json',
                    data: varJson,
                    success: function (data) {
                        if (data.success) {
                            window.alert("重置密码成功!");
                            window.location.href = "<c:url value="/actions/accountManager"/>";
                        } else {
                            window.alert("重置密码失败!");
                        }
                    },
                    error: function () {
                        window.alert("重置密码操作错误!");
                    }
                });
    }



</script>
</html>
