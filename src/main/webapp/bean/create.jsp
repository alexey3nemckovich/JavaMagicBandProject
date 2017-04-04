<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/classes.css">
    <title>${name}</title>
    <%--<style>--%>
        <%--.centered--%>
        <%--{--%>
            <%--display: inline-block;--%>
            <%--margin-left: auto;--%>
            <%--margin-right: auto;--%>
            <%--text-align: left;--%>
        <%--}--%>
    <%--</style>--%>
</head>
<body>

    <div style = "border: 2px solid black;">
        <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
    </div>

    <div class="centered_parent">
        <form class="centered" name="" action="" method="post">
            <table>
                <c:forEach items="${fields}" var="field">
                    <tr>
                        <td>
                           ${field.getName()}
                        </td>
                        <td>
                            <input type="text" name="${field.getName()}" value=""/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input align="center" type="submit" value="Create new ${name}"/>
        </form>
    </div>

</body>
</html>
