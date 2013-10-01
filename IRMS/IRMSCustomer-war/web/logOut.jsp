<%-- 
    Document   : logOut
    Created on : 01-Oct-2013, 12:18:10
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session.invalidate();
%>

<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h4>You have logged out</h4>
        <h6>To log in, click <a href="member"> here</h6>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
