<%-- 
    Document   : ticketBooking
    Created on : 09-Oct-2013, 16:04:21
    Author     : Jieqiong
--%>

<%@page import="ATMS.entity.AttrExpressPassEntity"%>
<%@page import="ATMS.entity.AttrTicketEntity"%>
<%@page import="java.util.List"%>
<%@page import="CRMS.entity.MemberEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    MemberEntity member=(MemberEntity)session.getAttribute("member");
    List<AttrTicketEntity> tickets=(List<AttrTicketEntity>)session.getAttribute("tickets");
    List<AttrExpressPassEntity> eps=(List<AttrExpressPassEntity>)session.getAttribute("eps");
%>

<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <script>
                $(document).ready(function() {
                $("#datepicker1").datepicker({
                 minDate:0,
                onClose: function(selectedDate) {
                var minDate = $(this).datepicker('getDate');
                }
                });
                });            
        </script>
        
        <h3>Welcome, ${member.memberName}!</h3>
        <h4>Ticket Booking</h4>
        
        <div class="section-container auto" data-section>
            <section>
                <p class="title" data-section-title><a href="#panel1"><strong>Adventure World</strong></a></p>
                <div class="content" data-section-content>
                    <form action="ticketBookingInformation">
                        <h6><strong>Please Select a Ticket</strong><h6>
                                </br>
                                <div class="row">
                                    <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Space</label>
                                    </div>
                                    <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Adult</label>
                                    </div>
                                    <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Child</label>
                                    </div>
                                    <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Senior</label>
                                    </div>
                                </div>
                                </br>
                      <!--      <div class="row">
                                <div class="large-3 large columns">
                                    <label for="right-label" class="left-align,inline"><strong>Ticket Type</label>
                                </div>
                                <div class="large-3 large columns">                                                           
                                    <p>1-Day Pass, Adult</p>
                                    <p>2-Day Pass, Adult</p>
                                    <p>Annual Pass, Adult<p>                                 
                                </div>
                                <div class="large-3 large columns">                                
                                    <p>$<%=tickets.get(0).getAttrTicketPrice()%></p>
                                    <p>$<%=tickets.get(1).getAttrTicketPrice()%></p>
                                    <p>$<%=tickets.get(2).getAttrTicketPrice()%></p>
                                </div>
                                <div class="large-3 large columns">                                
                                    <select required name="quantity1" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    <p></p>
                                    <select required name="quantity2" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    <p></p>
                                    <select required name="quantity3" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    
                                </div>
                            </div>
                                
                                <p></p>
                                
                                </br>-->
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>One-day Ticket</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=tickets.get(0).getAttrTicketPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="quantity1" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Two-day Ticket</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=tickets.get(1).getAttrTicketPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="quantity2" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Annual Ticket</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=tickets.get(2).getAttrTicketPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="quantity3" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                    </br>

                                
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>One-day Express Pass</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=eps.get(0).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq1" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Two-day Express Pass</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=eps.get(1).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq2" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                     <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Annual Express Pass</label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline"><strong>$<%=eps.get(2).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq3" id="customDropdown">
                                        <option value="0">--Select--</option>
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
                                    </select>
                                    </div>
                                </div>
                                
                                
                                <p></p>
                            <div class="row">
                                <div class="large-3 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Date</label>
                                </div>
                                <div class="large-3 large columns">
                                    <select required name="dateDay" id="customDropdown">
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
                                    <select required name="dateMonth" id="button dropdown">
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
                                <div class="small-3 columns">
                                    <select required name="dateYear" id="customDropdown">
                                        <option value="2013">2013</option>
                                        <option value="2013">2014</option>
                                    </select>
                                        
                                    
                                </div>
                                
                         <!--       <div class="row">                               
                                    <div class="large-3 columns"> 
                                            <label for="right-label" class="left-align,inline"><strong>Date</label>
                                    </div>
                                    <div class="large-9 columns">
                                        <input type="text" id="datepicker1" name="startDate" id="startDate"/>
                                    </div>
                                </div>-->
                         
                                     
            <input type="hidden" name="attrId" value="OT"/>
                      
                                <div class="row">
                                    <input type="submit" class="small button" value="Submit">
                                </div>
                                
                                
                                
                    </form>
                </div>
            </section>
     
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
                                    document.write('<script src=' +
                                            ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
                                            '.js><\/script>')
        </script>
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
