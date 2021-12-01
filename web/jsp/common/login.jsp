<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/loginStyle.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
    <title>Login</title>
</head>
<body>
<%@ include file="/jsp/jspf/header.jspf"%>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Taxi Service<br><fmt:message key='menu.loginPage'/></h2>
        <p><fmt:message key='menu.loginPageText'/></p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <form action=<%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="logIn"/>
        <div class="login-form">
            <div class="form-group">
                <label for="login"><fmt:message key='menu.log'/></label> <br>
                <input type="text" id="login" name="login" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key='menu.pass'/></label> <br>
                <input type="password" id="password" name="password" required>
            </div>
            <br>
            <div class="form-group">
                <input type="submit" class="btn btn-black" value="<fmt:message key='menu.login'/>">
                <a class="btn btn-secondary" href="<c:url value="/jsp/client/signUp.jsp"/>">
                    <fmt:message key="menu.signup"/>
                </a>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="footer main-footer">
    <%@ include file="/jsp/jspf/footer.jspf"%>
</div>

</body>
</html>
