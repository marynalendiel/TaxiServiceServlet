<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<%@page import="java.lang.String"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title><fmt:message key='admin.autoPark'/></title>
</head>
<body>
<div class="header">
    <%@ include file="/jsp/jspf/head.jspf" %>
</div>

<div id="demo">
    <h1><fmt:message key='admin.autoPark'/></h1>

    <div class="container">

        <!-- Responsive table starts here -->
        <div class="table-responsive-vertical shadow-z-1">

            <!-- Table starts here -->
            <table id="table" class="table table-hover table-mc-light-blue">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key='car.brand'/></th>
                    <th scope="col"><fmt:message key='car.model'/></th>
                    <th scope="col"><fmt:message key='car.numberOfSeats'/></th>
                    <th scope="col"><fmt:message key='car.category'/></th>
                    <th scope="col"><fmt:message key='car.description'/></th>
                    <th scope="col"><fmt:message key='car.status'/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="car" items="${list}">
                    <tr scope="row">
                        <td>${car.getBrand()}</td>
                        <td>${car.getModel()}</td>
                        <td>${car.getNumberOfSeats()}</td>
                        <td><fmt:message key='${car.getCategory()}'/></td>
                        <td>${car.getDescription()}</td>
                        <td>
                            <form action="<%= request.getContextPath() %>/controller" method="post">
                                <input type="hidden" name="command" value="changeCarStatus"/>
                                <select class="btn btn-light action-button" role="button" id="status" name="status">
                                    <option disabled selected><fmt:message key='${car.getStatus().replaceAll(" ","")}'/></option>
                                    <option value="to order"><fmt:message key='toorder'/></option>
                                    <option value="on the road"><fmt:message key='ontheroad'/></option>
                                    <option value="inactive"><fmt:message key='inactive'/></option>
                                </select>
                                <button class="btn btn-black" role="button" type="submit" name="carId"
                                        value="${car.getId()}"><fmt:message key='admin.changeStatus'/></button>
                            </form>
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
                            <li><a href="controller?command=getCars&page=${i}">${i}</a></li>
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
