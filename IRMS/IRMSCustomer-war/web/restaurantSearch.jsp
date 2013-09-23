<%-- 
    Document   : restaurantSearch
    Created on : Sep 17, 2013, 2:13:03 AM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Restaurant Search Result!</h1>
            <table>
                <tbody>
        <c:forEach items="${data}" var="data">
            <tr>
                <td>${data.restName}</td>
                <td>${data.restNeighbourhood}</td>
                <td>${data.restTypeOfPlace}</td>
                <td>${data.restCuisine}</td>
                 
            </tr>
        </c:forEach>
            </tbody>
            </table>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
