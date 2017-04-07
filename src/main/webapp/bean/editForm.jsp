<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/classes.css">
    <title>${name}</title>
</head>
<body>

    <div style = "border: 2px solid black;">
        <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
    </div>

    <%String url = (String) request.getAttribute("javax.servlet.include.query_string");%>

    <div class="centered_parent">
        <form class="centered" action="${url}?name=${name}&action=${action}"  method="post">
            <table>
                <c:forEach items="${fields}" var="field">
                    <tr>
                        <td>
                           ${field.getKey()}
                        </td>
                        <td>
                            <input type="text" name="${field.getKey()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input align="center" type="submit" value="${action}"/>
        </form>
    </div>

</body>
</html>
