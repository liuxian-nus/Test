<%-- 
    Document   : eventSearchVenue
    Created on : Oct 8, 2013, 11:43:38 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h4>Venue Search Result</h4>

        <c:forEach items="${data}" var="data">
            <div class="row">
                ${data.venueName}
   
                    <br>
                    <form action="restaurantBook"><input class="small button" type="submit" value ="Make Reservation"/>
                        <input type="hidden" name="restId" value="${data.restId}"/>
                    </form> 
               
            </div>
        </c:forEach>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
