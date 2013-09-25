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
            <h1>Your reservation has been confirmed!</h1>
            <h2>Please use your reservation number to view or update! Thank you!</h2>
            <div class="row">
                <div class="large-3 columns">
                    
                </div>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
