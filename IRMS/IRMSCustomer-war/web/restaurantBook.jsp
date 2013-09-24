<%-- 
    Document   : restaurantBook
    Created on : Sep 24, 2013, 12:00:00 AM
    Author     : Diana Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Restaurant book!</h1>
            <form action="restaurantCheck" method="POST">
                <fieldset>
                    <legend>Book Restaurant</legend>
                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Date</label>
                        </div>

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

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Number of People</label>
                        </div>
                        <div class="small-10 columns">
                            <input id="input-name" type="text" placeholder="restaurant name" name="numberPeople">
                            <select name="dateMonth" id="button dropdown">
     
                                <option value="1">1 People</option>
                                <option value="2">2 People</option>
                                <option value="3">3 People</option>
                                <option value="4">4 People</option>
                                <option value="5">5 People</option>
                                <option value="6">6 People</option>
                                <option value="7">7 People</option>
                                <option value="8">8 People</option>
                                <option value="9">9 People</option>
                                <option value="10">10 People</option>
                                <option value="11">11 People</option>
                                <option value="12">12 People</option>
                                <option value="13">13 People</option>
                                <option value="14">14 People</option>
                                <option value="15">15 People</option>
                                <option value="16">16 People</option>
                                <option value="17">17 People</option>
                                <option value="18">18 People</option>
                                <option value="19">19 People</option>
                                <option value="20">20 People</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Title</label>
                        </div>
                        <div class="small-3 columns">
                            <select name="title" id="customDropdown">
                                <option value="0">Mr.</option>
                                <option value="1">Miss</option>
                                <option value="2">Mrs.</option>
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
                                <input type="text" placeholder="e-mail" name="e-mail">
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
                            <input type="submit" class="small button" value="Search">
                        </div>
                    </div>


                </fieldset>
            </form>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
