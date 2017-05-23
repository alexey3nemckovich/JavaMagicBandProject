<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="userOrders" scope="request"
             type="java.util.List<main.com.bsuir.autoservice.bean.impl.Order>"/>


<c:forEach items="${userOrders}" var="userOrder">
    <div class="row">

        <div class="col-md-2">
            <label for="order-id">Order id: </label>
            <p class="form-control-static" id="order-id">${userOrder.id}</p>
        </div>

        <div class="col-md-offset-1 col-md-3">
            <label for="date-open">Date open: </label>
            <p class="form-control-static" id="date-open">${userOrder.dateOpen}</p>
        </div>

        <div class="col-md-3">
            <label for="date-close">Date close: </label>
            <p class="form-control-static" id="date-close">${userOrder.dateClose}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <label for="order-sum">Sum: </label>
            <p class="form-control-static" id="order-sum">${userOrder.sum}</p>
        </div>


        <div class="col-md-offset-1 col-md-3">
            <label for="order-state">State: </label>
            <p class="form-control-static" id="order-state">${userOrder.state}</p>
        </div>


        <div class="col-md-offset-1 col-md-3">
            <button type="submit" class="btn btn-primary btn-block order-more" data-id="${userOrder.id}"> More</button>
        </div>
    </div>
</c:forEach>

<script>
    $(document).ready(function () {
        $(".order-more").click(function () {
            alert($(this).data("id"));
            return false;
        });
    });
</script>