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
                <p> ${data.venueName}</p>
   
               
               
            </div>
        </c:forEach>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
