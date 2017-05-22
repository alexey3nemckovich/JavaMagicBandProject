<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${isAuthorized}">
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div>
                    <c:out value="${userName}"/>
                </div>
            </li>
            <li>
                <a href="#">
                    <span class="glyphicon glyphicon-log-out"></span> Logout
                </a>
            </li>
        </ul>
    </c:when>
    <c:otherwise>
        <form class="navbar-form navbar-right" action="/login/checkLogin.ass">
            <div class="form-group">
                <input type="text" class="form-control" name="login" placeholder="Login"/>
                <input type="text" class="form-control" name="password" placeholder="Password"/>
            </div>
            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-log-in"></span> Login
            </button>
        </form>
    </c:otherwise>
</c:choose>
