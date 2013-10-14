<%-- 
    Document   : eventRegisterResult
    Created on : Oct 14, 2013, 3:00:29 PM
    Author     : Diana Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Register Result Page</title>
        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <fieldset>
                <legend>Event Registration Confirmation</legend>
                <div class="row">
                    <div class="small-11 small-centered columns">
                        <div class="panel">
                            <h6><strong>Here is your reservation details, please use your reservation number to make further booking or modifications.</strong></h6>
                            <br>
                            <h6><strong>Event Reservation number : </strong>${data.eventId}</h6>
                        <br>
                        <h6><strong>Start Date : </strong>${data.startDate}</h6>
                        <br>
                        <h6><strong>End Date : </strong>${data.endDate} </h6>
                        <br> 
                        <h6><strong>Event Name : </strong>${data.eventName}</h6>
                        <br>
                        <h6><strong>Event Type : </strong>${data.eventType}</h6>
                        <br>
                        <h6><strong>Event Status : </strong>${data.status}</h6>
                        <br>
                        <h6><strong>Event Contact : </strong>${data.title}${data.name}</h6>
                        <br>
                        <h6><strong>Contact's Email; : </strong>${data.email}</h6>
                    </div>
                </div>
            </div>
                    </fieldset>
                    </body>
                    </html>
