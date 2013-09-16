<%-- 
    Document   : home
    Created on : Sep 16, 2013, 2:22:54 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="base.jsp"></jsp:include>
    <script>
        $(document).ready(function() {
            $("#reg-form").submit(function(){
               if ($("#input-name").val().length<1){
                   $("#input-name").addClass("error");
               }
            });
        });
    </script>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
  
        <div class="row">
            <div class="large-12 columns">
                <form id="search-form">
                    <fieldset>
                      <legend>Search a restaurant</legend>

                      <div class="row">
                        <div class="large-12 columns">
                          <label>Search restaurants,bars&pubs</label>
                          <input id="input-name" type="text" placeholder="restaurant name">
                        </div>
                      </div>

                        <div class="large-4 columns">
                            
                          <label for="customDropdown">Type of place</label>
                            <select id="customDropdown">
                              <option DISABLED>This is a dropdown</option>
                              <option>Fast food</option>
                              <option>Restaurant</option>
                              <option>Pub/Bar</option>
                            </select>
                        </div>
                       <div class="large-4 columns">
                            
                          <label for="customDropdown">Neighborhood</label>
                            <select id="customDropdown">
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
                            <select id="customDropdown">
                              <option DISABLED>This is a dropdown</option>
                              <option>Asian</option>
                              <option>Chinese</option>
                              <option>Western</option>
                              <option>Hala</option>
                            </select>
                        </div>
                    </fieldset>
                  </form>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
