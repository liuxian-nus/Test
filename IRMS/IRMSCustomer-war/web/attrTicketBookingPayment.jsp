<%-- 
    Document   : ticketBookingPayment
    Created on : 30-Oct-2013, 17:18:53
    Author     : Jieqiong
--%>

<%@page import="CRMS.entity.MemberEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    MemberEntity member=(MemberEntity)session.getAttribute("member");
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>Attraction at Coral Resort World</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <!--Progress Widget-->
            <div class="row">
                <ul class="button-group round even-4">
                    <li><a href="#" class="button small secondary"><strong>Choose your ticket ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>View ticket information ></strong></a></li>
                    <li><a href="#" class="button small" disabled><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Confirm your payment ></strong></a></li>
                </ul>
            </div>
        <h4>Payment</h4>
        <form action="attrTicketBookingConfirmation" method="POST">
            <div class="row">
                <div class="large-2 large columns">
                    <label for="right-label" class="left-align,inline"><strong>Email</label>
                </div>
                <div>
                    <div class="large-6 large columns">
                        <input required type="text" value="${member.memberEmail}"placeholder="Email" name="email">
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
            <p>Please use your registered email if you are a member.</p>
            <div class="row">
                <input type="submit" class="small button" value="Submit">
            </div>

        </form>
        
       
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
