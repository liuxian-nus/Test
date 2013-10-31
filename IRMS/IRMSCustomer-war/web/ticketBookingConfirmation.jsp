<%-- 
    Document   : ticketBookingConfirmation
    Created on : 30-Oct-2013, 17:19:16
    Author     : Jieqiong
--%>

<%@page import="ATMS.entity.AttractionEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    AttractionEntity attr=(AttractionEntity)session.getAttribute("attr");
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>Attraction at Coral Resort World</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Ticket Booking Confirmation</h1>
        <h6>Your ticket has been successfully booked. </h6>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
