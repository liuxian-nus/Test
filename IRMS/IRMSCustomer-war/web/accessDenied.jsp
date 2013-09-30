<%-- 
    Document   : accessDenied
    Created on : 29-Sep-2013, 20:57:05
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>You have not logged in yet</h1>
        <p>Please log in from <a href="member">here.</a></p>
        <p>If you are not a member, please register from <a href="memberRegister">here.</a></p>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
