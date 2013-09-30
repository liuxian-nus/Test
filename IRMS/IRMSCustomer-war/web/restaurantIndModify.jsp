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

            <a href="#" onclick="checkInfo()"><strong>Modify your reservation below</strong></a>

            <form data-abide id="search-form" action="restaurantCheck" method="POST">
                <fieldset>
                    <legend>Your reservation order</legend>
                    <p>${message}</p>
                <div class="row">
                    <div class="large-12 columns">
                        <label>Reservation number</label>
                        <input type ="text" name ="indReservationId" readonly="readonly" value="${data.indReservationId}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <label>Reservation restaurant name</label>
                        <input type="text" name="restName" readonly="readonly" value="${data.restaurant.restName}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="large-6 columns">
                        <label>Reservation customer title</label>
                        <input type="hidden" id ="title" value="${data.title}"/>
                        <select name="title" id="titleDropdown" name="title">
                            <option value="Mr.">Mr.</option>
                            <option value="Miss">Miss</option>
                            <option value="Mrs.">Mrs.</option>
                        </select>
                    </div>   


                    <div class="large-6 columns">
                        <label>Reservation customer name</label>
                        <input type="text" name ="name" value="${data.name}"/>
                    </div> 
                </div>


                <div class="row">
                    <div class="large-6 columns">
                        <label>Reservation customer mobile</label>
                        <input required pattern="[0-9]{8}" type="text" name ="mobile" value="${data.mobile}"/>
                        <small class="error" id="confirmMessage">Please enter a valid phone number.</small>
                    </div> 


                    <div class="large-6 columns">
                        <label>Reservation number of people</label>
                        <input required pattern="[0-9]{2}" type="text" name ="numberPeople" value="${data.numberPeople}"/>
                        <small class="error" id="confirmMessage">Please enter a valid number.</small>
                    </div> 
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <label>Reservation date</label>
                        <input type="text" name ="indReservationDateTime" readonly="readonly" value ="${data.indReservationDateTime}"/>
                    </div>                                        
                </div>

                <div class ="row">
                    <div class="large-3 columns">
                        <label>Hour(0~23)</label>
                        <input type="hidden" id="time" value ="${data.indReservationDateTime.getHours()}"/>

                        <select name="time" id="timeDropdown">
                            <option value="0">0am</option>
                            <option value="1">1am</option>
                            <option value="2">2am</option>
                            <option value="3">3am</option>
                            <option value="4">4am</option>
                            <option value="5">5am</option>
                            <option value="6">6am</option>
                            <option value="7">7am</option>
                            <option value="8">8am</option>
                            <option value="9">9am</option>
                            <option value="10">10am</option>
                            <option value="11">11am</option>
                            <option value="12">12pm</option>
                            <option value="13">1pm</option>
                            <option value="14">2pm</option>
                            <option value="15">3pm</option>
                            <option value="16">4pm</option>
                            <option value="17">5pm</option>
                            <option value="18">6pm</option>
                            <option value="19">7pm</option>
                            <option value="20">8pm</option>
                            <option value="21">9pm</option>
                            <option value="22">10pm</option>
                            <option value="23">11pm</option>

                        </select>
                    </div>   

                    <div class="large-3 columns">
                        <label>Date</label>
                        <input type="hidden" id="date" value ="${data.indReservationDateTime.getDate()}"/>
                        <select name="date" id="dateDropdown">
                            <option value="0">Day</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                        </select>
                    </div>   

                    <div class="large-3 columns">
                        <label>Month</label>
                        <input type="hidden" id="month" value ="${data.indReservationDateTime.getMonth()+1}"/>
                        <select name="month" id="monthDropdown">

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

                    <div class="large-3 columns">
                        <label>Year</label>
                        <input type="hidden" id="year" value ="${data.indReservationDateTime.getYear()+1900}"/>
                        <select name="year" id="yearDropdown">

                            <option value="2013">2013</option>
                            <option value="2014">2014</option>
                        </select>
                    </div>   
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <label>Reservation email</label>
                        <input required type ="email" name ="email" value ="${data.email}"/>
                        <small class="error" id="confirmMessage">Please enter a valid e-mail.</small>
                    </div>  
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <label>Reservation notes</label>
                        <input type ="text" name ="notes" value ="${data.notes}"/>
                    </div>  
                </div>

                <input type="hidden" name="restId" value="${data.restaurant.restId}"/>
                <input type="hidden" name="booking" value="false"/>

                <div class="row">
                    <div class="large-12 columns">
                        <input type="submit" class="small button" value="Confirm">

                    </div>
                </div>
            </fieldset>

        </form>

        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>

        <script>
            function checkInfo() {
                console.log("********in check function******");
                // Console.log("********in check information function******");
                //Store the password field objects into variables ...
                var title = document.getElementById('title').value;
                var time = document.getElementById('time').value;
                var year = document.getElementById('year').value;
                var month = document.getElementById('month').value;
                var date = document.getElementById('date').value;




                console.log(title);
                //look for security question
                for (var i = 0; i < 3; i++) {
                    console.log("inside loop");

                    if (document.getElementById("titleDropdown").options[i].value == title) {

                        document.getElementById("titleDropdown").options[i].selected = true;
                        console.log(document.getElementById("titleDropdown").options[i]);
                    }
                }
                //look for hour

                for (var i = 0; i < 24; i++) {
                    console.log("inside loop");

                    if (document.getElementById("timeDropdown").options[i].value == time) {

                        document.getElementById("timeDropdown").options[i].selected = true;
                        console.log(document.getElementById("timeDropdown").options[i]);
                    }
                }


                // look for year
                for (var i = 0; i < 2; i++) {
                    console.log("inside loop");

                    if (document.getElementById("yearDropdown").options[i].value == year) {

                        document.getElementById("yearDropdown").options[i].selected = true;
                        console.log(document.getElementById("yearDropdown").options[i]);
                    }
                }
                // look for month
                for (var i = 0; i < 12; i++) {
                    console.log("inside loop");

                    if (document.getElementById("monthDropdown").options[i].value == month) {

                        document.getElementById("monthDropdown").options[i].selected = true;
                        console.log(document.getElementById("monthDropdown").options[i]);
                    }
                }
                // look for day
                for (var i = 0; i < 31; i++) {
                    console.log("inside loop");

                    if (document.getElementById("dateDropdown").options[i].value == date) {

                        document.getElementById("dateDropdown").options[i].selected = true;

                        console.log(document.getElementById("dateDropdown").options[i]);
                    }
                }


            }


        </script>

    </body>
</html>
