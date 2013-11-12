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
        <h1>Ticket Booking Confirmation</h1>
        <h6><%=couponInfo%></h6>                            
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
