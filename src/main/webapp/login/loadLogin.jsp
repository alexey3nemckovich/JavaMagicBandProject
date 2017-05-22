<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${isAuthorized}">
        <ul class="nav navbar-nav navbar-right">
            <li>
                <button style="padding: 12px" class="btn btn-block align-middle" type="button"> User : <c:out value="${userName}"/></button>
            </li>
            <li>
                <button type="button" class="btn" id = "nav-logout">
                    <span class="glyphicon glyphicon-log-out"></span> Logout
                </button>

                <script>
                    $(document).ready(function () {
                        $("#nav-logout").click(function () {
                            $.get("/login/logout.ass", {},
                                function (data) {
                                    if (data.isLogout) {
                                        location.reload();
                                    } else {
                                        alert("Problem with logout");
                                    }
                                }, "json");
                        });
                    });
                </script>
            </li>
        </ul>
    </c:when>
    <c:otherwise>
        <form class="navbar-form navbar-right" id="nav-login-form">
            <div class="form-group">
                <input type="text" class="form-control" name="login" placeholder="Login"/>
                <input type="text" class="form-control" name="password" placeholder="Password"/>
            </div>
            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-log-in"></span> Login
            </button>
        </form>

        <script>
            $(document).ready(function () {
                $("#nav-login-form").submit(function () {
                    $.get("/login/checkLogin.ass",
                        $("#nav-login-form").serialize(),
                        function (data) {
                            if (data.isAuthorized) {
                                loadLogin();
                            } else {
                                alert('Not login');
                            }
                        }, "json");
                });
            });
            action = "/login/checkLogin.ass"
        </script>
    </c:otherwise>
</c:choose>
