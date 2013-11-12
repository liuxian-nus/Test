<%-- 
    Document   : hotelSearch
    Created on : 2013-10-7, 22:30:39
    Author     : liuxian
--%>

<%@page import="ACMS.entity.RoomPriceEntity"%>
<%@page import="ACMS.entity.ReservationEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>

<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
            <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
            <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
            <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
            <script type="text/javascript" src="/IRMSCustomer-war/js/countdown.js"></script> 

        </head>
        <body>

        <jsp:include page="header.jsp"></jsp:include>

            <script type="text/javascript">
                
                //process order
                function paypal(){
                    $.ajax({
                        url: "https://api-3t.sandbox.paypal.com/nvp",
                        data:{
                            "USER": "xinyusoc-facilitator_api1.gmail.com",
                            "PWD": "1383997852",
                            "SIGNATURE": "AFcWxV21C7fd0v3bYYYRCpSSRl31A4L4WLmbdOQyA2Nn26.xecMb47ed",
                            "METHOD": "SetExpressCheckout",
                            "VERSION": "93",
                            "PAYMENTREQUEST_0_PAYMENTACTION": "SALE",
                            "PAYMENTREQUEST_0_AMT": "10.00",
                            "PAYMENTREQUEST_0_CURRENCYCODE": "USD",
                            "cancelUrl": "http://is3102.cloudapp.net", //cancel order
                            "returnUrl": "http://is3102.cloudapp.net/IRMSCustomer-war/irmsServlet/hotelPay" //confirm order
                        },
                        dataType: "text",
                        success: function (data) {
                            console.log(data);
                            var token = data.match(/TOKEN=(.*?)&/)[1];
                            //store order with token
                            window.location.replace("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token);
                        }
                      });
                }
                
                
                
                
                
                
                function getParameterByName( name,href )
                {
                  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
                  var regexS = "[\\?&]"+name+"=([^&#]*)";
                  var regex = new RegExp( regexS );
                  var results = regex.exec( href );
                  if( results == null )
                    return "";
                  else
                    return decodeURIComponent(results[1].replace(/\+/g, " "));
                }
                
                function confirm_order(){
                    var path = window.location.href;
                    var token = getParameterByName("token",path);
                    var payer = getParameterByName("PayerID",path);
                    console.log()
                    console.log(token);
                    console.log(payer);
                    $.ajax({
                        url: "https://api-3t.sandbox.paypal.com/nvp",
                        data:{
                            "USER": "xinyusoc-facilitator_api1.gmail.com",
                            "PWD": "1383997852",
                            "SIGNATURE": "AFcWxV21C7fd0v3bYYYRCpSSRl31A4L4WLmbdOQyA2Nn26.xecMb47ed",
                            "METHOD": "DoExpressCheckoutPayment",
                            "VERSION": "93",
                            "TOKEN": token,
                            "PAYERID": payer,
                            "PAYMENTREQUEST_0_PAYMENTACTION": "SALE",
                            "PAYMENTREQUEST_0_AMT": "10.00",
                            "PAYMENTREQUEST_0_CURRENCYCODE": "USD"
                        },
                        dataType: "text",
                        crossDomain: true,
                        success: function (data) {
                        }
                      });
                }
            </script> 
            <div class="row">
                <ul class="button-group round even-4">
                    <li><a href="#" class="button secondary small"><strong>Search your hotel ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Fill in your information ></strong></a></li>
                    <li><a href="#" class="button small" disabled><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Confirm your reservation ></strong></a></li>
                </ul>
            </div>
            <div class ="row">

                <div class='large-6 columns'>
                    <div class="panel callout" >
                        <div class="row">
                            <div class="large-8 columns">
                            <c:if test="${data.reservationHotelNo == 1}">
                                <h6>Reservation for <strong class="right-align,inline"> Orchard Hotel</strong></h6>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 2}">
                                <h6>Hotel Reservation for <strong class="right-align,inline"> Marina Hotel</strong></h6>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 3}">
                                <h6>Hotel Reservation for <strong class="right-align,inline"> BeachView Hotel</strong></h6>
                            </c:if>
                            <h6>Room Type: <strong class="right-align,inline"> ${data.reservationRoomType} </strong></h6>
                            <h6>Check-In Date: <strong class="right-align,inline"> <fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                            value="${data.rcCheckInDate}" /> </strong></h6>
                            <h6>Check-Out Date: <strong class="right-align,inline"><fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                            value="${data.rcCheckOutDate}" /></strong></h6>
                        </div>

                        <div class="large-4 columns">
                            <c:if test="${data.reservationHotelNo == 1}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/orchard.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/orchard.jpg">
                                </a>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 2}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/marina.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/marina.jpg">
                                </a>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 3}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/beachview.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/beachview.jpg">
                                </a>
                            </c:if>

                        </div>
                    </div>
                </div>
                <div class="panel callout">
                    <form data-abide id="paymentForm" action="hotelPayConfirm" method="POST">
                        <fieldset>
                            <p>${message}</p>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Select Payment Method</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <select name="payment" id="customDropdown">
                                        <option value="">Please Select</option>
                                        <option value="Visa">Visa</option>
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
                                        <option value="2018">2018/option>
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



                            <br>
                            <div class="row">
                                <div class="small-1 columns">
                                    <input type="checkbox" name="subscribe" value="true" required>
                                </div>
                                <div class="small-11 columns"> 
                                    <strong><label><strong>I agree with the booking conditions and general terms by booking this room</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" class="center-align" value="Pay & Book Now!">
                                </div>
                            </div>
                             <div class="button" onclick="paypal()">Register</div>
                        </fieldset>
                    </form>                     
                </div>
            </div>





            <div class="large-6 columns">
                <div class="panel">
                    <fieldset>
                        <legend style="color:#4d4d4d">Booking Summary</legend>
                        <p>${message}</p>
                        <div class="row">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><h6>Rate: </h6></label>
                            </div>
                            <div class="large-7 columns">
                                <label for="right-label" class="right-align,inline"><strong></strong></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-6 columns">
                                <label for="right-label" class="left-align,inline"><h6>Number of nights:</h6></label>
                            </div>
                            <div class="large-6 columns">
                                <label for="right-label" class="right-align,inline"><strong>${data.reservationRoomCount}</strong></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-6 columns">
                                <label for="right-label" class="left-align,inline"><h6>Total:</h6></label>
                            </div>
                            <div class="large-6 columns">
                                <label for="right-label" class="right-align,inline"><strong></strong></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-6 columns">
                                <label for="right-label" class="left-align,inline"><h6>Guest Name:</h6></label>
                            </div>
                            <div class="large-6 columns">
                                <label for="right-label" class="right-align,inline"><strong>${data.rcName}</strong></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-6 columns">
                                <label for="right-label" class="left-align,inline"><h6>Guest Email:</h6></label>
                            </div>
                            <div class="large-6 columns">
                                <label for="right-label" class="right-align,inline"><strong>${data.rcEmail}</strong></label>
                            </div>
                        </div>
                        <div class='row'>
                            <div class='large-12 columns'>
                                <label for="right-label" class="left-align,inline"><h6>Your reservation session will be time out in :</h6></label>
                            </div>
                        </div>
                        <div class='row'>
                            <div class='large-12 columns'>
                                <script type="text/javascript">

                                    var myCountdown2 = new Countdown({
                                        time: "${SessionTime}", width: 100, height: 50, rangeHi: "minute"
                                    });
                                </script>
                            </div>
                        </div>
                    </fieldset>
                    <br><br>

                </div>
            </div>




        </div>



        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 



        <script>
            $(document).foundation();
        </script>
    </body>
</html>
