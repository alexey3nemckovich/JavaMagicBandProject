<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Beans</title>
    <link rel="stylesheet" href="./css/text.css">
    <link rel="stylesheet" href="./css/reference.css">
    <link rel="stylesheet" href="./css/site_style.css">
</head>
<body class="site-background">

    <div class="site-header">
        AutoServiceShop
    </div>

    <div align="center">
        <table>

            <c:forEach items="${dbBeanNames}" var="name">
                <tr>
                    <td>
                        <a class="reference" href="<c:url value="/bean/view.ass?tableName=${name}&page=1&countRecords=3"/>">
                            Table '${name}'
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

</body>
</html>
