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
  <title>Taxi Service - Home</title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

<%@ include file="/jsp/jspf/header.jspf"%>
<div class="sidenav">
  <div class="login-main-text">
    <h2>Taxi Service</h2>
    <p><fmt:message key='menu.main'/></p>
  </div>
  <img class="img" src="${pageContext.request.contextPath}/style/image/yellow_cab.png" alt="Yellow car" size="60%">
</div>
<div class="main">
  <div class="welcome-block">
    <c:choose>
      <c:when test="${user.getRole() == true}">
        <h1><fmt:message key='admin.carsInfo'/></h1>
        <form action= <%= request.getContextPath() %>/controller method="get">
          <button  class="btn welcome-block btn-black" name="command" value="getCars">
            <fmt:message key='admin.autoPark'/>
          </button>
        </form>
      </c:when>
      <c:when test="${user.getRole() == false}">
        <h1><fmt:message key='menu.welcome'/></h1>
        <form action = <%= request.getContextPath() %>/jsp/client/newOrder.jsp method="get">
          <button class="btn btn-black"><fmt:message key='user.makeOrder'/></button>
        </form>
      </c:when>
      <c:otherwise>
        <h1><fmt:message key='menu.welcome'/></h1>
          <a class="btn btn-black" href="jsp/common/login.jsp" class="login"><fmt:message key='menu.login'/></a>
      </c:otherwise>
    </c:choose>
  </div>
</div>
<div class="footer main-footer">
  <%@ include file="/jsp/jspf/footer.jspf"%>
</div>
</body>
</html>
