<%-- 
    Document   : cateringModify
    Created on : Sep 28, 2013, 1:40:23 AM
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
        <title>Catering Update</title>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
        <h1>Your reservation is displayed below</h1>
        <div class="row">
            <div class="large-9" columns>
                <form id="search-form" action="cateringCheck" method="POST">
                    <fieldset>
                        <legend>Your reservation order</legend>
                        <div class="row">
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation number</label>
                                    <input type ="text" name ="orderId" readonly="readonly" value="${data.orderId}"/>
                                </div>
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer title</label>
                                    <input type="text" name ="title" value="${data.title}"/>
                                </div>
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer name</label>
                                    <input type="text" name ="name" value="${data.name}"/>
                                </div> 
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation customer mobile</label>
                                    <input type="text" name ="mobile" value="${data.mobile}"/>
                                </div> 
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation number of people</label>
                                    <input type="text" name ="numberOrder" value="${data.menu.getNumberOrder()}"/>
                                </div> 
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation date</label>
                                    <input type="text" name ="orderDateTime" readonly="readonly" value ="${data.orderDateTime}"/>
                                </div>                                        
                                </div>
                                
                                <div class ="row">
                                <div class="large-12 columns">
                                    <label>Hour(0~23)</label>
                                    <input type="text" name="time" value ="${data.orderDateTime.getHours()}"/>
                                </div>   
                                </div>
                                
                                <div class ="row">
                                <div class="large-12 columns">
                                    <label>Date</label>
                                    <input type="text" name="date" value ="${data.orderDateTime.getDate()}"/>
                                </div>   
                                </div>
                                
                                <div class ="row">
                                <div class="large-12 columns">
                                    <label>Month</label>
                                    <input type="text" name="month" value ="${data.orderDateTime.getMonth()+1}"/>
                                </div>   
                                </div>
                                
                                <div class ="row">
                                <div class="large-12 columns">
                                    <label>Year</label>
                                    <input type="text" name="year" value ="${data.orderDateTime.getYear()+1900}"/>
                                </div>   
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation email</label>
                                    <input type ="text" name ="email" value ="${data.email}"/>
                                </div>  
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <label>Reservation notes</label>
                                    <input type ="text" name ="notes" value ="${data.notes}"/>
                                </div>  
                                </div>
                                
                                <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Confirm">

                                </div>
                                 </div>
                                
                                
                                
                                <input type="hidden" name="booking" value="false"/>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>
