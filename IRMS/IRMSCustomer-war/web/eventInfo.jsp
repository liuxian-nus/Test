<%-- 
    Document   : event
    Created on : Oct 7, 2013, 11:25:47 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coral Island Resort</title>
        <%System.out.println("inside event page ");%>

        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="row"> 
                <div class="large-9 columns">
                    <form id="search-form" action="eventVenueSearch" method="POST">
                        <fieldset>
                            <legend>Event Info</legend>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Event Name</label>
                                    <input type="text" value="${viewedEvent.getEventName()}" disabled>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-6 columns">
                                <label>Start Date:</label>
                                <input type="text" value="${viewedEvent.getStartDate()}" disabled>
                            </div>
                            <div class="large-6 columns">
                                <label>End Date:</label>
                                <input type="text" value="${viewedEvent.getEndDate()}" disabled>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-4 columns">
                                <label>Event Corporate:</label>
                                <input type="text" value="${viewedEvent.getEventCorporate()}" disabled>
                            </div>
                            <div class="large-4 columns">
                                <label>Event Type:</label>
                                <input type="text" value="${viewedEvent.getEventType()}" disabled>
                            </div>
                            <div class="large-4 columns">
                                <label>Event Contact Person:</label>
                                <input type="text" value="${viewedEvent.getEventContact()}" disabled>
                            </div>
                        </div>

          

                        <div class="row">
                            <div class="large-12 columns">
                             
                                <h5> ${viewedEvent.description}</h5>
                            </div>
                        </div>
                    </fieldset>         
                </form>

            </div>
        </div>


        <jsp:include page="footer.jsp"></jsp:include>
</html>
