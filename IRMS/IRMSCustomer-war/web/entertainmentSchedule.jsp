<%-- 
    Document   : entertainmentSchedule
    Created on : Oct 31, 2013, 3:35:55 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/IRMSCustomer-war/css/flipclock.css" type="text/css" media="screen">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
        <title>Event Venue Booking</title>
        <jsp:include page="base.jsp"></jsp:include>
    
         <script type="text/javascript" src="/IRMSCustomer-war/js/countdown.js"></script>
               <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
     <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
       <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
    <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/bootstrap-datepicker.js"></script>
         
        </head>
        <body>
            
        <jsp:include page="header.jsp"></jsp:include>
        <h5> Please select your ticket for <strong>${thisShow.showName}</strong> </h5>
     
        <div class="row">
            <ul class="button-group round even-4">
                <li><p style="color:white"class = "button small disabled"><strong>Select date and time ></strong></li>
                <li><p class="button small secondary"><strong>Select your seats ></strong></li>
                <li><p class="button small secondary"><strong>Make payment ></strong></li>
                <li><p class="button small secondary"><strong>Confirm your reservation ></strong></li>
            </ul> 
       </div>
        <div class="row" style="width:600px;height:100px" >
       
         </div>
        <div class="row">
            <div class="large-4 columns">
                <fieldset>

                   <h5><strong>${thisShow.showName}</strong></h5>
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="/IRMS-war/images/Harry-Potter.jpg" width="100px" height="100px">
                        </a>                  
                        <p>
                            ${thisShow.showDescription}
                        </p>
                
                </fieldset>
            </div>
            <div class="large-8 columns">
                <form action="entertainmentVenue" method="POST">
                <c:forEach items="${showSchedules}" var="schedule">
                    <div class="row">
                        <div class="panel large-10 large-offset-1 columns">
                            <div class="row">
                                <div class="large-4 columns">
                                    <div style="width: 90px;
                                         height: 90px;background: url('/IRMSCustomer-war/images/Calendar_background.png'); background-size: 90px 90px">
                                        <!--<img src="/IRMSCustomer-war/images/Calendar_background.png" alt=" "  style="z-index: -1"/>
                                        <div style="position:absolute;left:0px;top:0px;font-size: 32px;display: none">-->
                                        <p style="padding-top: 10px; padding-left:12px; color:white"> <strong> <fmt:formatDate type="date" pattern="MMM yyyy" 
                                                        value="${schedule.startDateTime}" /></strong></p>
                                        <p style="margin-top:-15px;padding-left:22px; font-size: 32px"><strong><fmt:formatDate type="date" pattern="dd" 
                                                        value="${schedule.startDateTime}" /></strong></p>
                                        <p style="margin-top:-20px;padding-left:28px;"><strong><fmt:formatDate type="date" pattern="E" 
                                                        value="${schedule.startDateTime}" /></strong></p>
                                    </div>
                                </div>
                                <div class="large-8 columns">
                                    <input type="radio" name="scheduleId" required value="${schedule.showScheduleId}">
                                    <lable style="color:#4d4d4d;font-size: 18px">
                                        <fmt:formatDate type="time" pattern="hh:mm a"value="${schedule.startDateTime}" />
                                    </lable><br>
                                </div>
                            </div>
                        </div>
                    </div>


                </c:forEach>
                    <div class="row">
                        <div class="large-2 columns large-offset-9"> 
                    <input type="submit" class="medium secondary button" value="Next"> 
                    </div>
                    </div>
                </form>
            </div>
        </div>



<jsp:include page="footer.jsp"></jsp:include>
  
</body>
</html>
