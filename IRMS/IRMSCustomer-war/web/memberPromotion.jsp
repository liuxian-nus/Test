<%-- 
    Document   : memberPromotion
    Created on : Nov 9, 2013, 4:33:18 PM
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
        <title>Promotion Page</title>
         <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
       <c:forEach items="${allPromotions}" var="promotion">            
            <div class="panel">
                <div class="row">
                    <div class="large-4 columns">
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="${promotion.imagePath}" width="100" height="100">
                        </a>

                    </div>
                    <div class="large-6 columns">
                        <h5><strong>${promotion.promotionTitle}</strong></h5>
                        <p>
                        <h7><strong>Promotion Details: </strong></h7>${promotion.promotionDescription}
                        </p>
                        
                        <p>
                        <h7><strong>Promotion Start/End Date: </strong></h7><fmt:formatDate type="date" pattern="dd MMM yyyy" 
                                                        value="${promotion.promotionStartDate}" /> <h7>-</h7> <fmt:formatDate type="date" pattern="dd MMM yyyy" 
                                                        value="${promotion.promotionEndDate}" />
                        </p>
                    </div>
                    <div class="large-2 columns">
                        <form action="entertainmentSchedule">
                            <input class="button" type="submit" value ="Buy Now"/>
                            <input type="hidden" name="promotionId" value="${promotion.promotionId}"/>
                        </form> 
                    </div>
                </div>
            </div>

            <br>             
        </c:forEach>
        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 


        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
         <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
