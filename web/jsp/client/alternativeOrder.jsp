<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>

    <title><fmt:message key='user.altProcessOrder'/></title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

<%@ include file="/jsp/jspf/header.jspf" %>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Taxi Service</h2>
        <p><fmt:message key='menu.newOrderPage'/></p>
    </div>
</div>
<div class="main">
    <div class="welcome-block">

        <form action="<%= request.getContextPath() %>/controller" method="get">
            <input type="hidden" name="command" value="alternativeOrder"/>
            <div class="input_wrap">
                <button class="btn btn-black btn-width" name="orderOption" value="anotherCategory"><fmt:message
                        key='user.carAnotherCategory'/></button>
            </div>
            <br>
            <div class="input_wrap">
                <button class="btn btn-black btn-width" name="orderOption" value="sameCategory"><fmt:message
                        key='user.severalCars'/></button>
            </div>

            <br>
            <div class="input_grp">
                <button class="btn btn-secondary btn-width" type="button">
                    <a href="<c:url value="/index.jsp"/>" style="color: #f0f0f0">
                        <fmt:message key='user.cancel'/>
                    </a>
                </button>
            </div>
        </form>

    </div>
</div>

<%@ include file="/jsp/jspf/footer.jspf"%>
</body>
</html>
