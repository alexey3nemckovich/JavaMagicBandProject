<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<html>

<head>
    <title>Bean</title>
    <link rel="stylesheet" href="../css/text.css">
    <link rel="stylesheet" href="../css/reference.css">
    <link rel="stylesheet" href="../css/site_style.css">
    <link rel="stylesheet" href="../css/bean-table.css">
    <link rel="stylesheet" href="../css/paginator.css">
    <link rel="stylesheet" href="../css/action-button.css">
</head>

<body class="site-background site-text-container">

<div class="site-header">
    Notepads admin tool
</div>

<div style="text-align: center; margin: 10px;">
    <a class="action-button" style="text-decoration: none;" href="../../index.jsp">To main page</a>
    <a class="action-button" style="text-decoration: none;" href="/bean.ass">Back to list of tables</a>
</div>

<%
    String url = (String) request.getAttribute("javax.servlet.include.query_string");
%>

<div style="text-align: center">

    <h1>${tableName}</h1>

    <c:if test='<%=!request.getAttribute("action").equals("get")%>'>
        <h2>${result}</h2>
    </c:if>

    <c:if test="${!empty beans}">

        <div>

            <c:forEach items="${beans}" var="bean">
                <form id="${bean.toString()}">
                </form>
            </c:forEach>

            <div class="bean-table" style="margin-left: auto; margin-right: auto;">

                    <%-- Header row --%>
                <div class="bean-table-header-row">

                    <c:forEach items="${beans.get(0).getFieldsOrdered()}" var="field">
                        <div class="bean-table-cell">${field.getName()}</div>
                    </c:forEach>

                    <div class="bean-table-cell">
                        Action
                    </div>

                </div>

                    <%-- Entities rows --%>
                <c:forEach items="${beans}" var="bean">
                    <div class="bean-table-row">

                            <%-- Every enrity row is form --%>
                            <%-- Enity fields --%>
                        <c:forEach items="${bean.getFieldsOrdered()}" var="field">
                            <div class="bean-table-cell">
                                <input form="${bean.toString()}" type="text" name="${field.getName()}" value="${field.get(bean)}" readonly/>
                            </div>
                        </c:forEach>

                            <%-- Action buttons --%>
                        <div class="bean-table-cell" style="min-width: 100px;">

                            <button form="${bean.toString()}" formmethod="post" type="submit" formaction="/bean/edit.ass?tableName=${tableName}">
                                Edit
                            </button>

                            <button form="${bean.toString()}" formmethod="post" type="submit" formaction="/bean/view.ass?action=delete&tableName=${tableName}&page=${page}&countRecords=${countRecords}">
                                Delete
                            </button>

                        </div>

                    </div>
                </c:forEach>

            </div>

            <div style="text-align: center;">
                <c:url var="searchUri" value="/bean/view.ass?tableName=${tableName}&page=##&countRecords=${countRecords}"/>
                <paginator:display
                        maxLinks="5"
                        currPage="${page}"
                        totalPages="${totalPagesCount}"
                        uri="${searchUri}"
                />
            </div>

        </div>

    </c:if>

    <form action="/bean/add.ass?tableName=${tableName}" method="POST">

        <a class="action-button" style="text-decoration: none;" href="#" onclick="this.parentNode.submit()">New '${tableName}'</a>

    </form>

</div>

</body>
</html>