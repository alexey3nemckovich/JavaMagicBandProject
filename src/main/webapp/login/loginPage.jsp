<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage titleName="Login">
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

        <div>
            <form id="login-form">
                <div class="form-group">
                    <label for="login">Email address</label>
                    <input type="email" class="form-control login" id="login" name="login" placeholder="Enter email">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control login" id="password" name="password" placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <script>
            $(document).ready(function () {

                var $login_form = $("#login-form");

                $('.login').keypress(function (e) {
                    if (e.which == 13) {
                        $login_form.submit();
                        return false;
                    }
                });


                $login_form.submit(function (event) {
                    event.preventDefault();

                    $.post("/login/checkLogin.ass",
                        $login_form.serialize(),
                        function (data) {
                            if (data.isAuthorized[0]) {
                                loadRoot();
                            } else {
                                alert('Not login');
                            }
                        }, "json");
                });
            });
        </script>
    </jsp:body>
</t:genericpage>
