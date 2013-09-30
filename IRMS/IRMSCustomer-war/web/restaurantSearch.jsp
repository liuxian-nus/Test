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
            <h4>Restaurant Search Result</h4>

            <table>
                
                <thead>
                <th width="200">Restaurant</td>
            <th width="200">Resort World Area</td>
            <th width="200">Type of Restaurant</td>
            <th width="200">Cuisine</td>
            <th width="200">Make Reservation</th>
                </thead>
                <tbody>
        <c:forEach items="${data}" var="data">
            <tr>
                <td width="200">&nbsp${data.restName}</td>
                <td width="200">&nbsp${data.restNeighbourhood}</td>
                <td width="200">&nbsp${data.restTypeOfPlace}</td>
                <td width="200">&nbsp${data.restCuisine}</td>
                <td width="200">
                    <br>
                    <form action="restaurantBook"><input class="small button" type="submit" value ="Make Reservation"/>
                        <input type="hidden" name="restId" value="${data.restId}"/>
                    </form> 
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
