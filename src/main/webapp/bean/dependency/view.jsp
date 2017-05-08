<%@ page import="main.com.bsuir.autoservice.service.Dependency" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="main.com.bsuir.autoservice.bean.Bean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Bean dependency</title>
</head>

<body>

<div>
    <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
</div>

<a href="../../index.jsp">To main page</a>

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

    <c:if test="${!empty beans}">

        <div>

            <h1>${dependencyTableName}</h1>

            <c:if test='<%=!request.getAttribute("action").equals("get")%>'>
                <h2>${result}</h2>
            </c:if>

            <table>

                    <%-- Header row --%>
                <tr>

                    <c:forEach items="${beans.get(0).getFieldsOrdered()}" var="field">
                        <th>${field.getName()}</th>
                    </c:forEach>

                    <th>
                        Action
                    </th>

                </tr>

                    <%-- Entities rows --%>
                <c:forEach items="${beans}" var="bean">

                    <%-- Every enrity row is form --%>
                    <form>
                        <tr>

                                <%-- Enity fields --%>
                            <c:forEach items="${bean.getFieldsOrdered()}" var="field">
                                <td>
                                    <input type="text" name="${field.getName()}" value="${field.get(bean)}" readonly/>
                                </td>
                            </c:forEach>

                                <%-- Action buttons --%>
                            <td>

                                <button formmethod="post" type="submit" formaction="/bean/dependency/edit.ass?tableName=<%=dependency.getTableName()%>&notModifiableFieldsNames=<%=notModifiableFieldsNames%>">
                                    Edit
                                </button>

                                <button formmethod="post" type="submit" formaction="/bean/dependency/view.ass?action=delete&tableName=${tableName}&dependency=<%=dependencyJson%>&page=${page}&countRecords=${countRecords}">
                                    Delete
                                </button>

                            </td>

                        </tr>
                    </form>

                </c:forEach>

            </table>

            <c:url var="searchUri" value="/bean/dependency/view.ass?action=get&tableName=${tableName}&dependency=<%=dependencyJson%>&page=##&countRecords=${countRecords}" />
            <paginator:display
                    maxLinks="5"
                    currPage="${page}"
                    totalPages="${totalPagesCount}"
                    uri="${searchUri}"
            />
        </div>

        <form action="/bean/dependency/add.ass?tableName=<%=dependency.getTableName()%>" method="POST">

            <input type="hidden" name="notModifiableFieldsNames" value="<%=notModifiableFieldsNames%>" />
            <input type="hidden" name="defaultValues" value="<%=defaultValues%>" />

            <a href="#" onclick="this.parentNode.submit()">Add new dependency</a>

        </form>

    </c:if>

</div>

</body>
</html>
