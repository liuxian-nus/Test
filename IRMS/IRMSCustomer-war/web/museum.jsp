<%-- 
    Document   : museum
    Created on : 04-Nov-2013, 00:40:31
    Author     : Jieqiong
--%>

<%@page import="ATMS.entity.AttrTicketEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<AttrTicketEntity> tickets=(List<AttrTicketEntity>)session.getAttribute("tickets");
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>CIR Attractions</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>the Art Museum tickets</h1>
        <p><a name="top"></a></p>
        <ul>
            <li>
                <h3>the Art Museum Tickets</h3>
                <p>Purchase your day passes to the Art Museum online and save time queuing when you arrive at the museum.<br />
                    We also have the annual ticket<br />
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
                            <td style="text-align: center;">$<%=tickets.get(18).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(20).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(22).getAttrTicketPrice()%></td>
                        </tr>
                        <tr>
                            <td class="subhead" style="text-align: left;">Annual Pass<br />
                            <td style="text-align: center;">$<%=tickets.get(19).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(21).getAttrTicketPrice()%></td>
                            <td style="text-align: center;">$<%=tickets.get(23).getAttrTicketPrice()%></td>
                        </tr>
                    </tbody>
                </table>
  <!--              Each regular priced day pass has a validity period of 180 days from the selected date of visit.<br />
                For Terms &amp; Conditions, <a href="/language/en-US/Homepage/ThingsToDo/UniversalStudiosSingapore/TicketingTermsConditions">click here</a>.</p>-->
                <table width="100%">
                    <tbody>
                        <tr>
                            <td align="right"><a class="BoxButton" href="attrTicketBooking" target="_blank">Book Now</a></td>
                        </tr>
                    </tbody>
                </table>
        </ul>
        <table width="100%">
            <tbody>
                <tr>
                    <td align="right"><a href="#top">Back to top</a></td>
                </tr>
            </tbody>
        </table>
            

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
