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

    <%
        String url = (String) request.getAttribute("javax.servlet.include.query_string");
        String action = request.getAttribute("action").toString();
        String tableName = request.getAttribute("tableName").toString();
        String formAction = url + "?tableName=" + tableName + "&action=" + action;
        if(action.equals("edit")){
            
            formAction += "";
        }
    %>

    <c:if test="${not empty result}">
        <h1>
            ${result}
        </h1>
    </c:if>

    <div class="centered_parent">

        <form class="centered" action=""<%=formAction%>"  method="post">

            <table>
                <c:forEach items="${fields}" var="field">
                    <tr>
                        <td>
                           ${field.getKey()}
                        </td>
                        <td>
                            <input type="text" name="${field.getKey()}" value="${field.getValue()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <input align="center" type="submit" value="${action}"/>

        </form>

    </div>

</body>
</html>
