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
    
 <jsp:include page="base.jsp"></jsp:include>
    </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="row">
                <div clas="large-9" colums></div>
                <div class="large-3" columns>
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
                                        <option>Whole</option>
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
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
