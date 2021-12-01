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
    <title><fmt:message key='menu.newOrderPage'/></title>
</head>

<body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

    <%@ include file="/jsp/jspf/header.jspf"%>
    <div class="sidenav">
        <div class="login-main-text">
            <h2>Taxi Service</h2>
            <p><fmt:message key='menu.newOrderPage'/></p>
        </div>
    </div>
    <div class="main">
        <div class="welcome-block">

            <form action="<%= request.getContextPath() %>/controller" method="get">
                <input type="hidden" name="command" value="checkOrder"/>
                <div class="input_wrap">
                    <label for="startPoint"><fmt:message key='menu.finishPoint'/></label> <br>
                    <input type="tel" id="startPoint" name="startPoint"
<%--                           pattern="([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+). ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+){4,}, ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+) ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+){4,}, [0-9]+($|[a-zA-Zа-яА-ЯёЁіІїЇґҐ]+|[0-9]+)"--%>
<%--                           title="м. Київ, вул. Хрещатик, 1/1" required>--%>
                </div>

                <div class="input_wrap">
                    <label for="startPoint"><fmt:message key='menu.startPoint'/></label> <br>
                    <input type="tel" id="finishPoint" name="finishPoint"
<%--                           pattern="([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+). ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+){4,}, ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+) ([a-zA-Zа-яА-ЯёЁіІїЇґҐ]+){4,}, [0-9]+($|[a-zA-Zа-яА-ЯёЁіІїЇґҐ]+|[0-9]+)"--%>
<%--                           title="м. Київ, вул. Хрещатик, 1/1" required>--%>
                </div>
                <br><br>
                <div class="input_wrap">
                    <select class="selectBox" id="category" name="category">
                        <option disabled selected><fmt:message key='user.carCategory'/></option>
                        <option value="standard"><fmt:message key='standard'/></option>
                        <option value="comfort"><fmt:message key='comfort'/></option>
                        <option value="minivan"><fmt:message key='minivan'/></option>
                    </select>
                </div>
                <br>
                <div class="input_wrap">
                    <label for="startPoint"><fmt:message key='menu.numberOfPassengers'/></label> <br>
                    <input type="number" id="numberOfSeats" name="numberOfSeats"
                           pattern="[20]|[0-9]{1,2}"
                           title="1-20" required>
                </div>
                <br>
                <div class="input_grp">
                    <button class="btn btn-secondary" type="button">
                        <a href="<c:url value="/index.jsp"/>" style="color: #f0f0f0">
                            <fmt:message key='user.cancel'/>
                        </a>
                    </button>
                    <button class="btn btn-black" type="submit"><fmt:message key='user.submit'/></button>
                </div>
            </form>

         </div>
    </div>
    <div class="footer main-footer">
        <%@ include file="/jsp/jspf/footer.jspf"%>
    </div>
</body>
</html>
