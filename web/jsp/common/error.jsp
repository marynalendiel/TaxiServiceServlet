<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/style/errorStyle.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Oops!</title>
</head>
<body>
<div class="sidenav">
    <div class="error-message">
        <c:choose>
            <c:when test="${errorMessage == null}">
                <h2><fmt:message key="error.oops"/></h2>
                <p><fmt:message key="error.main"/></p>
            </c:when>
            <c:otherwise>
                <h2><fmt:message key='${errorMessage}'/></h2>
            </c:otherwise>
        </c:choose>
        <a class="btn btn-light" role="button" href="${pageContext.request.contextPath}/index.jsp">
            <fmt:message key="menu.back"/>
        </a>
    </div>
</div>
<div class="main">
    <img src="${pageContext.request.contextPath}/style/image/oops_cat.png" alt="Error cat">
</div>
<div class="footer">
    <%@ include file="/jsp/jspf/footer.jspf"%>
</div>
</body>
</html>