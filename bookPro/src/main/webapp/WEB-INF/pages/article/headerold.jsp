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

       /* .text-border{
            text-shadow:#FF0000 0 0 10px;
            color:white;
            font-size:30px;
            padding-left: 40px;
        }

        body {
            font: 90%/160% Arial, Helvetica, sans-serif;
            color: #666;
            width: 900px;
            max-width: 96%;
            margin: 0 auto;
        }
        h4{font-size:14px; line-height:30px}
        p{line-height:30px}*/
        /* nav */
        .nav {
            position: relative;
           /* margin: 20px 0;*/
        }
        .nav ul {
            /*margin: 0;
            padding: 0;*/
        }
       /* .nav li {
            margin: 0 5px 10px 0;
            padding: 0;
            list-style: none;
            display: inline-block;
        }
        .nav a {
            padding: 3px 12px;
            text-decoration: none;
            color: #999;
            line-height: 100%;
        }*/
        .nav a:hover {
            color: #d0d0d0;
        }
        .nav .current a {
            background: #999;
            color: #fff;
            border-radius: 5px;
        }

        /* right nav */
        .nav.right ul {
            text-align: right;
        }


        @media screen and (max-width: 600px) {
            .nav {
              /*  position: relative;*/
              /*  min-height: 40px;*/
            }

            .nav ul {
                width: 180px;
                padding: 5px 0;
                position: absolute;
                top: 0;
                left: 0;
                border: solid 1px #aaa;
                /*background: #fff url(../../../images/menu.png) no-repeat 10px 11px;*/
                background: #0c89de url("<c:url value="/images/menu.png"/>") no-repeat 10px 11px;
                border-radius: 5px;
                box-shadow: 0 1px 2px rgba(0, 0, 0, .3);
            }

            .nav li {
                display: none; /* hide all <li> items */
                margin: 0;
            }

            .nav .current {
                display: block; /* show only current <li> item */
            }

            .nav a {
                display: block;
                padding: 5px 5px 5px 32px;
                text-align: left;
            }

            .nav .current a {
                background: none;
                color: #666;
            }

            /* on nav hover */
            .nav ul:hover {
                background-image: none;
            }

            .nav ul:hover li {
                display: block;
                margin: 0 0 5px;
            }

            .nav ul:hover .current {
                /*background: url(../../../images/check.png) no-repeat 10px 7px;*/
            }

            /* right nav */
            .nav.right ul {
                left: auto;
                right: 0;
                color: #0b2e13;
            }
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


    <nav class="nav right">
      <%-- <div>--%>
          <%-- <ul style=" float: left; height:40px;  background-color: #3d3d3d; width: 30%; text-align: left">
               <div class="text-border">极 简 技 术 之 道 </div>
           </ul>--%>
          <%-- <ul style=" float: right; height:40px; background-color: #3d3d3d; width: 70%;">--%>
            <ul>
               <li class="current"><a >网站首页</a></li>
               <li><a href="<c:url value="/actions/home"/> ">后台管理中心</a></li>
               <li><a href="https://www.csdn.net" target="_blank">博客导航</a></li>
               <li><a href="#">留言</a></li>
               <c:if test="${account!=null}">
                   <li>
                       <a href="<c:url value="/actions/article/myBlogSpace"/> ">个人中心</a>
                   </li>
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

               <c:if test="${account!=null}">
                   <li><a href="<c:url value="/actions/home/logout"/> ">退出</a></li>
               </c:if>
           </ul>
     <%--  </div>--%>
    </nav>
</header>
<body>

</body>
</html>
