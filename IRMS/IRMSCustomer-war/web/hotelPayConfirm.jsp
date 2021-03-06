<%-- 
    Document   : hotelConfirm
    Created on : Nov 8, 2013, 11:23:18 PM
    Author     : lionetdd
--%>

<%@page import="CRMS.entity.CouponEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    CouponEntity coupon=(CouponEntity)session.getAttribute("coupon");
    String couponInfo="";
    if(coupon!=null){
        couponInfo="Here is a coupon with coupon code "+coupon.getCouponId()+" for you. You may use this coupon to purchase";
    }
%>
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
         
            <div class="row">
                <div class="small-11 small-centered columns">
                    <div class="panel">

                        <h6><strong>Here is your reservation details:</strong></h6>
                        <br>

                        <h6><strong>Reservation number : </strong>${data.reservationId}</h6>
                    <br>
                    <c:if test="${data.reservationHotelNo == 1}">
                        <h6><strong class="right-align,inline"> Reservation for </strong>Orchard Hotel</h6>
                    </c:if>
                    <c:if test="${data.reservationHotelNo == 2}">
                        <strong class="right-align,inline"><h6>Hotel Reservation for </strong>Marina Hotel</h6>
                    </c:if>
                    <c:if test="${data.reservationHotelNo == 3}">
                        <strong class="right-align,inline"><h6>Hotel Reservation for </strong>BeachView Hotel</h6>
                    </c:if>
                    <br>
                    <h6><strong class="right-align,inline"> Room Type:</strong>  ${data.reservationRoomType} </h6>
                    <br>                   
                    <h6><strong class="right-align,inline"> Promotion Code:</strong>  ${promotion.promotionCode} </h6>
                    <br>
                    <h6><strong class="right-align,inline"> Total Price:</strong>  ${data.reservationTotal} </h6>
                    <br>
                    <h6><strong>Check-in Date : </strong><fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                    value="${data.rcCheckInDate}" /></h6>
                    <br>
                    <h6><strong>Check-out Date : </strong><fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                    value="${data.rcCheckOutDate}" /></h6>
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
                    <br>
                    <c:if test="${coupon!=null}">
                        <h6><strong>Here is a coupon with coupon code ${coupon.getCouponId()}.</strong></h6>
                        <h6><strong>You can use this coupon to purchase attraction or show ticket with $20 deduction.</strong></h6>
                        <h6><strong>One coupon can only be used once.</strong></h6>
                    </c:if>
              
       
                      </div>

            </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
