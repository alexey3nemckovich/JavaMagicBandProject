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
        <p>Id page = '${data.id }'</p>
        <p>Attribute page = ${ids}</p>
        <p>Name page = ${data.name}</p>
    </div>
</body>
</html>
