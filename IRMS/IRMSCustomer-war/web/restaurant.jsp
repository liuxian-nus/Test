<%-- 
    Document   : home
    Created on : Sep 16, 2013, 2:22:54 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(document).ready(function() {
                $("#reg-form").submit(function() {
                    if ($("#input-name").val().length < 1) {
                        $("#input-name").addClass("error");
                    }
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/IRMSCustomer-war/js/bootstrap-datepicker.js"></script>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="row">
                <div class="large-3 columns">
                    <form id="search-form" action="restaurantSearch" method="POST">
                        <fieldset>
                            <legend>Search a restaurant</legend>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Search restaurants,bars&pubs</label>
                                    <input id="input-name" type="text" placeholder="restaurant name" name="keyword">
                                    <script type="text/javascript">
                                    </script>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="customDropdown">Type of place</label>
                                    <select id="customDropdown" name="restTypeOfPlace">

                                        <option></option>
                                        <option>Fast food</option>
                                        <option>Restaurant</option>
                                        <option>Bar</option>
                                        <option>Dessert</option>
                                        <option>Cafe</option>
                                        <option>Food Stall</option>
                                        <option></option>

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="customDropdown">Neighborhood</label>
                                    <select id="customDropdown" name="restNeighbourhood">
                                        <option></option>
                                        <option>Central</option>
                                        <option>West</option>
                                        <option>East</option>
                                        <option>North</option>
                                        <option>South</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns"> 
                                    <label for="customDropdown">Cuisine</label>
                                    <select id="customDropdown" name="restCuisine">
                                        <option></option>
                                        <option>Japanese</option>
                                        <option>Chinese</option>
                                        <option>Western</option>
                                        <option>Halal</option>
                                        <option>Thai</option>
                                        <option>Vegetarian</option>
                                        <option>Korean</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Search">

                                </div>
                            </div>
                        </fieldset>         
                    </form>
                </div>
                <div class="large-3 columns">
                    <div class="panel">
                        <h5><strong>Royal China</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_01.png">
                        </a>
                       <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                    </div>
                </div>
                <div class="large-3 columns">
                    <div class="panel">
                        <h5><strong>The Reuben’s</strong></h5>
             
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_02.png">
                        </a>
                        <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                    </div>
                </div>
                <div class="large-3 columns">
                    <div class="panel">
                        <h5><strong>The Rocky Road</strong></h5>
                        <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                            <img src="/IRMSCustomer-war/images/gallery/food_03.png">
                        </a>
                    </div>
                </div>
            </div>
            <div class="row">
               
                <div class="large-3 columns">
                    <form id="search-form" action="restaurantIndModify" method="POST">
                        <fieldset>
                            <legend>Modify reservation</legend>

                            <div class="row">
                        
                                <div class="large-12 columns">
                                    <label>Input your reservation confirmation number here</label>
                                    <input id="input-name" type="text" placeholder="confirmation number" name="reservationId">

                                </div> 
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <p>${message}</p>
                                <label for="customDropdown">Type of Order</label> 
                                <select id="customDropdown" name="type">
                                    <option>Individual</option>
                                    <option>Catering</option>
                                </select>
                            </div>  
                        </div>
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <input type="submit" class="small button" value="Search">

                                <script type ="text/javascript">

                                </script>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="large-3 columns">
                <div class="panel">
                     <h5><strong>The Java Bean</strong></h5>
                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_04.png">
                        <img src="/IRMSCustomer-war/images/gallery/food_04.png">
                    </a>
                </div>
            </div>
            <div class="large-3 columns">
                <div class="panel">
                            <h5><strong>The Glassfish</strong></h5>
                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_05.png">
                        <img src="/IRMSCustomer-war/images/gallery/food_05.png">
                    </a>
                </div>
            </div>
            <div class="large-3 columns">
                <div class="panel">
                            <h5><strong>The Tom Cat</strong></h5>
                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_06.png">
                        <img src="/IRMSCustomer-war/images/gallery/food_06.png">
                    </a>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
