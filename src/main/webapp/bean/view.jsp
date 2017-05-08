<%@ page import="main.com.bsuir.autoservice.service.Dependency" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="main.com.bsuir.autoservice.bean.Bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Bean</title>
</head>

<body>

    <div>
        <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
    </div>

    <a href="../../index.jsp">To main page</a>

    <%
        String url = (String) request.getAttribute("javax.servlet.include.query_string");
    %>

    <div>

        <c:if test="${!empty beans}">

            <div>

                <h1>${tableName}</h1>

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

                        <th>
                            Dependencies
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

                                    <button formmethod="post" type="submit" formaction="/bean/edit.ass?tableName=${tableName}">
                                        Edit
                                    </button>

                                    <button formmethod="post" type="submit" formaction="/bean/view.ass?action=delete&tableName=${tableName}&page=${page}&countRecords=${countRecords}">
                                        Delete
                                    </button>

                                </td>

                                <%-- Dependencies --%>
                                <c:if test="${!empty dependencyTableNames}">
                                    <td>
                                        <select name="dependencyTableName">
                                            <c:forEach items="${dependencyTableNames}" var="dependecyTableName">
                                                <option value="${dependecyTableName}">
                                                    ${dependecyTableName}
                                                </option>
                                            </c:forEach>
                                        </select>

                                        <button formmethod="post" type="submit" formaction="/bean/dependency/view.ass?action=get&tableName=${tableName}&page=1&countRecords=3">
                                            View
                                        </button>

                                    </td>
                                </c:if>

                            </tr>
                        </form>

                    </c:forEach>

                </table>
                <c:url var="searchUri" value="/bean/view.ass?tableName=${tableName}&page=##&countRecords=${countRecords}"/>
                <paginator:display
                        maxLinks="5"
                        currPage="${page}"
                        totalPages="${totalPagesCount}"
                        uri="${searchUri}"
                />
            </div>

        </c:if>

    </div>

</body>
</html>
