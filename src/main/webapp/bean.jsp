<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Beans</title>
    <link rel="stylesheet" href="/css/classes.css">
</head>
<body>

    <div style = "border: 2px solid black;">
        <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
    </div>

    <div class="centered_parent">
        <table class="centered">

            <c:forEach items="${dbBeanNames}" var="name">
                <tr>
                    <td>
                        <a href="<c:url value="/bean/add.ass?name=${name}"/>">Add new '${name}'</a>
                    </td>
                    <td>
                        <a href="<c:url value="/bean/view.ass?name=${name}&page=1&countRecords=3"/>">View table '${name}'</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

</body>
</html>
