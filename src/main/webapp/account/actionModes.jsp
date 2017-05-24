<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<jsp:useBean id="userLevel" scope="request"
             type="main.com.bsuir.autoservice.config.permission.PermissionLevel"/>


<div class="row">
<c:choose>
    <c:when test="${userLevel eq 'MECHANIC' || userLevel eq 'CHIEF_MECHANIC'}">
        <div class="col-md-offset-1 col-md-3">
            <button type="button" class="btn btn-block" id="action-mechanic"> Mechanic </button>
        </div>

        <c:if test="${userLevel eq 'CHIEF_MECHANIC'}">
            <div class="col-md-offset-1 col-md-3">
                <button type="button" class="btn btn-block" id="action-chief-mechanic"> Mechanic </button>
            </div>

            <script>
                $(document).ready(function () {
                    $("#action-chief-mechanic").click(function () {
                        $("#user-container").load("/mechanic/chiefWorks.ass");
                    });
                });
            </script>
        </c:if>

        <script>
            $(document).ready(function () {
                $("#action-mechanic").click(function () {
                    $("#user-container").load("/mechanic/orderWorks.ass");
                });
            });
        </script>
    </c:when>

    <c:when test="${userLevel eq 'ADMINISTRATOR'}">
        <div class="col-md-offset-1 col-md-3">
            <button type="button" class="btn btn-block" id="action-administrator"> Administrator </button>
        </div>


        <script>
            $(document).ready(function () {
                $("#action-administrator").click(function () {
                    $("#user-container").load("/administrator/controlPage.ass");
                });
            });
        </script>
    </c:when>

    <c:when test="${userLevel eq 'DIRECTOR'}">
        <div class="col-md-offset-1 col-md-3">
            <button type="button" class="btn btn-block" id="action-director"> Director </button>
        </div>


        <script>
            $(document).ready(function () {
                $("#action-director").click(function () {
                    $("#user-container").load("/director/directorCrud.ass");
                });
            });
        </script>
    </c:when>

</c:choose>

    <c:if test="${userLevel ne 'USER'}">
        <div class="col-md-offset-1 col-md-3">
            <button type="button" class="btn btn-block" id="action-user"> User </button>
        </div>
    </c:if>

    <script>
        $(document).ready(function () {
            $("#action-user").click(function () {
                $("#user-container").load("/account/generalInformation.ass");
            });
        });
    </script>
</div>