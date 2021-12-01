<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>

    <title><fmt:message key='menu.newOrderPage'/></title>
</head>
<body>
<%@ include file="/jsp/jspf/header.jspf" %>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Taxi Service</h2>
        <p><fmt:message key='menu.newOrderPage'/></p>
    </div>
</div>

<div class="main">
    <div class="welcome-block">
            <div class="input_wrap">
                <p><fmt:message key='menu.startPoint'/>: ${order.getStartPoint()}</p>
                <p><fmt:message key='menu.finishPoint'/>: ${order.getFinishPoint()}</p>
                <p><fmt:message key='menu.price'/>: ${order.getPrice()}</p>
            </div>

            <div class="form-group">
                <a class="btn btn-secondary" type="button" href="<c:url value="/index.jsp"/>" style="color: #f0f0f0">
                    <fmt:message key='user.cancel'/>
                </a>
                <form action="<%= request.getContextPath() %>/controller" method="post">

                    <input type="hidden" name="command" value="createOrder"/>
                    <button class="btn btn-black" type="submit"><fmt:message key='user.submit'/></button>
                </form>
            </div>
    </div>
</div>

</body>
</html>
