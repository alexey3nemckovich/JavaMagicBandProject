<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="fullAvailableServices" scope="request"
             type="java.util.List<main.com.bsuir.autoservice.bean.impl.Service>"/>
<jsp:useBean id="fullWorkingShop" scope="request"
             type="java.util.List<main.com.bsuir.autoservice.bean.impl.ServiceShop>"/>


<t:genericpage titleName="Add order">
    <jsp:attribute name="headExtra">
    <link rel="stylesheet" href="<c:url value="/css/text.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/reference.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/site_style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/action-button.css"/>">
    </jsp:attribute>

    <jsp:body>
        <div class="site-header">
            AutoServiceShop Add order
        </div>

        <div id="user-container">
            <form id="add_order">

                <p id="info"></p>

                <div class="form-group">
                    <label for="form-services">Available services: </label>
                    <select class="selectpicker" id="form-services" multiple data-selected-text-format="count" name="orderService" data-width="75%" title="Available Service">
                        <c:forEach items="${fullAvailableServices}" var="availableService">
                            <option value="${availableService.id}">${availableService.name}- ${availableService.cost}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="form-service-shop">Work service shop: </label>
                    <select class="selectpicker" id="form-service-shop" name="orderServiceShop" data-width="75%" title="Working service shop">
                        <c:forEach items="${fullWorkingShop}" var="workingShop">
                            <option value="${workingShop.id}">${workingShop.city}, ${workingShop.street}, ${workingShop.house}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Add Order</button>
            </form>
        </div>

        <script>
            $(document).ready(function () {
                const $formAddOrder = $("#add_order");

                $formAddOrder.submit(function (event) {
                    disableSendForm(event);

                    $.post("/account/orderAddData.ass", $formAddOrder.serialize(), function (data) {
                        if (data.isAddedOrder[0]){
                            $("#info").html("Ok, added. Backed");
                            backPage();
                        }else {
                            $("#info").html("Error add order");
                        };
                    }, "json");
                });
            });
        </script>
    </jsp:body>
</t:genericpage>
