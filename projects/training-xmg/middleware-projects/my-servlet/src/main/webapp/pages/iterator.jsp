<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: langji
  Date: 7/7/21
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/iterator-tld" prefix="cc" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        List list = new ArrayList<>();
        list.add("iterator-");
        list.add("1-");
        list.add("2-");
        list.add("3-");

        request.setAttribute("list", list);
    %>
    <cc:iterator var="str" items="${list}">
        ${str}
    </cc:iterator>
</body>
</html>
