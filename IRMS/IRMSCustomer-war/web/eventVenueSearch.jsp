<%-- 
    Document   : eventSearchVenue
    Created on : Oct 8, 2013, 11:43:38 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Research Result</title>
        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
     

        <table>
                
                <thead>
                <th width="200">Venue </td>
            <th width="200">Capacity</td>
                <th width="200">Make Reservation</th>
            
                </thead>
                <tbody>
        <c:forEach items="${data}" var="data">
            <tr>
                <td width="200">&nbsp${data.venueName}</td>
                <td width="200">&nbsp${data.venueCapacity}</td>
                
                <td width="200">
                    <br>
                    <form action="eventVenueBook"><input class="small button" type="submit" value ="Make Reservation"/>
                        <input type="hidden" name="venueId" value="${data.venueId}"/>
                    </form> 
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
