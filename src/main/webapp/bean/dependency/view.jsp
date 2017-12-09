<%@ page import="main.com.bsuir.autoservice.service.Dependency" %>
<%@ page import="java.util.List" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="main.com.bsuir.autoservice.bean.Bean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<html>

<head>
    <title>Bean dependency</title>
    <link rel="stylesheet" href="../../css/text.css">
    <link rel="stylesheet" href="../../css/reference.css">
    <link rel="stylesheet" href="../../css/site_style.css">
    <link rel="stylesheet" href="../../css/bean-table.css">
    <link rel="stylesheet" href="../../css/paginator.css">
    <link rel="stylesheet" href="../../css/action-button.css">
</head>

<body class="site-background site-text-container">

    <div class="site-header">
        AutoServiceShop
    </div>

    <div style="text-align: center; margin: 10px;">
        <a class="action-button" style="text-decoration: none;" href="../../index.jsp">To main page</a>
        <a class="action-button" style="text-decoration: none;" href="/bean.ass">To list of tables</a>
        <a class="action-button" style="text-decoration: none;" href="/bean/view.ass?tableName=${tableName}&page=${page}&countRecords=${countRecords}">Back to table ${tableName}</a>
    </div>

    <%
        String url = (String) request.getAttribute("javax.servlet.include.query_string");

        Dependency dependency = (Dependency) request.getAttribute("dependency");

        String dependencyJson = dependency.getUrlEncodedJson();

        String notModifiableFieldsNames = dependency.getName();

        Map<String, String> defaultValuesMap = new LinkedHashMap<>();
        defaultValuesMap.put(dependency.name, dependency.value.toString());
        JSONObject jsonObject = new JSONObject(defaultValuesMap);
        String defaultValues = jsonObject.toString();
        defaultValues = defaultValues.replace("\"", "\\\"");
        defaultValues = URLEncoder.encode(defaultValues, "UTF-8");
    %>

    <div>

        <h1><%=dependency.getTableName()%></h1>

        <c:if test='<%=!request.getAttribute("action").equals("get")%>'>
            <h2>${result}</h2>
        </c:if>

        <c:if test="${!empty beans}">

            <div>

                <c:forEach items="${beans}" var="bean">
                    <form id="${bean.toString()}">
                    </form>
                </c:forEach>

                <div class="bean-table">

                        <%-- Header row --%>
                    <div class="bean-table-header-row">

                        <c:forEach items="${beans.get(0).getFieldsOrdered()}" var="field">
                            <div class="bean-table-cell">${field.getName()}</div>
                        </c:forEach>

                        <div class="bean-table-cell">
                            Action
                        </div>

                        <div class="bean-table-cell">
                            Dependencies
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

                                <button form="${bean.toString()}" class="text-container" formmethod="post" type="submit" formaction="/bean/dependency/edit.ass?tableName=<%=dependency.getTableName()%>&notModifiableFieldsNames=<%=notModifiableFieldsNames%>">
                                    Edit
                                </button>

                                <button form="${bean.toString()}" class="text-container" formmethod="post" type="submit" formaction="/bean/dependency/view.ass?action=delete&tableName=${tableName}&dependency=<%=dependencyJson%>&page=${page}&countRecords=${countRecords}">
                                    Delete
                                </button>

                            </div>

                            <%-- Dependencies --%>
                            <div class="bean-table-cell" style="min-width: 180px;">
                                <c:choose>
                                    <c:when test="${!empty dependencyMap.get(bean)}">
                                        <form style="margin-bottom: 0px">

                                            <select name="dependency">
                                                <c:forEach items="${dependencyMap.get(bean)}" var="dependecy">
                                                    <option value="${dependecy.getUrlEncodedJson()}">
                                                            ${dependecy.getTableName()}
                                                    </option>
                                                </c:forEach>
                                            </select>

                                            <button formmethod="post" type="submit" formaction="/bean/dependency/view.ass?action=get&tableName=<%=dependency.getTableName()%>&page=1&countRecords=3">
                                                View
                                            </button>

                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                    </c:forEach>

                </div>

                <div style="text-align: center;">
                    <c:url var="searchUri" value="/bean/dependency/view.ass?action=get&tableName=${tableName}&dependency=<%=dependencyJson%>&page=##&countRecords=${countRecords}" />
                    <paginator:display
                            maxLinks="5"
                            currPage="${page}"
                            totalPages="${totalPagesCount}"
                            uri="${searchUri}"
                    />
                </div>
            </div>

        </c:if>

        <form action="/bean/dependency/add.ass?tableName=<%=dependency.getTableName()%>" method="POST">

            <input type="hidden" name="notModifiableFieldsNames" value="<%=notModifiableFieldsNames%>" />
            <input type="hidden" name="defaultValues" value="<%=defaultValues%>" />

            <a class="action-button" style="text-decoration: none;" href="#" onclick="this.parentNode.submit()">New '<%=dependency.getTableName()%>' dependency to ${tableName}</a>

        </form>

    </div>

</body>
</html>
