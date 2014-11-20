<%--
  Created by IntelliJ IDEA.
  User: serious
  Date: 20.11.14
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<div><%= request.getParameter("program")%>
</div>
<div><%= request.getParameter("tape")%>
</div>
<div><%= request.getAttribute("result")%>
</div>
</body>
</html>
