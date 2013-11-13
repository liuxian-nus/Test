<%-- 
    Document   : eventSearchVenue
    Created on : Oct 8, 2013, 11:43:38 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Research Result</title>
           <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
  
        <script type="text/javascript" src="/IRMSCustomer-war/js/jquery.tablesorter.js"></script>
        <link rel="stylesheet" href="/IRMSCustomer-war/css/style.css" type="text/css" media="screen" /> 
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.min.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.css" type="text/css" media="screen" />
        <link href="/IRMSCustomer-war/css/templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/IRMSCustomer-war/css/ddsmoothmenu.css" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/normalize.css" type="text/css" media="screen" /> 
        <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
 
        <link rel="stylesheet" href="/IRMSCustomer-war/css/style.css" type="text/css" media="screen" /> 
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.min.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.css" type="text/css" media="screen" />
        <link href="/IRMSCustomer-war/css/templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/IRMSCustomer-war/css/ddsmoothmenu.css" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/normalize.css" type="text/css" media="screen" /> 
        <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
           <script type='text/javascript'>

            $(document).ready(function() {
                $("table").tablesorter({
                    headers: {
                        // assign the secound column (we start counting zero) 
                        4: {
                            // disable it by setting the property sorter to false 
                            sorter: false
                        },
                        // assign the third column (we start counting zero) 

                    }
                });
            });
        </script>

        <table class="tablesorter">
                
                <thead>
                <th width="200">Venue </td>
            <th width="200">Capacity</td>
                <th width="200">Make Reservation</th>
                <th width="200"></th>
            
                </thead>
                <tbody>
        <c:forEach items="${data}" var="data">
            <tr>
                <td width="200">&nbsp${data.venueName}</td>
                <td width="200">&nbsp${data.venueCapacity}</td>
                <td width="200">
                    <c:forEach items="${data.venueFunction}" var="function">
                        <p>${function.functionName}</p>
                    </c:forEach>
                    </td>
                <td width="200">
                    <br>
                    <form action="eventVenueBook"><input class="small button" type="submit" value ="Make Reservation"/>
                        <input type="hidden" name="venueId" value="${data.venueId}"/>
                    </form> 
                </td>
                
            </tr>
        </c:forEach>
    </tbody>
</table>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
