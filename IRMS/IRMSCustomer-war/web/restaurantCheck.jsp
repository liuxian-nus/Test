<%-- 
    Document   : restaurantCheck
    Created on : Sep 25, 2013, 11:44:52 AM
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
            <title>Your order has been updated!</title>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h4>Your reservation has been confirmed!</h4> 
            <div class="row">
                <div class="small-11 small-centered columns">
                    <div class="panel">

                        <h6><strong>Here is your reservation details, please use your reservation Id to make modifications.</strong></h6>
                                <br>
                                <h6><strong>Reservation number : </strong>${data.indReservationId}</h6>
                            <br>
                            <h6><strong>Reservation time : </strong>${data.indReservationDateTime}</h6>
                            <br>
                            <h6><strong>Reservation restaurant : </strong>${data.restaurant.restName} </h6>
                            <br> 
                            <h6><strong>Reservation people : </strong>${data.numberPeople}</h6>
                            <br>
                            <h6><strong>Reservation name : </strong>${data.title}${data.name}</h6>
                            <br>
                            <h6><strong>Reservation e-mail : </strong>${data.email}</h6>
                            <br>
                            <h6><strong>Reservation mobile : </strong>${data.mobile}</h6>
                            <br>
                            <h6><strong>Additional notes : </strong>${data.notes}</h6>
                            </div>
                            </div>
                            </div>
                            <jsp:include page="footer.jsp"></jsp:include>
                            </body>
                            </html>
