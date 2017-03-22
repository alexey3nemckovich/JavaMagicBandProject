<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main bean</title>
</head>
<body>
${tables}
    <form method="get" action="/bean/table.ass">
        <label>Tables:</label>
        <br/>
        <select id="table_id" style="width:200px;">
            <c:forEach items="${tables}" var="table">
                <c:out value="${table}" />
                <option value="${table}"> ${table}</option>
            </c:forEach>
        </select>
        <br/>
        <button type="submit"> Go </button>
    </form>
</body>
</html>
