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
    <title>用户注册</title>
</head>
<body>
<div class="reg_div">
    <p>注册</p>
    <ul class="reg_ul">
        <li>
            <span>用户昵称：</span>
            <input type="text" name="nickname" placeholder="2-6位汉字" class="reg_nickname">
            <span class="tip nickname_hint"></span>
        </li>
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
            <span>确认密码：</span>
            <input type="password" name="passwordConfirm" placeholder="确认密码" class="reg_confirm">
            <span class="tip confirm_hint"></span>
        </li>
        <%--<li>
            <span>身份证：</span>
            <input type="text" name="idCard" placeholder="身份证" class="reg_idCard">
            <span class="tip idCard_hint"></span>
        </li>--%>
        <li>
            <span>手机号码：</span>
            <input type="text" name="mobile" placeholder="手机号" class="reg_mobile">
            <span class="tip mobile_hint"></span>
        </li>
        <li>
            <button type="button" name="button" class="red_button">注册</button>
        </li>
    </ul>
</div>

<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/> "></script>
<script type="text/javascript">
    // user
    var nickname_Boolean = false;
    var username_Boolean = false;
    var password_Boolean = false;
    var varConfirm_Boolean = false;
    var idCard_Boolean = true;
    var Mobile_Boolean = true;
    $('.reg_nickname').blur(function () {
        //汉字正则,2到6位汉字
        if ((/^[\u4e00-\u9fa5]{2,6}[a-zA-Z0-9]{0,3}$/).test($(".reg_nickname").val())) {
            $('.nickname_hint').html("✔").css("color", "green");
            nickname_Boolean = true;
        } else {
            $('.nickname_hint').html("×").css("color", "red");
            nickname_Boolean = false;
        }


    });
    $('.reg_username').blur(function () {
        if ((/^[a-zA-Z0-9_-]{4,16}$/).test($(".reg_username").val())) {
            $('.username_hint').html("✔").css("color", "green");
            username_Boolean = true;
        } else {
            $('.username_hint').html("×").css("color", "red");
            username_Boolean = false;
        }
    });
    // password
    $('.reg_password').blur(function () {
        if ((/^[a-z0-9_-]{6,16}$/).test($(".reg_password").val())) {
            $('.password_hint').html("✔").css("color", "green");
            password_Boolean = true;
        } else {
            $('.password_hint').html("×").css("color", "red");
            password_Boolean = false;
        }
    });


    // password_confirm
    $('.reg_confirm').blur(function () {
        if (($(".reg_password").val()) == ($(".reg_confirm").val())) {
            $('.confirm_hint').html("✔").css("color", "green");
            varConfirm_Boolean = true;
        } else {
            $('.confirm_hint').html("×").css("color", "red");
            varConfirm_Boolean = false;
        }
    });


    // IdCard
   /* $('.reg_idCard').blur(function () {
        if ((/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/).test($(".reg_idCard").val())) {
            $('.idCard_hint').html("✔").css("color", "green");
            idCard_Boolean = true;
        } else {
            $('.idCard_hint').html("×").css("color", "red");
            idCard_Boolean = false;
        }
    });*/


    // Mobile
    $('.reg_mobile').blur(function () {
        if ((/^1[34578]\d{9}$/).test($(".reg_mobile").val())) {
            $('.mobile_hint').html("✔").css("color", "green");
            Mobile_Boolean = true;
        } else {
            $('.mobile_hint').html("×").css("color", "red");
            Mobile_Boolean = false;
        }
    });


    // click
    $('.red_button').click(function () {
        var nickname = $('.reg_nickname').val();
        var username = $('.reg_username').val();
        var password = $('.reg_password').val();
//        var idCard = $('.reg_idCard').val();
        var mobile = $('.reg_mobile').val();
        if (!(nickname_Boolean && username_Boolean && password_Boolean && varConfirm_Boolean && idCard_Boolean && Mobile_Boolean)) {
            window.alert("请完善相关注册信息");
            return;
        }
        var varJson = {
            "nickname": nickname, "loginName": username,
//            "idCard": idCard,
            "cellPhone": mobile,
            "password": password,"enabled":true
        };
        $.ajax(
                {
                    type: "post",
                    url: "<c:url value="/actions/security/registerSubmit"/>",
                    dataType: 'json',
                    data: varJson,
                    success: function (data) {
                        if (data.success) {
                            window.alert("注册成功!");
                            window.location.href = "<c:url value="/"/>";
                        } else {
                            window.alert("注入用户失败,原因："+data.other);
                        }
                    },
                    error: function () {
                        window.alert("系统内部出错！");
                    }
                });

    });
</script>

</body>
</html>
