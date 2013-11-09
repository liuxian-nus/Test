<%-- 
    Document   : hotelConfirm
    Created on : Nov 8, 2013, 11:23:18 PM
    Author     : lionetdd
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

        <title>Your order has been updated!</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h4>Your reservation has been confirmed!</h4> 
            <div class="row">
                <div class="small-11 small-centered columns">
                    <div class="panel">

                        <h6><strong>Here is your reservation details:</strong></h6>
                        <br>
                        
                        <h6><strong>Reservation number : </strong>${data.reservationId}</h6>
                      <c:if test="${data.reservationHotelNo == 1}">
                        <h6>Reservation for <strong class="right-align,inline"> Orchard Hotel</strong></h6>
                    </c:if>
                    <c:if test="${data.reservationHotelNo == 2}">
                        <h6>Hotel Reservation for <strong class="right-align,inline"> Marina Hotel</strong></h6>
                    </c:if>
                    <c:if test="${data.reservationHotelNo == 3}">
                        <h6>Hotel Reservation for <strong class="right-align,inline"> BeachView Hotel</strong></h6>
                    </c:if>
                    <h6>Room Type: <strong class="right-align,inline"> ${data.reservationRoomType} </strong></h6>
                    <br>
                    <h6><strong>Check-in Date : </strong>${data.rcCheckInDate}</h6>
                    <br>
                    <h6><strong>Check-out Date : </strong>${data.rcCheckOutDate}</h6>
                    <br>
                    <h6><strong>Number of people : </strong>${data.reservationGuestCount}</h6>
                    <br>
                    <h6><strong>Number of rooms : </strong>${data.reservationRoomCount}</h6>
                    <br>
                    <h6><strong>Guest name : </strong>${data.rcName}</h6>
                    <br>
                    <h6><strong>Reservation e-mail : </strong>${data.rcEmail}</h6>
                    <br>
                    <h6><strong>Reservation mobile : </strong>${data.rcHP}</h6>
                    <br>
                  
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>