<%-- 
    Document   : entertainmentPayConfirm
    Created on : 14-Nov-2013, 11:47:09
    Author     : Ser3na
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="base.jsp"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ page import="java.io.*,java.util.*" %>
        <%@page import="java.text.SimpleDateFormat"%>


    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h4>Your ticket reservation has been confirmed.</h4> 
            <div class="row">
                <div class="small-11 small-centered columns">
                    <div class="panel">


                        <h6><strong class="right-align,inline"> Show Name:</strong>  ${thisShow.showName} </h6>
                    <br>
                    <h6><strong>Show Time : </strong><fmt:formatDate type="date" pattern="MMM dd yyyy hh:mm a" 
                                    value="${thisSchedule.showStartDateTime}" /></h6>
                    <br>   
                    <h6><strong>Here are your tickets:</strong></h6>
                    <br>

                    <c:forEach items="${totalTickets}" var="data">
                    <h6><strong class="right-align,inline"> Ticket ID:</strong>  ${data.showTicketSaleId} </h6>
                    <br>
                    <h6><strong>Number of tickets : </strong>${data.showTicketQuantity}</h6>
                    <br>
                    <h6><strong>Ticket Price : </strong>${data.showTicketPrice}</h6>
                    <br>
                    </c:forEach>
                    <h6>${ticTotal}</h6>
                </div>

            </div>
            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
