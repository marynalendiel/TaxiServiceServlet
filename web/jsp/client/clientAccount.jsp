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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/table.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title><fmt:message key='user.info'/></title>
</head>
<body>

<div class="header">
    <%@ include file="/jsp/jspf/head.jspf" %>
</div>

<div id="demo">
    <h1><fmt:message key='user.info'/></h1>

    <div class="container">

<%--        <form action=<%= request.getContextPath() %>/controller method="get">--%>
<%--            <button class="btn btn-secondary" role="button" name="command" href="<c:url value="/controller?command=getOrders&refresh=refresh"/>">--%>
<%--                <fmt:message key='admin.refresh'/></button>--%>
<%--        </form>--%>
        <a class="btn btn-secondary" href="<c:url value="/controller?command=getOrders&refresh=refresh"/>">
            <fmt:message key='admin.refresh'/>
        </a>
<%--        <a href="<c:url value="/controller?command=getOrdersList&refresh=refresh"/>">--%>
<%--            <fmt:message key='admin.refresh'/>--%>
<%--        </a>--%>

    <!-- Responsive table starts here -->
    <!-- For correct display on small screens you must add 'data-title' to each 'td' in your table -->
    <div class="table-responsive-vertical shadow-z-1">

        <!-- Table starts here -->
        <table id="table" class="table table-hover table-mc-light-blue">
            <thead>
            <tr>
                <th scope="col"><fmt:message key='menu.startPoint'/></th>
                <th scope="col"><fmt:message key='menu.finishPoint'/></th>
                <th scope="col"><a href="controller?command=getOrders&sort=date" class="more"><fmt:message
                        key='menu.date'/></a></th>
                <th scope="col"><a href="controller?command=getOrders&sort=price" class="more"><fmt:message
                        key='menu.price'/></a></th>
                <th scope="col"><fmt:message key='menu.numberOfPassengers'/></th>
                <th scope="col"><fmt:message key='menu.cars'/></th>
            </tr>
            </thead>
            <tbody>

                <c:forEach var="order" items="${list}">
                    <tr scope="row">

                    <td>${order.getStartPoint()}</td>
                    <td>${order.getFinishPoint()}</td>
                    <td>${order.getCreateTime()}</td>
                    <td>${order.getPrice()}</td>
                    <td>${order.getNumberOfPassengers()}</td>
                    <td>
                    <a href="controller?command=getCarInformation&orderId=${order.getId()}" class="more">
                    <fmt:message key='user.details'/>
                    </a>
                    </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
        <div class="container_pagination">
            <ul class="pagination">
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${page eq i}">
                            <li><a>${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="controller?command=getOrders&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/style/tableJS.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>
