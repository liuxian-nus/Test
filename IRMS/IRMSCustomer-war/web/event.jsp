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
                    <form id="search-form" action="restaurantSearch" method="POST">
                        <fieldset>
                            <legend>Search venue</legend>

                            <div class="row">
                                <div class="large-4 columns">
                                    <!-- <label for="customDropdown">Event Type</label>-->
                                    <select id="customDropdown" class="button dropdown secondary small" name="VenueFunction">
                                        <option>Conference</option>
                                        <option>Dinner</option>
                                        <option>Dinner and Dance</option>
                                        <option>Exhibition</option>
                                        <option>Meeting</option>
                                        <option>Seminar</option>
                                        <option>Wedding</option>
                                    </select>

                                </div>


                                <div class="large-4 columns">
                                    <!--<label for="customDropdown">Venue Capacity</label>-->
                                    <select id="customDropdown" class="button dropdown secondary small" name="Venue Capacity">
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
                    <input type="submit" class="button" value="Request for proposal">
                    <input type="submit" class="button" value="Manage your event   ">
                </div>
            </div>


            <div clas="row">              
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>Royal China</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_01.png">
                        </a>
                        <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                    </div>
                </div>
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>The Reuben’s</strong></h5>

                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_02.png">
                        </a>
                        <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                    </div>
                </div>
                <div class="large-4 columns">
                    <div class="panel">
                        <h5><strong>The Rocky Road</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_03.png">
                        </a>
                    </div>
                </div>
            </div>
            <div clas="row">
                <div class=""
            </div>


        <jsp:include page="footer.jsp"></jsp:include>
</html>
