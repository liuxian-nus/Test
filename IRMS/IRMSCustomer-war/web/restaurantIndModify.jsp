<%-- 
    Document   : restaurantIndModify
    Created on : Sep 24, 2013, 9:54:52 PM
    Author     : Diana Wang
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
        <jsp:include page="base.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/IRMSCustomer-war/js/bootstrap-datepicker.js"></script>
        <title>restaurant search and modify information</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Your reservation is displayed below</h1>
        <div class="row">
                
                <div class="large-9" columns>
                    <form id="search-form" action="restaurantCheck" method="POST">
                        <fieldset>
                            <legend>Your reservation order</legend>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation number</label>
                                    <input type ="text" value="${data.indReservationId}" name="restId"/>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer title</label>
                                    <input type="text" value="${data.title}"/>
                                </div>   
                            </div>
                                
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer name</label>
                                    <input type="text" value="${data.name}"/>
                                </div> 
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer mobile</label>
                                    <input type="text" value="${data.mobile}"/>
                                </div> 
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation number of people</label>
                                    <input type="text" value="${data.numberPeople}"/>
                                </div> 
                            </div>
                                
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation date</label>
                                    <input type="text" value ="${data.indReservationDateTime}"/>
                                </div>                                        
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation email</label>
                                    <input type ="text" value ="${data.email}"/>
                                </div>  
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation notes</label>
                                    <input type ="text" value ="${data.notes}"/>
                                </div> 
                            </div>
                            
                             <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Confirm">

                                </div>
                            </div>
                        </fieldset>
                        
                    </form>
                    
                </div>
         </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
