<%-- 
    Document   : ticketBookingPayment
    Created on : 30-Oct-2013, 17:18:53
    Author     : Jieqiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>Attraction at Coral Resort World</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h4>Payment</h4>
        <form action="ticketBookingConfirmation" method="POST">
            <div class="row">
                <div class="large-2 large columns">
                    <label for="right-label" class="left-align,inline"><strong>Email</label>
                </div>
                <div>
                    <div class="large-6 large columns">
                        <input required type="text" placeholder="Email" name="email">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="large-2 large columns">
                    <label for="right-label" class="left-align,inline"><strong>Payment method</label>
                </div>
                <div class="small-6 columns"> 

                    <select required id="customDropdown" name="paymentMethod">
                        <option value="Card">Credit card</option>
                        <option value="Coin">Coins</option>
                    </select>
                </div>
                <div class="large-4 columns">
                    <p>${message}</p>
                </div>
            </div>
            <div class="row">
                <input type="submit" class="small button" value="Submit">
            </div>

        </form>
        
       
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
