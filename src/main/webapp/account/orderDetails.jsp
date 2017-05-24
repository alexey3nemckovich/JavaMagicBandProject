<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="orderServices" scope="request"
             type="java.util.List<main.com.bsuir.autoservice.bean.impl.Service>"/>

<div class="order-services">
    <div class="row">
        <p class="form-control-static text-center">Services</p>
    </div>
    <c:forEach items="${orderServices}" var="service">

        <div class="row">
            <div class="col-md-8">
                <label for="service-name">Service name: </label>
                <p class="form-control-static" id="service-name">${service.name}</p>
            </div>

            <div class="col-md-offset-1 col-md-2">
                <label for="service-cost">Service cost: </label>
                <p class="form-control-static" id="service-cost">${service.cost}</p>
            </div>
        </div>

    </c:forEach>

</div>