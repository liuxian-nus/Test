<%-- 
    Document   : ticketBookingConfirm
    Created on : 09-Oct-2013, 23:17:16
    Author     : Jieqiong
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ATMS.entity.AttrTicketEntity"%>
<%@page import="ATMS.entity.TicketPurchaseEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    TicketPurchaseEntity tp=(TicketPurchaseEntity)session.getAttribute("tp");
    AttrTicketEntity ticket;
    List<AttrTicketEntity> tkts=new ArrayList<AttrTicketEntity>();
    tkts=tp.getAttrTickets();
    List<Integer> quantities=new ArrayList<Integer>();
    quantities=tp.getAttrTicketQuantities();
    Integer quantity=0;
    System.out.println("tkts size: "+tkts.size());
    System.out.println("quantities size: "+quantities.size());
    String information="";
    for(int i=0;i<tkts.size();i++){
        ticket=tkts.get(i);
        quantity=quantities.get(i);
        information+=quantity+" "+ticket.getAttrTicketName()+", \n";
    }
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
    String dateString=sdf.format(tp.getAttrTicketBookDate());
    session.setAttribute("tp",tp);
   // System.out.println("information: "+information);
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>Attraction at Coral Resort World</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="panel">
            <h4>Here's your booking information:</h4>
            </br>
            <h6>
            <%=information%>
         </h6>
            <h6>Date: <%=dateString%></h6>
            <h6>Fee: <%=tp.getAttrTicketFee()%></h6>
       
        </div>
        <form action="ticketBookingPayment">
            <button type="Submit" class="button">Confirm</button>
        </form>
        

        <jsp:include page="footer.jsp"></jsp:include>
        
    </body>
</html>
