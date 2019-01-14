<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <style type="text/css">
        .cbbfixed {position: fixed;right: 10px;transition: bottom ease .3s;bottom: -85px;z-index: 3;cursor:pointer;}
        .cbbfixed .cbbtn {width: 40px;height: 40px;display: block;background-color: #02a2aa;}
        .cbbfixed .gotop {transition: background-color ease .3s;margin-top: 1px;}
        .cbbfixed .gotop .up-icon{float:left;margin:14px 0 0 9px;width:23px;height:12px;background: url(<c:url value="/images/fruitxiao.png"/>); }
        .cbbfixed .gotop:hover {background-color:#2c2d2e;}
        .cbbfixed .cweixin {background: #dadbdd;}
        .cbbfixed .cweixin div {background: url(<c:url value="/images/vx3.png"/>); width: 200px;height: 200px;position: absolute;left: -200px;top: -115px;opacity: 0;transform: scale(0);    -webkit-transform: scale(0);transform-origin: 100% 70%;-webkit-transform-origin: 100% 70%;transition: transform ease .3s,opacity ease .3s;-webkit-transition: all ease .3s;display:none\0;*display:none;}
        .cbbfixed .cweixin:hover div {display: block;transform: scale(1);-webkit-transform: scale(1);opacity: 1;*display:block;}
        .cbbfixed .cweixin .weixin-icon{float: left;margin:12px 0 0 11px;width:18px;height: 18px;  background: url(<c:url value="/images/ewm.png"/>);}

        .text-border{
            text-shadow:#FF0000 0 0 10px;
            color:white;
            font-size:30px;
            padding-left: 40px;
        }
    </style>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script type="text/javascript">
        function chinaz(){
            this.init();
        }
        chinaz.prototype = {
            constructor: chinaz,
            init: function(){
                this._initBackTop();
            },
            _initBackTop: function(){
                var $backTop = this.$backTop = $('<div class="cbbfixed">'+
                    '<a class="cweixin cbbtn"">'+
                    '<span class="weixin-icon"></span><div></div>'+
                    '</a>'+
                    '<a class="gotop cbbtn">'+
                    '<span class="up-icon"></span>'+
                    '</a>'+
                    '</div>');
                $('body').append($backTop);

                $backTop.click(function(){
                    $("html, body").animate({
                        scrollTop: 0
                    }, 120);
                });

                var timmer = null;
                $(window).bind("scroll",function() {
                    var d = $(document).scrollTop(),
                        e = $(window).height();
                    0 < d ? $backTop.css("bottom", "10px") : $backTop.css("bottom", "-90px");
                    clearTimeout(timmer);
                    timmer = setTimeout(function() {
                        clearTimeout(timmer)
                    },100);
                });
            }

        }
        var chinaz = new chinaz();
    </script>
    <%--悬浮框--%>

    <nav>
       <div>
           <ul style=" float: left; height:40px;  background-color: #3d3d3d; width: 30%; text-align: left">
               <div class="text-border">极 简 技 术 之 道 </div>
           </ul>
           <ul style=" float: right; height:40px; background-color: #3d3d3d; width: 70%;">

               <li><a href="<c:url value="/"/> " target="_blank">网站首页</a></li>|
               <li><a href="<c:url value="/actions/home"/> ">后台管理中心</a></li>|
               <li><a href="https://www.csdn.net" target="_blank">博客导航</a></li>|
               <li><a href="#">留言</a></li>|
               <c:if test="${account!=null}">
                   <li>
                       <a href="<c:url value="/actions/article/myBlogSpace"/> ">个人中心</a>
                   </li>
                   |
               </c:if>

               <li>
                   <c:choose>
                       <c:when test="${account==null}">
                           <a href="<c:url value="/actions/security/login"/> ">登录</a>
                       </c:when>
                       <c:otherwise>
                           <a href="#">${account.nickname},欢迎你</a>
                       </c:otherwise>
                   </c:choose>
               </li>
               |
               <li>
                   <c:choose>
                       <c:when test="${account==null}">
                           <a href="<c:url value="/actions/security/register"/> ">注册</a>
                       </c:when>
                       <c:otherwise>
                           <a href="<c:url value="/actions/article/postedit"/> ">写文章</a>
                       </c:otherwise>
                   </c:choose>
               </li>
               |
               <c:if test="${account!=null}">
                   <li><a href="<c:url value="/actions/home/logout"/> ">退出</a></li>
               </c:if>
           </ul>
       </div>
    </nav>
</header>
<body>

</body>
</html>
