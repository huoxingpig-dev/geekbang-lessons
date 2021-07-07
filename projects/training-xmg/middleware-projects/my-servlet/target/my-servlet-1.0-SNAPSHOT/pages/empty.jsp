<%--
  Created by IntelliJ IDEA.
  User: langji
  Date: 7/6/21
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/empty-tld" prefix="ex" %>
<html>
<head>
    <title>A simple empty tag page</title>
</head>
<body>
<%
    request.setAttribute("cacheControl", "no-cache");
    request.setAttribute("expires" , "-1");
    request.setAttribute("pragma","no-cache");
%>
    <ex:Hello cacheControl="no-cache" expires="-1" pragma="no-cache"/>
</body>
</html>
