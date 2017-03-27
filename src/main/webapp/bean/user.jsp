<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User pages</title>
</head>
<body>
<p>
    Using java command
    <%= request.getAttribute("data")%>
</p>
    <div>
        <p>Id page = '${data[0].id }'</p>
        <p>Name page = ${data[0].name}</p>
    </div>
</body>
</html>
