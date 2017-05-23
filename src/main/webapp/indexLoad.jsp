<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="availableServices" scope="request" type="java.util.List<main.com.bsuir.autoservice.dto.ServiceAvailableDTO>"/>

<div class="main-service">
    <div class="row" id="main-service-head">
        <div class="col-md-8">
            Service name
        </div>
        <div class="col-md4">
            Service cost
        </div>
    </div>

    <c:forEach items="${availableServices}" var="availableService">
        <div class="row" id="main-service-block">
            <div class="col-md-8">
                    ${availableService.nameService}
            </div>
            <div class="col-md-4">
                    ${availableService.costService}
            </div>
        </div>
    </c:forEach>
</div>

<div class="main-shares">

</div>