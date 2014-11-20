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
