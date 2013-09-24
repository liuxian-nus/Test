<%-- 
    Document   : memberInfo
    Created on : Sep 21, 2013, 4:54:20 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h5> Welcome back, dear ${data.memberName}</h5>
        
         
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
