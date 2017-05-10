<%@ page import="org.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <link rel="stylesheet" href="../css/text.css">
    <link rel="stylesheet" href="../css/reference.css">
    <link rel="stylesheet" href="../css/site_style.css">
    <link rel="stylesheet" href="../css/bean-table.css">
    <link rel="stylesheet" href="../css/action-button.css">
    <link rel="stylesheet" href="../../css/text.css">
    <link rel="stylesheet" href="../../css/reference.css">
    <link rel="stylesheet" href="../../css/site_style.css">
    <link rel="stylesheet" href="../../css/bean-table.css">
    <link rel="stylesheet" href="../../css/action-button.css">
    <title>${name}</title>
</head>

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

<body class="site-background site-text-container">

    <div class="site-header">
        AutoServiceShop
    </div>

    <div style="text-align: center; margin: 10px;">
        <a class="action-button" style="text-decoration: none;" href="../../index.jsp">To main page</a>
        <a class="action-button" style="text-decoration: none;" href="/bean.ass">To list of tables</a>
        <a class="action-button" style="text-decoration: none;" href="/bean/view.ass?tableName=${tableName}&page=1&countRecords=3">To table ${tableName}</a>
    </div>
    
    <div>

        <h1 style="margin: 0px;">
            ${tableName}
        </h1>

        <c:if test="${not empty result}">
            <h2>
                    ${result}
            </h2>
        </c:if>

        <form style="margin: 0;" id="input-form" action="<%=formAction%>"  method="post" hidden>
        </form>

        <div style="text-align: center;">

            <div class="bean-table" style="margin: 0 auto; margin-bottom: 40px;">

                <c:forEach items="${fields}" var="field">

                    <div class="bean-table-row">

                        <div class="bean-table-cell">
                           ${field.getKey()}
                        </div>

                        <div class="bean-table-cell">

                            <c:choose>

                                <c:when test="${hiddenFieldsNames.contains(field.getKey())}">

                                    <input form="input-form" type="hidden" name="${field.getKey()}" value="${field.getValue()}"/>

                                </c:when>

                                <c:when test="${notModifiableFieldsNames.contains(field.getKey())}">

                                    <input form="input-form" type="text" name="${field.getKey()}" value="${field.getValue()}" readonly/>

                                </c:when>

                                <c:otherwise>

                                    <input form="input-form" type="text" name="${field.getKey()}" value="${field.getValue()}"/>

                                </c:otherwise>

                            </c:choose>

                        </div>

                    </div>

                </c:forEach>

            </div>

            <input form="input-form" style="max-width: 100px;" align="center" type="submit" value="${action}"/>

        </div>

    </div>

</body>
</html>
