<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage titleName="Personal account">
    <jsp:attribute name="headExtra">
    <link rel="stylesheet" href="<c:url value="/css/text.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/reference.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/site_style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/action-button.css"/>">
    </jsp:attribute>

    <jsp:body>
        <div class="site-header">
            AutoServiceShop Profile
        </div>

        <div id="account-modes">

        </div>

        <div id="user-container">
            <div id="general_information">
                General information
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $("#user-container").load("/account/generalInformation.ass");
                $("#account-modes").load("/account/accountModes.ass");
            });
        </script>
    </jsp:body>
</t:genericpage>
