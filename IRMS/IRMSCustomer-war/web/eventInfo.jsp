<%-- 
    Document   : event
    Created on : Oct 7, 2013, 11:25:47 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <input type="text" value="${viewedEntity.getEventName()}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-6 columns">
                                <label>Start Date</label>
                                <input type="text" value="${viewedEntity.getStartDate()}">
                            </div>
                            <div class="large-6 columns">
                                <label>End Start</label>
                                <input type="text" value="${viewedEntity.getEndDate()}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-4 columns">
                                <label>Event Corporate</label>
                                <input type="text" value="${viewedEntity.getEventCorporate()}">
                            </div>
                            <div class="large-4 columns">
                                <label>Event Type</label>
                                <input type="text" value="${viewedEntity.getEventType()}">
                            </div>
                            <div class="large-4 columns">
                                <label>Event Contact</label>
                                <input type="text" value="${viewedEntity.getEventContact()}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label>Status</label>
                                <input type="text" value="${viewedEntity.getStatus()}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label>Notes</label>
                                <textarea value="${viewedEntity.getNotes()}"></textarea>
                            </div>
                        </div>
                    </fieldset>         
                </form>

            </div>
        </div>


        <jsp:include page="footer.jsp"></jsp:include>
</html>
