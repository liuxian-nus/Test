<%-- 
    Document   : ticketBookingConfirmation
    Created on : 30-Oct-2013, 17:19:16
    Author     : Jieqiong
--%>

<%@page import="CRMS.entity.CouponEntity"%>
<%@page import="CRMS.session.GenerateBarcodeSessionBean"%>
<%@page import="ATMS.entity.TicketPurchaseEntity"%>
<%@page import="ATMS.entity.AttractionEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    AttractionEntity attr=(AttractionEntity)session.getAttribute("attr");
    TicketPurchaseEntity tp=(TicketPurchaseEntity)session.getAttribute("tp");
    String tpIdString=String.valueOf(tp.getTpId());
    GenerateBarcodeSessionBean generateBarcodeSessionBean=new GenerateBarcodeSessionBean();
    tpIdString=generateBarcodeSessionBean.makeToSevenDigit(tpIdString);
    String hrefString="/IRMSCustomer-war/images/attractionTicket/"+tpIdString+".jpg";
    System.out.println(hrefString);
    CouponEntity coupon=(CouponEntity)session.getAttribute("coupon");
    String couponInfo="";
    if(coupon!=null){
        couponInfo+="You have used the coupon "+coupon.getCouponId()+" to make the purchase";
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
                    <li><a href="#" class="button small secondary"><strong>View ticket information ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small" disabled><strong>Confirm your payment ></strong></a></li>
                </ul>
            </div>
        <h1>Ticket Booking Confirmation</h1>
        <div class="panel">
            <h6>You have successfully purchased the ticket.</h6>
            <h6>The ticket information has been sent to your email.</h6>
            <h6>Thanks for purchasing ticket at <%=attr.getAttrName()%> in Coral Island Resort.</h6>
            <br>
            <h6><%=couponInfo%></h6>    
        </div>
                                
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
