<%-- 
    Document   : eventVenueBook
    Created on : Oct 9, 2013, 9:32:28 PM
    Author     : Diana Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Booking For Your Event!</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h4>Venue book for <strong> ${data.venueName} </strong></h4>
        <form data-abide action="eventVenueCheck" method="POST">
            <fieldset>
                <legend>Book Venue</legend>
                <div class="row">
                    
                </div>
            </fieldset>
        </form>
    </body>
</html>
