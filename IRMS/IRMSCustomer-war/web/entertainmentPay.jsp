<%-- 
    Document   : entertainmentPay
    Created on : Oct 31, 2013, 10:53:07 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="row">
        <fieldset>
            <legend>Booking Summary</legend>
             <div class="large-4 columns">
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="/IRMS-war/images/Harry-Potter.jpg" width="100" height="100">
                        </a>
                    </div>
                    <div class="large-8 columns">
                        <h5><strong>${thisShow.showName}</strong></h5>
                        <p>
                            ${thisShow.showDescription}
                            <br>
                        Time Chosen: 
                        </p>
                        <p>
                        <fmt:formatDate type="both" pattern="dd MMM YYYY hh:mm a"value="${thisSchedule.startDateTime}" />
                        <br>
                        Ticket Chosen:
                       </p>
                    </div>
                        <a href="#" data-reveal-id="myModal">Click Me For A Modal</a>
                        
        </fieldset>
        </div>
        <div class ="row">
            <div class="large-7 columns">
                <div class="panel callout">
                    <form data-abide action="hotelPayConfirm" method="POST">
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
                                <select name="mm" id="button dropdown">
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
                                <select name="yyyy" id="button dropdown">
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
                        </select>
                        </div>
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <label for="right-label" class="left-align,inline"><strong>I agree with the booking conditions and general terms by booking this room</label>
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit" class="small button" class="center-align" value="Pay & Book Now!">
                        </div>
                    </fieldset>
                </form>                     
            </div>

        <div class="large-5 columns">
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
                        <div class="large-7 columns">
                            <label for="right-label" class="left-align,inline"><h6>Number of nights:</h6></label>
                        </div>
                        <div class="large-5 columns">
                            <label for="right-label" class="right-align,inline"><strong></strong></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="large-5 columns">
                            <label for="right-label" class="left-align,inline"><h6>Total:</h6></label>
                        </div>
                        <div class="large-7 columns">
                            <label for="right-label" class="right-align,inline"><strong></strong></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="large-7 columns">
                            <label for="right-label" class="left-align,inline"><h6>Service Charge:</h6></label>
                        </div>
                        <div class="large-5 columns">
                            <label for="right-label" class="right-align,inline"><strong></strong></label>
                        </div>
                    </div>
                </fieldset>
                <br><br>
                <div class="row">
                    <div class="large-2 columns"></div>
                    <div class="large-10 columns">
                        <label for="right-label" class="left-align,inline"><strong>15% of the total room reservation fee will be deducted from your credit card after making the reservation. This service charge will not be refundable nor transferable</strong></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
                    <div id="myModal" class="reveal-modal">
  <h2>Awesome. I have it.</h2>
  <p class="lead">Your couch.  It is mine.</p>
  <p>Im a cool paragraph that lives inside of an even cooler modal. Wins</p>
  <a class="close-reveal-modal">&#215;</a>
  <a class="close-reveal-modal">Submit</a>
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

