<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/20
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 重置文件 -->
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/style2.css"/> ">
    <title>用户登录</title>
</head>
<body>
<div class="reg_div">
    <p>用户登录</p>
    <ul class="reg_ul">
        <li>
            <span>登录帐号：</span>
            <input type="text" name="username" placeholder="4-8位用户名" class="reg_username">
            <span class="tip username_hint"></span>
        </li>
        <li>
            <span>密码：</span>
            <input type="password" name="password" placeholder="6-16位密码" class="reg_password">
            <span class="tip password_hint"></span>
        </li>
        <li>
            <button type="button" name="button" class="red_button">登录</button>
        </li>
    </ul>
</div>

<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/> "></script>
<script type="text/javascript">
    // click
    $('.red_button').click(function () {

        var username = $('.reg_username').val();
        var password = $('.reg_password').val();
        if ((username ==null || password==null)) {
            window.alert("非法输入");
            return;
        }
        if(username == ''||password==''){
            window.alert("请根据页面提示输入相关信息");
            return;
        }
        var varJson = {
            "loginName": username,
            "password": password
        };
        //关注点11，这里是登录页面，上面已经获取到了用户名与密码。
        //然后通过ajax方式发送给后端
        //在AccountSecurityController中的accountLogin方法上
        //请查看关注点12
        $.ajax(
                {
                    type: "post",
                    url: "<c:url value="/actions/security/accountLogin"/>",
                    dataType: 'json',
                    data: varJson,
                    //后台返回了一个JsonReponse对象，这个对象中有success属性
                    //这里会把那个JsonReponse对象映射到data变量
                    //如果data变量的success属性是true，那么就跳转到登录的首页
                    success: function (data) {
                        if (data.success) {
                            window.location.href = "<c:url value="/actions/article/list"/>";
                        } else {
                            window.alert("登录失败,原因："+data.other);
                        }
                    },
                    //如果ajax没有任何响应，那么就提示用户系统内部出错
                    error: function () {
                        window.alert("系统内部出错！");
                    }
                });

    });
</script>

</body>
</html>
