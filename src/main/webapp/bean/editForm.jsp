<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.net.URLEncoder" %>
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
        String action = request.getAttribute("action").toString();
        String tableName = request.getAttribute("tableName").toString();
        String formAction = "?tableName=" + tableName + "&action=" + action;
        if(action.equals("edit")){
            Map<String, String> fields = (Map<String, String>)request.getAttribute("fields");
            JSONObject oldValues = new JSONObject(fields);
            String oldValuesParamString = oldValues.toString();
            oldValuesParamString = oldValuesParamString.replace("\"", "\\\"");
            formAction += "&oldValues=" + URLEncoder.encode(oldValuesParamString, "UTF-8");
        }
    %>

    <h2>
        <%=formAction%>
    </h2>

    <c:if test="${not empty result}">
        <h1>
            ${result}
        </h1>
    </c:if>

    <div class="centered_parent">

        <form class="centered" action="<%=formAction%>"  method="post">

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
