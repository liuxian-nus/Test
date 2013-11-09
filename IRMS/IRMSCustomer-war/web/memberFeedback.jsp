<%-- 
    Document   : memberFeedback
    Created on : Nov 9, 2013, 3:38:55 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Feedback Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
        <h1>Member Feedback!</h1>
          <h3>Welcome, ${member.memberName}!</h3>
         <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
