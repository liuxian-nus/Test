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
          <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
          
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

        <script>
                        $(document).ready(function() {
                $("#datepicker1").datepicker({
                 minDate:0,
                onClose: function(selectedDate) {
                var minDate = $(this).datepicker('getDate');
                var newMin = new Date(minDate.setDate(minDate.getDate() + 1));            
                }
                });
                });            
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <!--Progress Widget-->
            <div class="row">
                <ul class="button-group round even-4">
                    <li><a href="#" class="button small" disabled><strong>Choose your ticket ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>View ticket information ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Confirm your payment ></strong></a></li>
                </ul>
            </div>
        <h3>Welcome ${member.memberName}!</h3>
        <h4>Ticket Booking</h4>
        
        <div class="section-container auto" data-section>
            <section>
                <p class="title" data-section-title><a href="#panel1"><strong>Adventure World</strong></a></p>
                <div class="content" data-section-content>
                    <form action="attrTicketBookingInformation" method="POST">
                         <h6><strong>Please Select a Ticket</strong></h6>
                         </br>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Adult</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Child</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Senior</strong></label>
                             </div>
                         </div>
                         </br>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>One-day Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(0).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(3).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity4" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(6).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity7" id="customDropdown">
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
                                     <label for="right-label" class="left-align,inline"><strong>Two-day Ticket</strong></label>
                                 </div>
                                 <div class="large-1 columns">
                                     <label for="right-label" class="left-align,inline">$<%=tickets.get(1).getAttrTicketPrice()%></label>
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
                                 <div class="large-1 columns">
                                     <label for="right-label" class="left-align,inline">$<%=tickets.get(4).getAttrTicketPrice()%></label>
                                 </div>
                                 <div class="large-2 columns">
                                     <select required name="quantity5" id="customDropdown">
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
                                 <div class="large-1 columns">
                                     <label for="right-label" class="left-align,inline">$<%=tickets.get(7).getAttrTicketPrice()%></label>
                                 </div>
                                 <div class="large-2 columns">
                                     <select required name="quantity8" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Annual Ticket</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=tickets.get(2).getAttrTicketPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=tickets.get(5).getAttrTicketPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="quantity6" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=tickets.get(8).getAttrTicketPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="quantity9" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>One-day Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(0).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(3).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq4" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(6).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq7" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Two-day Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(1).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(4).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq5" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(7).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq8" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Annual Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(2).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(5).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq6" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(8).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq9" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Date</strong></label>
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
                            </div>
                                
                                <p></p>
                                <div class="row">
                                    <div class="large-3 large columns">
                                        <label for="right-label" class="left-align,inline"><strong>Coupon Code</strong></label>
                                    </div>
                                    <div class="large-3 large columns">
                                        <input type="text" value="0" placeholder="Coupon Code" name="couponId">
                                    </div>
                                    <div class="large-6 large columns"><p>${message}</p></div>
                                </div>
                                <div class="row">
                                    <div class="large-3 columns">
                                        <label for="customDropdown"><strong>Date</strong></label>
                                    </div>
                                    <div class="large-3 columns">
                                        <input type="text" id="datepicker1" name="date" />
                                    </div>
                                    <div class="large-6 columns">                   
                                    </div>
                                </div>
                             
                                 <input type="hidden" name="attrId" value="OT"/>
                                 <div class="row">
                                     <input type="submit" class="small button" value="Submit">
                                 </div> 

                    </form>
                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#panel2"><strong>Horror House</strong></a></p>
                <div class="content" data-section-content>
                <form action="attrTicketBookingInformation" method="POST">
                         <h6><strong>Please Select a Ticket</strong></h6>
                         </br>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Adult</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Child</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Senior</strong></label>
                             </div>
                         </div>
                         </br>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>One-day Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(9).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(12).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity4" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(15).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity7" id="customDropdown">
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
                                 <label for="right-label" class="left-align,inline"><strong>Two-day Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(10).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(13).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity5" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(16).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity8" id="customDropdown">
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
                                 <label for="right-label" class="left-align,inline"><strong>Annual Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(11).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(14).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity6" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(17).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity9" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>One-day Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(9).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(12).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq4" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(15).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq7" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Two-day Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(10).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(13).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq5" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(16).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq8" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Annual Express Pass</strong></label>
                                    </div>
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(11).getAttrEPPrice()%></label>
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(14).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq6" id="customDropdown">
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
                                    <div class="large-1 columns">
                                    <label for="right-label" class="left-align,inline">$<%=eps.get(17).getAttrEPPrice()%></label>
                                    </div>
                                     <div class="large-2 columns">
                                    <select required name="epq9" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Date</strong></label>
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
                            </div>
                                <p></p>
                                <div class="row">
                                    <div class="large-3 large columns">
                                        <label for="right-label" class="left-align,inline"><strong>Coupon Code</strong></label>
                                    </div>
                                    <div class="large-3 large columns">
                                        <input type="text" placeholder="Coupon Code" name="couponId">
                                    </div>
                                    <div class="large-6 large columns"></div>
                                </div>
                             
                             
                             <input type="hidden" name="attrId" value="IT"/>
                             <input type="hidden" name="epq1" value="0"/>
                             <input type="hidden" name="epq2" value="0"/>
                             <input type="hidden" name="epq3" value="0"/>
                                 <div class="row">
                                     <input type="submit" class="small button" value="Submit">
                                 </div>
                             
                    </form>
                            </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#panel3"><strong>Museum</strong></a></p>
                <div class="content" data-section-content>
                    <form action="attrTicketBookingInformation" method="POST">
                         <h6><strong>Please Select a Ticket</strong></h6>
                         </br>
                          <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Adult</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Child</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Senior</strong></label>
                             </div>
                         </div>
                         </br>
                         <p></p>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>One-day Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(18).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(20).getAttrTicketPrice()%></label>
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(22).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity5" id="customDropdown">
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
                                 <label for="right-label" class="left-align,inline"><strong>Annual Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(19).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(21).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity4" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(23).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity6" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Date</strong></label>
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
                            </div>
                            <input type="hidden" name="attrId" value="MU"/>
                            <input type="hidden" name="quantity7" value="0"/>
                            <input type="hidden" name="quantity8" value="0"/>
                            <input type="hidden" name="quantity9" value="0"/>
                             <input type="hidden" name="epq1" value="0"/>
                             <input type="hidden" name="epq2" value="0"/>
                             <input type="hidden" name="epq3" value="0"/>
                             <input type="hidden" name="epq4" value="0"/>
                             <input type="hidden" name="epq5" value="0"/>
                             <input type="hidden" name="epq6" value="0"/>
                             <input type="hidden" name="epq7" value="0"/>
                             <input type="hidden" name="epq8" value="0"/>
                             <input type="hidden" name="epq9" value="0"/>
      
                             <div class="row">
                                 <input type="submit" class="small button" value="Submit">
                             </div>
                    </form>
                </div>

            </section>
            <section>
                <p class="title" data-section-title><a href="#panel4"><strong>Aquarium</strong></a></p>
                <div class="content" data-section-content>
                    <form action="attrTicketBookingInformation" method="POST">
                         <h6><strong>Please Select a Ticket</strong></h6>
                         </br>
                          <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Adult</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Child</strong></label>
                             </div>
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>Senior</strong></label>
                             </div>
                         </div>
                         </br>
                         <p></p>
                         <div class="row">
                             <div class="large-3 columns">
                                 <label for="right-label" class="left-align,inline"><strong>One-day Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(24).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(26).getAttrTicketPrice()%></label>
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(28).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity5" id="customDropdown">
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
                                 <label for="right-label" class="left-align,inline"><strong>Annual Ticket</strong></label>
                             </div>
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(25).getAttrTicketPrice()%></label>
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
                             <div class="large-1 columns">
                                 <label for="right-label" class="left-align,inline">$<%=tickets.get(27).getAttrTicketPrice()%></label>
                             </div>
                             <div class="large-2 columns">
                                 <select required name="quantity4" id="customDropdown">
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
                            <div class="large-1 columns">
                                <label for="right-label" class="left-align,inline">$<%=tickets.get(29).getAttrTicketPrice()%></label>
                            </div>
                            <div class="large-2 columns">
                                <select required name="quantity6" id="customDropdown">
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
                                    <label for="right-label" class="left-align,inline"><strong>Date</strong></label>
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
                            </div>
                            <input type="hidden" name="attrId" value="AQ"/>
                            <input type="hidden" name="quantity7" value="0"/>
                            <input type="hidden" name="quantity8" value="0"/>
                            <input type="hidden" name="quantity9" value="0"/>
                             <input type="hidden" name="epq1" value="0"/>
                             <input type="hidden" name="epq2" value="0"/>
                             <input type="hidden" name="epq3" value="0"/>
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
                ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                '.js><\/script>');
        </script> 
        <script>
        $(document).foundation();    
        </script>
    </body>
</html>
