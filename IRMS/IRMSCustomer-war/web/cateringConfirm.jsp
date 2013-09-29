<%-- 
    Document   : cateringConfirm
    Created on : Sep 26, 2013, 9:10:58 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
        <title>Make your group ordering reservation</title>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
            <h1>Group catering order reservation: Please complete the form below</h1>
            <form action="cateringCheck" method="POST">
                <fieldset>
                    <legend>Place catering order</legend>
                    <p>${message}</p>
                    <div class="row">
                        <div class="small-2 columns">
                            <select name="date" class="customDropdown">
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
                        <div class="small-3 columns">
                            <select name="month" id="button dropdown">
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

                        <div class="small-2 columns">
                            <select name="year" id="customDropdown">
                                <option value="0">Year</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                            </select>
                        </div>

                        <div class="small-3 columns">
                            <select name="time" id="button dropdown">
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
                                <option value="12">12am</option>
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
                                <option value="24">12pm</option>

                            </select>
                        </div>
                    </div>
                    <br>
                    
                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Number of People</label>
                        </div>
                        <div class="small-10 columns">
                       
                            <select name="numberPeople" id="button dropdown">
     
                                <option value="50">50 People</option>
                                <option value="60">60 People</option>
                                <option value="70">70 People</option>
                                <option value="80">80 People</option>
                                <option value="90">90 People</option>
                                <option value="100">100 People</option>
                                <option value="110">110 People</option>
                                <option value="120">120 People</option>
                                <option value="130">130 People</option>
                                <option value="140">140 People</option>
                                <option value="150">150 People</option>
                                <option value="160">160 People</option>
                                <option value="170">170 People</option>
                                <option value="180">180 People</option>
                                <option value="190">190 People</option>
                                <option value="200">200 People</option>
                                <option value="250">250 People</option>
                                <option value="300">300 People</option>
                                <option value="350">350 People</option>
                                <option value="400">400 People</option>
                            </select>
                        </div>
                    </div> 
                    
                     <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Title</label>
                        </div>
                        <div class="small-3 columns">
                            <select name="title" id="customDropdown">
                                <option value="Mr.">Mr.</option>
                                <option value="Miss">Miss</option>
                                <option value="Mrs.">Mrs.</option>
                            </select>
                        </div>
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Name</label>
                        </div>
                        <div class="small-5 columns">
                            <input id="input-name" type="text" placeholder="name" name="name">
                        </div>

                    </div>
                    
                     <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>E-mail</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" placeholder="email" name="email">
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Mobile phone</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" name="mobile"placeholder="mobile phone number">
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Notes</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" name="notes" placeholder="additional notes">
                            </div>
                        </div>
                    </div>
                    
                    <div clas="row">
                        <div class="large-12 columns">
                            <input type="submit" class="small button" value="Confirm">
                        </div>
                    </div>
                    
                    
                    <input type="hidden" name="orders" value="${data}"/> 
                    <input type="hidden" name="booking" value="true"/>
                
                </fieldset>
            </form>
            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
