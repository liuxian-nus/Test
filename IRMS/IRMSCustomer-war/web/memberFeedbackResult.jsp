<%-- 
    Document   : memberFeedbackResult
    Created on : Nov 10, 2013, 12:48:58 AM
    Author     : Diana Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Feedback Result Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Member Feedback Result</h1>
        <h3>${member.memberName}, Thank you for your feedback!</h3>
        <h3>We will get back to you to make us serve you better!</h3>
        <h3>Please Support us more by liking us on Facebook /Following us on Twitter! Thank you!</h3>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
