<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="isAuthorized" scope="request" type="java.lang.Boolean"/>

<c:choose>
    <c:when test="${isAuthorized}">
        <jsp:useBean id="userName" scope="request" type="java.lang.String"/>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <button style="padding: 12px" class="btn btn-block align-middle" type="button" id="nav-user-enter"> User : ${userName}</button>
            </li>
            <li>
                <button type="button" class="btn" id = "nav-logout">
                    <span class="glyphicon glyphicon-log-out"></span> Logout
                </button>

                <script>
                    $(document).ready(function () {
                        $("#nav-logout").click(function () {
                            $.post("/login/logout.ass", {},
                                function (data) {
                                    if (data.isLogout[0]) {
                                        location.reload();
                                    } else {
                                        alert("Problem with logout");
                                    }
                                }, "json");
                        });

                        $("#nav-user-enter").click(function () {
                            const $accountUser = "/account/user.ass";

                            $('body').load($accountUser);
                            updateHistory($accountUser);
                        });
                    });
                </script>
            </li>
        </ul>
    </c:when>
    <c:otherwise>
        <form class="navbar-form navbar-right" id="nav-login-form">
            <div class="form-group">
                <input type="text" class="form-control nav-login" name="login" placeholder="Login"/>
                <input type="password" class="form-control nav-login" name="password" placeholder="Password"/>
            </div>
            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-log-in"></span> Login
            </button>
        </form>

        <script>
            $(document).ready(function () {

                var $nav_login_form = $("#nav-login-form");

                $('.nav-login').keypress(function (e) {
                    if (e.which == 13) {
                        $nav_login_form.submit();
                        return false;
                    }
                });


                $nav_login_form.submit(function (event) {
                    event.preventDefault();

                    $.post("/login/checkLogin.ass",
                        $nav_login_form.serialize(),
                        function (data) {
                            if (data.isAuthorized[0] === true) {
                                loadRoot();
                            } else {
                                const $loginPage = "/login/loginPage.ass";

                                $('body').load($loginPage);
                                updateHistory($loginPage);
                            }
                        }, "json");
                });
            });
        </script>
    </c:otherwise>
</c:choose>
