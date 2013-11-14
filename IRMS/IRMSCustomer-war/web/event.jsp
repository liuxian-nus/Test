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
                            <legend>Search venue</legend>

                            <div class="row">
                                <div class="large-4 columns">
                                    <!-- <label for="customDropdown">Event Type</label>-->
                                    <select id="customDropdown" class="button dropdown secondary small" name="venueFunction">
                                        <option>Convention</option>
                                        <option>Exhibition</option>
                                        <option>Auditorium</option>
                                        <option>Seminar</option>
                                        <option>Banquet</option>
                                        <option>Meeting</option>
                                    </select>

                                </div>


                                <div class="large-4 columns">
                                    <!--<label for="customDropdown">Venue Capacity</label>-->
                                    <select id="customDropdown" class="button dropdown secondary small" name="venueCapacity">
                                        <option value="50">Below 50 people</option>
                                        <option value="100">50-100 people</option>
                                        <option value="500">100-500 people</option>
                                        <option value="1000">500-1000 people</option>
                                        <option value="3000">1000-3000 people</option>


                                    </select>
                                </div>

                                <div class="large-4 columns">
                                    <!--<label for="customDropdown">Venue Capacity</label>-->
                                    <input type="submit" class="small button" value="Search">
                                </div>

                                <!--     <div class="large-2 columns">
                                         <input type="submit" class="small button" value="Search">
                                     </div>-->
                            </div>
                        </fieldset>         
                    </form>

                </div>
              
                <div class="large-3 columns">
                    
                       <ul class="button-group">                    
                                  <li><a href="eventRegister" class="button">Request for proposal</a></li>
                                  <li><a href="eventVenueList" class="button">Browse all venues &nbsp&nbsp&nbsp&nbsp   </a></li>
                            </ul>
           
                   <!-- <input h class="button" value="Request for proposal">
         
                    <input type="submit" class="button" value="Manage your event   ">-->
                </div>
            </div>


            <div clas="row">              
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>Seminars and Meetings</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                            <img src="/IRMSCustomer-war/images/event/Seminar.jpg" style="width:400px;height:150px">
                        </a>
                        <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                    </div>
                </div>
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>Banquets and Weddings</strong></h5>

                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                            <img src="/IRMSCustomer-war/images/event/banquet.jpg" style="width:400px;height:150px">
                        </a>
                        <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                    </div>
                </div>
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>Conventions and Exhibitions</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                            <img src="/IRMSCustomer-war/images/event/convention.jpg" style="width:400px;height:150px">
                        </a>
                    </div>
                </div>
            </div>
            <div clas="row">
                <div class=""
            </div>


        <jsp:include page="footer.jsp"></jsp:include>
</html>
