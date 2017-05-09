<%@ page import="org.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <link rel="stylesheet" href="/main/webapp/css/text.css">
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
        formAction += "&notModifiableFieldsNames=";
        List<String> notModifiableFieldsNames = (List<String>)request.getAttribute("notModifiableFieldsNames");
        for(int i = 0; i < notModifiableFieldsNames.size(); i++){
            formAction += notModifiableFieldsNames.get(i);
            if(i != notModifiableFieldsNames.size() - 1){
                formAction += ',';
            }
        }
        if(action.equals("edit")){
            Map<String, String> fields = (Map<String, String>)request.getAttribute("fields");
            JSONObject oldValues = new JSONObject(fields);
            String oldValuesParamString = oldValues.toString();
            oldValuesParamString = oldValuesParamString.replace("\"", "\\\"");
            formAction += "&oldValues=" + URLEncoder.encode(oldValuesParamString, "UTF-8");
        }
    %>

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
                            <c:choose>
                                <c:when test="${notModifiableFieldsNames.contains(field.getKey())}">
                                    <input type="text" name="${field.getKey()}" value="${field.getValue()}" readonly/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" name="${field.getKey()}" value="${field.getValue()}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <input align="center" type="submit" value="${action}"/>

        </form>

    </div>

</body>
</html>
