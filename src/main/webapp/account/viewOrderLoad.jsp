<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage titleName="Orders">
    <jsp:attribute name="headExtra">
    <link rel="stylesheet" href="<c:url value="/css/text.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/reference.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/site_style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/action-button.css"/>">
    </jsp:attribute>

    <jsp:body>
        <div class="site-header">
            AutoServiceShop Orders
        </div>

        <div id="user-container">
            <div id="orders">
            </div>

            <div class="pagination-holder">
            </div>

        </div>

        <script>
            $(document).ready(function () {

                const $itemsOnPage = 5;

                function loadOrderPage($page) {
                    $("#orders").load("/account/orderView.ass", {currentPage: $page, itemsOnPage: $itemsOnPage});
                }


                $.get("/account/orderNumber.ass", function (data) {
                    const $currentPage = 1;

                    $(".pagination-holder").pagination({
                        items: data.numberOrder[0],
                        itemsOnPage: $itemsOnPage,
                        currentPage: $currentPage,
                        cssStyle: 'light-theme',
                        onPageClick(pageNumber, event){
                            loadOrderPage(pageNumber);
                        }
                    });

                    loadOrderPage($currentPage);
                });
            });
        </script>
    </jsp:body>
</t:genericpage>
