<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/style/loginStyle.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
    <title>Sign Up</title>
</head>
<body>
<%@ include file="/jsp/jspf/header.jspf"%>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Taxi Service<br><fmt:message key='menu.signUpPage'/></h2>
        <p><fmt:message key='menu.loginPageText'/></p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <form action=<%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="signUp"/>
            <div class="login-form">
                <div class="input_wrap">
                    <label for="login"><fmt:message key='menu.firstname'/></label> <br>
                    <input type="text" id="first_name" name="first_name" required>
                </div>
                <div class="input_wrap">
                    <label for="login"><fmt:message key='menu.lastname'/></label> <br>
                    <input type="text" id="last_name" name="last_name" required>
                </div>
                <div class="input_wrap">
                    <label for="login"><fmt:message key='menu.log'/></label> <br>
                    <input type="text" id="login" name="login" required>
                </div>
                <div class="input_wrap">
                    <label for="phone_number"><fmt:message key='menu.phone'/></label> <br>
                    <input type="tel" id="phone_number" name="phone_number"
                           pattern="0[0-9]{9}" required>
                </div>
                <div class="input_wrap">
                    <label for="email"><fmt:message key='menu.email'/></label> <br>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input_wrap">
                    <label for="password"><fmt:message key='menu.pass'/></label> <br>
                    <input type="password" id="password" name="password" required>
                </div>
                <br>
                <div class="input_grp">
                    <input type="submit" value="<fmt:message key='menu.signup'/>" class="btn btn-black" >
                    <a class="btn btn-secondary" href="<c:url value="/jsp/common/login.jsp"/>">
                        <fmt:message key="menu.login"/>
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
