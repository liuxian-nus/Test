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
                        $(document).ready(function() {
                $("#datepicker1").datepicker({
                 minDate:0,
                onClose: function(selectedDate) {
                var minDate = $(this).datepicker('getDate');
                var newMin = new Date(minDate.setDate(minDate.getDate() + 1));
                $("#datepicker2").datepicker("option", "minDate", newMin);
                }
                });
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
                                <label for="hotel"><strong>Where to live</strong></label>
                                <select id="hotel" name="hotel" onblur="loadRoomType()">
                                    <option></option>
                                    <option>Orchard Hotel</option>
                                    <option>Marina Hotel</option>
                                    <option>BeachView Hotel</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label for="roomType"><strong>Type of room</strong></label>
                                <select id="roomType" name="roomType">

                                    <option></option>

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
                                
                                   <select id="input-room" name="roomCount">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-12 columns">
                                <label for="right-label" class="left-align,inline"><strong>Number of People</label>                               
                                 <select id="input-people" name="people">
                                    <option>1</option>
                                    <option selected='selected'>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option>24</option>
                                    <option>25</option>
                                    <option>26</option>
                                    <option>27</option>
                                    <option>28</option>
                                    <option>29</option>
                                    <option>30</option>
                                    <option>31</option>
                                    <option>32</option>
                                    <option>33</option>
                                    <option>34</option>
                                    <option>35</option>
                                    <option>36</option>
                                    <option>37</option>
                                    <option>38</option>
                                    <option>39</option>
                                    <option>40</option>
                                 </select>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <input type="submit" class="small button" value="Search Availability">
                                

                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
        <div class="large-9 columns"> 
            <div class="row">
                <div class="panel">
                    <div class="row">
                        <div class="large-5 columns">
                        <h5><strong>&nbsp Orchard Hotel</strong></h5>
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
                                <h5><strong>&nbsp Marina Hotel</strong></h5>

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
                            <p style="color:#4d4d4d">Marina Hotel is a luxurious treat for art lovers. A tribute to one of America's greatest contemporary architects 
                                Michael Graves, we let this boutique hotel speak for itself through the designer's elegant and distinctive touches, from lamps and
                                crockery to furnishings and the decor.We enrich all our guests' stay experience with art through our art gallery-like atmosphere in 
                                every maple-accented room, which features mural-adorned walls and artistic furniture pieces. Even the bathrooms are works of art, with
                                flower-motif mosaics on the walls and the unusual blue mosaic-tiled circular shower.</p>

                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="large-5 columns">
                                <h5><strong>&nbsp BeachView Hotel</strong></h5>
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
                '.js><\/script>');
        </script> 

    <script>
        $(document).foundation();    </script>
        <script>
            function loadRoomType(){
                var hotel = document.getElementById('hotel').value;
                if (hotel=== "Orchard Hotel"){
                    $('#roomType').html('<option value="Orchard Suite">Orchard Suite</option> <option value="Deluxe Suite">Deluxe Suite</option><option value="Deluxe">Deluxe</option>');
            
                }
                else if (hotel==="Marina Hotel"){
                    $('#roomType').html('<option value="Chairman Suite">Chairman Suite</option><option value="Deluxe Suite">Deluxe Suite</option><option value="Deluxe">Deluxe</option>');
                     
                }else if (hotel==="BeachView Hotel"){
                    $('#roomType').html('<option value="Superior">Superior</option><option value="Deluxe Suite">Deluxe Suite</option><option value="Deluxe">Deluxe</option>');
                 
                }else{
                    
                }
           
            }
            </script>
</body>
</html>
