<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bean</title>

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
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Table</h1>

<c:if test="${!empty beans}">
    <table class="tg">
        <tr>
            <c:forEach items="${beans.get(0).getFieldsOrdered()}" var="field">
                <th>${field.getName()}</th>
            </c:forEach>
        </tr>
        <c:forEach items="${beans}" var="bean">
            <tr>
                <c:forEach items="${bean.getFieldsOrdered()}" var="field">
                    <td>${field.get(bean)}</td>
                </c:forEach>
                <td><a href="<c:url value='/edit/${bean.getId()}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${bean.getId()}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
