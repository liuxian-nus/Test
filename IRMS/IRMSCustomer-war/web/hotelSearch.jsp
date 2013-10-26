<%-- 
    Document   : hotelBooking
    Created on : Oct 7, 2013, 12:00:00 AM
    Author     : liuxian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="base.jsp"></jsp:include>
            <title>Hotel Search</title>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
          


            <script>

                $(function() {
                    $("#datepicker1").datepicker();
                });
            </script>
            <script>

                $(function() {
                    $("#datepicker2").datepicker();
                });
            </script>
        </head>


        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="large-3 columns">    
                <form id="search-form" action="searchAvailable" method="POST">
                    <fieldset>
                        <legend>Search for hotels</legend>

                        <div class="row">
                            <div class="large-12 columns">
                                <label for="customDropdown"><strong>Where to live</strong></label>
                                <select id="customDropdown" name="hotel">

                                    <option></option>
                                    <option>Orchard Hotel</option>
                                    <option>Marina Hotel</option>
                                    <option>BeachView Hotel</option>

                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label for="customDropdown"><strong>Type of room</strong></label>
                                <select id="customDropdown" name="roomType">

                                    <option></option>
                                    <option>Orchard Suite</option>
                                    <option>Chairman Suite</option>
                                    <option>Deluxe Suite</option>
                                    <option>Deluxe</option>
                                    <option>Superior</option>

                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-12 columns">
                                <label for="customDropdown"><strong>Check In Date</strong></label>
                                <input type="text" id="datepicker1" name="in_date" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns"> 
                                <label for="customDropdown"><strong>Check Out Date</strong></label>

                                <input type="text" id="datepicker2" name ="out_date"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-12 columns">
                                <label for="right-label" class="left-align,inline"><strong>Number of Rooms</label>
                                <input id="input-room"  placeholder="roomCount" name="roomCount">
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-12 columns">
                                <label for="right-label" class="left-align,inline"><strong>Number of People</label>
                                <input id="input-people"  placeholder="people" name="people">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <input type="submit" class="small button" value="Search Availability">
                                <input type="hidden" name="availableHotel" value="${data.reservationHotel}"/>
                            <input type="hidden" name="availableType" value="${data.reservationRoomType}"/>
                            <input type="hidden" name="availableCheckInDate" value="${data.rcCheckInDate}"/>
                            <input type="hidden" name="availableCheckOutDate" value="${data.rcCheckOutDate}"/>
                            <input type="hidden" name="availableCheckOutDate" value="${data.reservationRoomCount}"/>

                        </div>
                    </div>
                </fieldset>
                <!--                   <form action="restaurantBook"><input class="small button" type="Search" value ="Search Availability"/>
                                       <input type="hidden" name="availableHotel" value="${data.availableHotel}"/>
                                   <input type="hidden" name="availableType" value="${data.availableType}"/>
                                   <input type="hidden" name="availableCheckInDate" value="${data.availableCheckInDate}"/>
                                   <input type="hidden" name="availableCheckOutDate" value="${data.availableCheckOutDate}"/>
                               </form>-->
            </form>
        </div>
        <div class="large-9 columns"> 
            <div class="row">
                <div class="panel">
                    <div class="row">
                        <div class="large-5 columns">
                        <h5><strong>&nbsp Orchard Hotel™</strong></h5>
                        </div>
                        <div class="large-7 columns">
                        <img style="vertical-align:text-bottom;" src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        </div>
                         
                    </div>
                    <div class="row">
                    <div class="large-5 columns">
                      
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/hotel2.jpg">
                            <img src="/IRMSCustomer-war/images/gallery/hotel2.jpg">
                        </a>
                    
                        <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                        </div>
                  
                    <div class="large-7 columns">

                      
                        <p style="color:#4d4d4d">Orchard Hotel is the fun destination of Coral Island Resort. The fabulous location, our family- and child-friendly environment, 
                            and range of fun amenities make this vibrant hotel the ideal family getaway.The nearby Festive Walk and 
                            World Square offer a host of non-stop entertainment and facilities to add to the holiday mood. For a fun family getaway, be sure to check in at 
                            Orchard Hotel!                                                                                                                                                         </p>

                    </div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="large-5 columns">
                                <h5><strong>&nbsp Marina Hotel™</strong></h5>

                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/hotel1.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/hotel1.jpg">
                                </a>
                                <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                        </div>
                        <div class="large-7 columns">

                         <img style="vertical-align:text-bottom;" src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                            <p style="color:#4d4d4d">Marina Hotel is a luxurious treat for art lovers. A tribute to one of America’s greatest contemporary architects 
                                Michael Graves, we let this boutique hotel speak for itself through the designer’s elegant and distinctive touches, from lamps and
                                crockery to furnishings and the decor.We enrich all our guests’ stay experience with art through our art gallery-like atmosphere in 
                                every maple-accented room, which features mural-adorned walls and artistic furniture pieces. Even the bathrooms are works of art, with
                                flower-motif mosaics on the walls and the unusual blue mosaic-tiled circular shower.</p>

                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="large-5 columns">
                                <h5><strong>&nbsp BeachView Hotel™</strong></h5>
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/hotel3.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/hotel3.jpg">
                                </a>
                        </div>
                        <div class="large-7 columns">

                        <img style="vertical-align:text-bottom;" src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                        <img style="vertical-align:text-bottom;"src="/IRMSCustomer-war/images/star.png">
                   
                            <p style="color:#4d4d4d">On the western tip of the Coral Island is our idyllic retreat of BeachView Hotel. An exquisite series of 
                                secluded tropical sanctuaries, BeachView Hotel offers spectacular views, personal butlers and luxurious comforts for that well-earned 
                                getaway or a romantic escapade.Enter paradise at Beach Villas, just one of an infinite range of momentous experiences we offer at
                                Coral Island Resort.</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    <jsp:include page="footer.jsp"></jsp:include>
    <script>
                document.write('<script src=' +
                ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                '.js><\/script>')    </script> 

    <script>
        $(document).foundation();    </script>
</body>
</html>
