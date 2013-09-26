<%-- 
    Document   : entertainment
    Created on : Sep 19, 2013, 3:11:57 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        <script type="text/javascript" src="/IRMSCustomer-war/js/calendar.js" ></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Entertainment!</h1>
        <input name="en_date" type="text" id="en_date" onclick="new Calendar(null, null, 1).show(this);" size="10" maxlength="10" readonly="readonly">
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
