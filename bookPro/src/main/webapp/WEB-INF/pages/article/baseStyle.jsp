<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="keywords" content="极简技术之道,极简技术,技术" />
  <meta name="description" content="极简技术之道是致力于前沿技术以及行业领域内的精彩软文的分享交流网站，并提供资源免费下载的原创网站" />
  <meta name="keywords2" content="原创,技术分享交流,技术原创网站" />
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
