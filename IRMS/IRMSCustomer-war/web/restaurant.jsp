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
    
    <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.min.css" type="text/css" media="screen" />
    <link href="/IRMSCustomer-war/css/templatemo_style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="/IRMSCustomer-war/css/ddsmoothmenu.css" />
    <link rel="stylesheet" href="/IRMSCustomer-war/css/nivo-slider.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/IRMSCustomer-war/css/normalize.css" type="text/css" media="screen" />


    <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
    <script type="text/javascript" src="/IRMSCustomer-war/js/jquery.js"></script>
    <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
    <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
    </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="row">
                <div class="large-12 columns">
                    <form id="search-form" action="restaurantSearch" method="POST">
                        <fieldset>
                            <legend>Search a restaurant</legend>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Search restaurants,bars&pubs</label>
                                    <input id="input-name" type="text" placeholder="restaurant name" name="keyword">
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-4 columns">

                                    <label for="customDropdown">Type of place</label>
                                    <select id="customDropdown" name="restTypeOfPlace">
                                        <option DISABLED>This is a dropdown</option>
                                        <option>Fast food</option>
                                        <option>Restaurant</option>
                                        <option>Pub/Bar</option>
                                    </select>
                                </div>
                                <div class="large-4 columns">

                                    <label for="customDropdown">Neighborhood</label>
                                    <select id="customDropdown" name="restNeighbourhood">
                                        <option DISABLED>This is a dropdown</option>
                                        <option>Whole Singapore</option>
                                        <option>Central Singapore</option>
                                        <option>Western Singapore</option>
                                        <option>Eastern Singapore</option>
                                        <option>Northern Singapore</option>
                                        <option>Southern Singapore</option>
                                    </select>
                                </div>

                                <div class="large-4 columns">

                                    <label for="customDropdown">Cuisine</label>
                                    <select id="customDropdown" name="restCuisine">
                                        <option DISABLED>This is a dropdown</option>
                                        <option>Asian</option>
                                        <option>Chinese</option>
                                        <option>Western</option>
                                        <option>Hala</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                
                            </div>
                            <div class="row">
                                <div class="large-4 columns">

                                <input type="submit" class="small button" value="Search">
                                </div>
                            </div>
                        </fieldset>
                       
                    </form>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
