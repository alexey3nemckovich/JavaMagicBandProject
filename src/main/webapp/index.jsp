<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:genericpage titleName="AutoServiceShop">
    <jsp:attribute name="headExtra">
    <link rel="stylesheet" href="<c:url value="/css/text.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/reference.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/site_style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/action-button.css"/>">
    </jsp:attribute>

    <jsp:body>
        <div class="site-header">
            AutoServiceShop
        </div>

        <div style="text-align: center; margin: 10px;">
            <a class="action-button" style="text-decoration: none;" href="/bean.ass">
                To list of tables
            </a>

            <a class="action-button" style="text-decoration: none;" href="/document.ass?action=generateUserListDoc&format=pdf">
                Generate user list pdf
            </a>

            <a class="action-button" style="text-decoration: none;" href="/document.ass?action=generateUserListDoc&format=xml">
                Generate user list xml
            </a>

            <a class="action-button" style="text-decoration: none;" href="/document.ass?action=generateUserListDoc&format=xls">
                Generate user list xls
            </a>

        </div>
    </jsp:body>
</t:genericpage>
