<%-- 
    Document   : adventureWorld
    Created on : 09-Oct-2013, 15:30:23
    Author     : Jieqiong
--%>

<%@page import="ATMS.entity.AttrExpressPassEntity"%>
<%@page import="ATMS.entity.AttrTicketEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<AttrTicketEntity> tickets=(List<AttrTicketEntity>)session.getAttribute("tickets");
    List<AttrExpressPassEntity> eps=(List<AttrExpressPassEntity>)session.getAttribute("eps");
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Tickets</h1>
        <p><a name="top"></a><a href="#Day_Pass">Adventure World||</a>
            <a href="#ExpressPass">Adventure World Express</a></br></p>
        
        <p><a name="Day_Pass"></a></p>
        
        <ul>
            <li>
                <h2>Adventure World Day Pass</h2>
                <p>Purchase your day passes to Adventure World online and save time queuing when you arrive at the park. It’s quick and easy!<br />
                    We have passes for one day, two days, and a great value Annual/ Season Pass that treats you to a whole year of cutting-edge rides, shows and attractions!<br />
                    <br />
                <table class="styled" cellpadding="0" cellspacing="0" border="1" width="100%">
                    <tbody>
                        <tr>
                            <th class="tableTitle" colspan="4" style="text-align: left;">Tickets</th>
                        </tr>
                        <tr>
                            <th class="ColumnTitle" style="text-align: left;">Ticket Type&#160;</th>
                            <th class="ColumnTitle" style="text-align: center;">Adult (Ages 13 to 59)</th>
                            <th class="ColumnTitle" style="text-align: center;">Child (Ages 4 to 12)</th>
                            <th class="ColumnTitle" style="text-align: center;">Senior (Ages 60 and above)</th>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">One Day Pass</td>
                            <td style="text-align: center;">$<%=tickets.get(0).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(3).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(6).getAttrTicketPrice()%></td>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">Two Day Pass<br />
                                <span style="font-size: smaller;">(Two consecutive days)</span></td>
                            <td style="text-align: center;">$<%=tickets.get(1).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(4).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(7).getAttrTicketPrice()%></td>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">Annual Pass<br />
                            <td style="text-align: center;">$<%=tickets.get(2).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(5).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(8).getAttrTicketPrice()%></td>
                        </tr>
                    </tbody>
                </table>
  <!--              Each regular priced day pass has a validity period of 180 days from the selected date of visit.<br />
                For Terms &amp; Conditions, <a href="/language/en-US/Homepage/ThingsToDo/UniversalStudiosSingapore/TicketingTermsConditions">click here</a>.</p>-->
                <table width="100%">
                    <tbody>
                        <tr>
                            <td align="right"><a class="BoxButton" href="ticketBooking" target="_blank">Book Now</a></td>
                        </tr>
                    </tbody>
                </table>
                <a name="ExpressPass"></a></li>
        </ul>
                            <table width="100%">
                                <tbody>
                                    <tr>
                                        <td align="right"><a href="#top">Back to top</a></td>
                                    </tr>
                                </tbody>
                            </table>
                            
           
        <ul>
            <li>
                <h2>Adventure World Express Pass</h2>
                <p>Express pass provides you a short cut to the rides. No more long queue!.<br />
                    We have express passes for one day, two days, and a great value Annual/ Season Pass<br />
                    <br />
                <table class="styled" cellpadding="0" cellspacing="0" border="1" width="100%">
                    <tbody>
                        <tr>
                            <th class="tableTitle" colspan="4" style="text-align: left;">Express passes</th>
                        </tr>
                        <tr>
                            <th class="ColumnTitle" style="text-align: left;">Express pass Type&#160;</th>
                            <th class="ColumnTitle" style="text-align: center;">Adult (Ages 13 to 59)</th>
                            <th class="ColumnTitle" style="text-align: center;">Child (Ages 4 to 12)</th>
                            <th class="ColumnTitle" style="text-align: center;">Senior (Ages 60 and above)</th>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">One Day Express Pass</td>
                            <td style="text-align: center;">$<%=eps.get(0).getAttrEPPrice()%></td>
                            <td style="text-align: center;">$55</td>
                            <td style="text-align: center;">$35</td>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">Two Day Pass<br />
                                <span style="font-size: smaller;">(Two consecutive days)</span></td>
                            <td style="text-align: center;">$<%=eps.get(1).getAttrEPPrice()%></td>
                            <td style="text-align: center;">$80</td>
                            <td style="text-align: center;">$50</td>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">Annual Pass<br />
                            <td style="text-align: center;">$<%=eps.get(2).getAttrEPPrice()%></td>
                            <td style="text-align: center;">$80</td>
                            <td style="text-align: center;">$50</td>
                        </tr>
                    </tbody>
                </table>
  <!--              Each regular priced day pass has a validity period of 180 days from the selected date of visit.<br />
                For Terms &amp; Conditions, <a href="/language/en-US/Homepage/ThingsToDo/UniversalStudiosSingapore/TicketingTermsConditions">click here</a>.</p>-->
        </ul>


        
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
