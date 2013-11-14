<%-- 
    Document   : jsp
    Created on : Nov 14, 2013, 9:29:14 AM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Black Pearl Banquet Rooms</h1> 
        <div class="row">
            <div class="large-6 columns">
                <strong> Organize your event with us: </strong>
                <br>
                <div class="panel"> 
                    <img src="/IRMSCustomer-war/images/event/banquet.jpg"/>

                </div>
                <div>
                    <ul class="tostay-specs"><li>7 Banquet Rooms</li>
                        <li>1,600 square metres in combined area</li>
                        <li>1,300 total seating capacity</li>
                        <li>Conveniently located near to Hard Rock Hotel Singapore</li>
                    </ul>
                 
                    If you would like to host your show at Coral Island Resort, please fill in your 
                    information. 
                    Our external show managers will contact you within 2 working days.

                </div>



            </div>
            <div class="large-6 columns">
                <li>At Equarius Hotel, guests can enjoy the vastness of the sprawling resort 
                    while getting up-close and personal with nature. The hotel has a total of 7 
                    banquet rooms where meetings and social events can be held. 
                    These rooms have a total area of up to 1,600 square metres with a combined 
                    capacity of over 1,400 people. Balconies extend outside the banquet rooms, 
                    bringing in the natural daylight to allow your guests a panoramic view of the lush 
                    tropical forest surrounding the hotel.<br><br>Everything you could possibly need to
                    make business a pleasure is right here. This resort ensconces delegates in a haven of
                    lush greenery with amenities flexible enough to host up to 1,300 guests banquet style.
                    Meeting options are endless with a VIP pavilion, private gardens, verandahs and mesmeric
                    sunset views to complement 7 spacious function rooms – making the Equarius an ideal 
                    choice for convivial themed events, cocktail occasions to celebrating a wedding in style.
                    <br><br>
                    <br><br>
            </div>
            <a href="eventRegister" class="button">Request For Proposal</a>

    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
