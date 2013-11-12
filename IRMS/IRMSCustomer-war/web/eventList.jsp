<%-- 
    Document   : eventList
    Created on : Oct 14, 2013, 8:00:16 PM
    Author     : lionetdd
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Coral Island Resort</title>
        <jsp:include page="base.jsp"></jsp:include>

            <script src="/IRMSCustomer-war/js/jquery-ui.min.js"></script>
            <link rel='stylesheet' type='text/css' href="/IRMSCustomer-war/css/fullcalendar.css" />
            <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
            <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
            <script type='text/javascript' src="/IRMSCustomer-war/js/fullcalendar.js"></script>
            <script type='text/javascript' src="/IRMSCustomer-war/js/moment.js"></script>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />


            <script>
                        $(document).ready(function() {
                var eventList = new Array();
            <%
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
            %>
            <c:forEach items="${eventList}" var="item">
                var startdate = "${item.startDate}";
                        var temp = startdate.split(" ");
                        temp = temp[0] + " " + temp[1] + " " + temp[2] + " 2013";
                        startdate = new Date(temp);
                        var enddate = "${item.endDate}";
                        var temp = enddate.split(" ");
                        temp = temp[0] + " " + temp[1] + " " + temp[2] + " 2013";
                        enddate = new Date(temp);
                        var item = {"title": "${item.eventName}",
                        "start": moment(startdate).format("YYYY-MM-DD"),
                        "end": moment(enddate).format("YYYY-MM-DD"),
                        "url": "eventInfo/${item.eventId}"
                        
                };
                        eventList.push(item);            
           </c:forEach>

                                    console.log(eventList);
                                    $('#mycalendar').fullCalendar({
                            header: {
                            left: 'prev,next today',
                                    center: 'title',
                                    right: ''
                            },
                                    defaultView: 'month',
                                    editable: false,
                                    slotMinutes: 5,
                                    allDaySlot: true,
                                    lazyFetching: true,
                                    events: eventList,
                                    eventClick: function(event) {
                                    if (event.url) {
                                    window.open(event.url);
                                    <%%>
                                    return false;
                                    }
                                    }



                            });
                            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div id='mycalendar'></div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
