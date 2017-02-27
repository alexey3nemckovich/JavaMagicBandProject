<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User pages</title>
</head>
<body>
<%= request.getAttribute("data")%>
    <div>
        Id page = ${data.id} ${ids}
    <br/>
        Name page = ${data.name}
    </div>
</body>
</html>
