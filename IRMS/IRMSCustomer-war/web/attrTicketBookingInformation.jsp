<%-- 
    Document   : ticketBookingConfirm
    Created on : 09-Oct-2013, 23:17:16
    Author     : Jieqiong
--%>

<%@page import="CRMS.entity.CouponEntity"%>
<%@page import="ATMS.entity.AttrExpressPassEntity"%>
<%@page import="ATMS.entity.ExpressPassPurchaseEntity"%>
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
        information+=quantity+" "+ticket.getAttrTicketName()+". \n";
    }
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
    String dateString=sdf.format(tp.getAttrTicketBookDate());
    session.setAttribute("tp",tp);
   // System.out.println("information: "+information);
    
    ExpressPassPurchaseEntity eppurchase=(ExpressPassPurchaseEntity)session.getAttribute("eppurchase");
    String epInfo="";
    if(eppurchase.getAttrEPs().size()==0){
        System.out.println("eppurchase is null");
        epInfo="you have not purchased any express pass";
    }
    else{
        List<AttrExpressPassEntity> eps=new ArrayList<AttrExpressPassEntity>();
        AttrExpressPassEntity ep=new AttrExpressPassEntity();
        eps=eppurchase.getAttrEPs();
        List<Integer> epquantities=eppurchase.getEpQuantities();
        int epquantity;
        for(int i=0;i<eps.size();i++){
            ep=eps.get(i);
            epquantity=epquantities.get(i);
            epInfo+=epquantity+" "+ep.getAttrEPName()+".\n";
        }
        session.setAttribute("eppurchase", eppurchase);
    }
    CouponEntity coupon=(CouponEntity)session.getAttribute("coupon");
    String couponInfo="";
    if(coupon==null){
        couponInfo="You have not used any coupon";
    }
    else{
        couponInfo="You have used coupon "+coupon.getCouponId()+" to get a "+coupon.getCouponType().getDiscount()+" discount.";
    }
%>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        <title>Attraction at Coral Resort World</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <!--Progress Widget-->
            <div class="row">
                <ul class="button-group round even-4">
                    <li><a href="#" class="button small secondary"><strong>Choose your ticket ></strong></a></li>
                    <li><a href="#" class="button small" disabled><strong>View ticket information ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Confirm your payment ></strong></a></li>
                </ul>
            </div>
        <div class="panel">
            <h4>Here's your booking information:</h4>
            </br>
            <h5>Ticket: </h5>
            <h6><%=information%></h6>          
            <h6>Fee: <%=tp.getAttrTicketFee()%></h6>
            </br>
            <h5>Express pass: </h5>
            <h6><%=epInfo%></h6>
            <h6>Fee: <%=eppurchase.getEpFee()%></h6>
            </br>
            <h5>Coupon: </h5>
            <h6><%=couponInfo%></h6>
            </br>
            <h6>Date: <%=dateString%></h6>
            <h6>Total Fee: <%=tp.getAttrTicketFee()+eppurchase.getEpFee()%></h6>
            
        </div>
            
        <form action="attrTicketBookingPayment">
            <button type="Submit" class="button">Confirm</button>
        </form>
        

        <jsp:include page="footer.jsp"></jsp:include>
        
    </body>
</html>
