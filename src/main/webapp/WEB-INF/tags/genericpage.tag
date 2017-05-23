<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="titleName" required="true" %>
<%@attribute name="headExtra" fragment="true" %>
<html>
<head>
    <title>${titleName}</title>
    <jsp:include page="/generalelement/head.jsp"/>
    <jsp:invoke fragment="headExtra"/>
</head>
<body>
    <jsp:include page="/generalelement/header.jsp"/>
    <div class="container">
        <jsp:doBody/>
    </div>
    <jsp:include page="/generalelement/footer.jsp"/>
<div id="log"></div>
</body>
</html>