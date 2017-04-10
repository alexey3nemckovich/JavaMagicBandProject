<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/Paginator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bean</title>
    <link rel="stylesheet" href="/css/classes.css">
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }

        .paginatorList { margin: 2px 6px; list-style: none outside none; }
        .paginatorList li { float: left; padding: 2px 4px; font-size: 1.2em; }
        li.paginatorCurr { font-weight: bold; font-size: 1.5em; margin-top: -2px; }
        li.paginatorLast { float: none; }
    </style>
</head>
<body>

    <div style = "border: 2px solid black;">
        <h1 align = "center">AutoServiceShop - magic project of Nikita, Vova, Alex</h1>
    </div>

    <a href="../../index.jsp">To main page</a>

    <%String url = (String) request.getAttribute("javax.servlet.include.query_string");%>

    <div class="centered_parent">
        <c:if test="${!empty beans}">
            <div class="centered">

                <h1>${tableName}</h1>

                <c:if test='<%=request.getAttribute("action").equals("get")%>'>
                    <h2>${result}</h2>
                </c:if>

                <table class="tg">
                    <tr>
                        <c:forEach items="${beans.get(0).getFieldsOrdered()}" var="field">
                            <th>${field.getName()}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${beans}" var="bean">
                        <form>
                            <tr>

                                <c:forEach items="${bean.getFieldsOrdered()}" var="field">
                                    <td>
                                        <input type="text" name="${field.getName()}" value="${field.get(bean)}"/>
                                    </td>
                                </c:forEach>

                                <td>
                                    <button formmethod="post" type="submit" formaction="/bean/edit.ass?tableName=${tableName}">
                                        Edit
                                    </button>
                                </td>
                                <td>
                                    <button formmethod="post" type="submit" formaction="/bean/view.ass?tableName=${tableName}&page=${page}&countRecords=${countRecords}&action=delete">
                                        Delete
                                    </button>
                                </td>

                            </tr>
                        </form>
                    </c:forEach>
                </table>
                <c:url var="searchUri" value="/bean/view.ass?tableName=${name}&page=##&countRecords=${countRecords}" />
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
