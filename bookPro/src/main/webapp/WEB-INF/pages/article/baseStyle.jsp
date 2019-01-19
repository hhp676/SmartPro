<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhp
  Date: 2018/3/19
  Time: 16:13
  页面基础模版来源:http://www.yangqq.com/
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="keywords" content="本页面模版来源:http://www.yangqq.com,页面数据与后台代码及数据库设计由大宇完成" />
  <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。" />
  <meta name="keywords2" content="大宇再次感谢杨青个人博客，让我的前端页面不再烦恼，谢谢" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%--360收录标签--%>
  <meta name="360-site-verification" content="8510aa2cbbb515daae7b11ef7218368d" />
  <link href="<c:url value="/css/base.css"/>" rel="stylesheet">
  <link href="<c:url value="/css/index.css"/>" rel="stylesheet">
  <!--[if lt IE 9]>
  <script src="/js/modernizr.js"></script>
  <![endif]-->
  <script>
    window.onload = function ()
    {
      var oH2 = document.getElementsByTagName("h2")[0];
      var oUl = document.getElementsByTagName("ul")[0];
      oH2.onclick = function ()
      {
        var style = oUl.style;
        style.display = style.display == "block" ? "none" : "block";
        oH2.className = style.display == "block" ? "open" : ""
      }
    }
  </script>
</head>
<body>

</body>
</html>
