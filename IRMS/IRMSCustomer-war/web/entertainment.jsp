<%-- 
    Document   : entertainment
    Created on : Sep 19, 2013, 3:11:57 PM
    Author     : lionetdd
    Example for Disable a list of dates;
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:forEach items="${shows}" var="show">            
            <div class="panel">
                <div class="row">
                    <div class="large-4 columns">
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="${show.imagePath}" width="100" height="100">
                        </a>

                    </div>
                    <div class="large-6 columns">
                        <h5><strong>${show.showName}</strong></h5>
                        <p>
                            ${show.showDescription}
                        </p>
                    </div>
                    <div class="large-2 columns">
                        <form action="entertainmentSchedule">
                            <input class="button" type="submit" value ="Buy Now"/>
                            <input type="hidden" name="showId" value="${show.showId}"/>
                        </form> 
                    </div>
                </div>
            </div>

            <br>             
        </c:forEach>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
