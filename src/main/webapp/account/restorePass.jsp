<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage titleName="Restore pass">
    <jsp:attribute name="headExtra">
    <link rel="stylesheet" href="<c:url value="/css/text.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/reference.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/site_style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/action-button.css"/>">
    </jsp:attribute>

    <jsp:body>
        <div class="site-header">
            AutoServiceShop Restore
        </div>

        <div id="user-container">
            <form id="restore_pass" class="form-horizontal">

                <p id="info"></p>

                <div class="form-group">
                    <label for="form-new-pass">New password: </label>
                    <input type="password" class="form-control" id="form-new-pass" placeholder="Enter new password" name="newPassword"/>
                </div>

                <div class="form-group">
                    <label for="form-new-retry-pass">Old password: </label>
                    <input type="password" class="form-control" id="form-new-retry-pass" placeholder="Retry new password" name="retryPassword"/>
                </div>

                <div class="row">
                    <div class="col-md-offset-4 col-md-4">
                        <button type="submit" class="btn btn-success btn-block" id="form-save">Save</button>
                    </div>
                </div>
            </form>
        </div>

        <script>
            $(document).ready(function () {
                var $restorePassForm = $("#restore_pass");

                $restorePassForm.submit(function (event) {
                    disableSendForm(event);
                    if ($("#form-new-pass").val() !== $("#form-new-retry-pass").val()){
                        $("#info").html("New password != Retry password");
                    }else{
                        $.post("/account/restorePassData.ass", $restorePassForm.serialize(), function (data) {
                            if (data.isRestoredPass[0] === true) {
                                $("#info").html("Ok. Backed");
                                backPage();
                            } else {
                                $("#info").html("Error update password");
                            }
                        }, "json");
                    }
                });
            });
        </script>

    </jsp:body>
</t:genericpage>
