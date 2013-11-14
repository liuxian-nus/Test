<%-- 
    Document   : entertainmentVenue
    Created on : Oct 22, 2013, 8:35:47 PM
    Author     : lionetdd
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="row">
                <ul class="button-group round even-4">
                    <li><p class = "button small secondary"><strong>Select date and time ></strong></li>
                    <li><p class="button small secondary"><strong>Select your seats ></strong></li>
                    <li><p class="button small disabled" style="color:white"><strong>Make payment ></strong></li>
                    <li><p class="button small secondary"><strong>Confirm your reservation ></strong></li>
                </ul>
            </div>
            <fieldset>
            <legend>Booking Summary</legend>
             <div class="large-4 columns">
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="${thisShow.imagePath}" width="100" height="100">
                        </a>
                    </div>
                    <div class="large-8 columns">
                        <h5><strong>${thisShow.showName}</strong></h5>
                        <p>
                            ${thisShow.showDescription}
                            <br>
                        <h6> <strong> Time Chosen: </strong> </h6> <fmt:formatDate type="both" pattern="dd MMM yyyy hh:mm a"value="${thisSchedule.startDateTime}" />
                        <br>
                        <h6><strong>Ticket Chosen: (No. of Tickets Bought/Price)</strong></h6>
                        <c:forEach items="${totalTickets}" var="ticket" >
                            <h6>${ticket.showTicketType} : ${ticket.showTicketQuantity} / ${ticket.showTicketPrice}</h6>
                        </c:forEach>
                            <p>
                            <h6><strong>Ticket Total Price: </strong></h6>${ticTotal}
                            </p>
                        </p>
                    </div>
            </fieldset>

        <div class ="row">
            <div class="large-7 columns">
                <div class="panel callout">
                    <form data-abide id="paymentForm" action="entertainmentPayConfirm" method="POST">
                        <fieldset>
                           
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Select Payment Method</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <select name="payment" id="customDropdown">
                                        <option value="">Please Select</option>
                                        <option selected="selected" value="Visa">Visa</option>
                                        <option value="MasterCard">MasterCard</option>
                                        <option value="JCB">JCB</option>
                                        <option value="American Express">American Express</option>
                                        <option value="PayPal">PayPal</option>
                                        <option value="Carte Bleue">Carte Bleue</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Card Number</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input required type="text" id="input-cardNo"  placeholder="cardNo" name="cardNo">
                                    <small class="error">Please enter your card number.</small>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Card Holder Name</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input required type="text" id="input-cardName"  placeholder="cardName" name="cardName">
                                    <small class="error">Please enter the name of the card holder.</small>
                                </div>
                            </div>

                            <div class="row">                                
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Expire Date</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-4 columns">
                                    <select name="cardMonth" id="button dropdown">
                                        <option value="0">Month</option>
                                        <option value="1">Jan</option>
                                        <option value="2">Feb</option>
                                        <option value="3">Mar</option>
                                        <option value="4">Apr</option>
                                        <option value="5">May</option>
                                        <option value="6">Jun</option>
                                        <option value="7">Jul</option>
                                        <option value="8">Aug</option>
                                        <option value="9">Sep</option>
                                        <option value="10">Oct</option>
                                        <option value="11">Nov</option>
                                        <option value="12">Dec</option>
                                    </select>
                                </div>
                                <div class="small-6 columns">
                                    <select name="cardYear" id="button dropdown">
                                        <option value="">Year</option>
                                        <option value="2013">2013</option>
                                        <option value="2014">2014</option>
                                        <option value="2015">2015</option>
                                        <option value="2016">2016</option>
                                        <option value="2017">2017</option>
                                        <option value="2018">2018</option>
                                        <option value="2019">2019</option>
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>                                   
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>CVC-code</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-6 columns">
                                    <input required type="text" id="input-cardCVC"  placeholder="cardCVC" name="cardCVC">
                                    <small class="error">Please enter the card CVC number.</small>
                                </div>
                            </div>
                             <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Promotion Code</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input id="input-promotionCode"  placeholder="Promotion Code" name="promotionCode">
                                </div>
                            </div>


                            <br>
                            <div class="row">
                                <div class="small-1 columns">
                                    <input type="checkbox" name="agree" value="true" id="agree">
                                </div>
                                <div class="small-11 columns"> 
                                    <strong><label><strong>I agree with the booking conditions and general terms by booking this room</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" onclick="return validate()" class="small button" class="center-align" value="Pay & Book Now!">
                                    <p>${message}</p>
                                </div>
                            </div>
                            <!--  <div class="button" onclick="paypal()">Register</div>-->
                        </fieldset>
                    </form>                           
                </div>
            </div>
                <div class="large-5 columns">
                    <div class="panel">
                         <c:choose>
                        <c:when test="${member == null}">
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6>Redeem your points and save money now!</h6></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-4 columns">                               
                                    <a href="member" class="small button">Log In</a>
                                </div>
                                <div class="large-8 columns">
                                    <a href="memberRegister" style="color:#4d4d4d"> Not a member?</a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6><strong>Welcome back, ${member.memberName}</strong></h6></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6><strong>You have ${member.coin} coins.</strong></h6></label>
                                </div>
                            </div>

                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
            
                            
            <jsp:include page="footer.jsp"></jsp:include>
     
            <script>
                function closeReveal(){
                    $('#myModal').foundation('reveal', 'close');
                }
            </script>
         <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>');
        </script> 
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>

