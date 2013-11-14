<%-- 
    Document   : customerPromotionView
    Created on : Nov 14, 2013, 2:09:06 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
       
        <div class="row">
             <strong> Print the following promotion and enjoy your staying at Coral Island Resort: </strong>
            <div class="large-6 columns">
               
                <br>
                <div class="panel"> 
                    <img src="{promotion.imagePath}"/>
                    

                </div>
                <div>
                    <ul ><li>Promotion Code:${promotion.promotionCode} </li>
                        <li> Valid Between:  ${promotion.promotionStartDate} and ${promotion.promotionEndDate}  </li>
                        <li> At Coral Island Resort ${promotion.promotionDepartment} departments</li>
                        <li> Enjoy ${promotion.discount} per cent discount</li>
                    </ul>
                 
                       Wish you a pleasant stay at Coral Island Resort.
                </div>
          </div>
            <div class="large-6 columns">
                <div class="row">
                <h6><strong>${promotion.promotionTitle}</strong></h6>
                </div>
                <div class="row">
                <h6>${promotion.promotionDescription}</h6>
                </div>
            </div>
         
        </div>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
