<%-- 
    Document   : restaurantSearch
    Created on : Sep 17, 2013, 2:13:03 AM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Search</title>
        <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/jquery-latest.js"></script> 
        <script type="text/javascript" src="/IRMSCustomer-war/js/jquery.tablesorter.js"></script>
        <link rel="stylesheet" href="/IRMSCustomer-war/css/style.css" type="text/css" media="screen" /> 
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.min.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.css" type="text/css" media="screen" />
        <link href="/IRMSCustomer-war/css/templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/IRMSCustomer-war/css/ddsmoothmenu.css" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/normalize.css" type="text/css" media="screen" /> 
        <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
 


        <script language="javascript" type="text/javascript">
            function clearText(field) {

                if (field.defaultValue == field.value)
                    field.value = '';
                else if (field.value == '')
                    field.value = field.defaultValue;

            }
        </script>
        <script type="text/javascript">

            ddsmoothmenu.init({
                mainmenuid: "templatemo_menu", //menu DIV id
                orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
                classname: 'ddsmoothmenu', //class added to menu's outer DIV
                //customtheme: ["#1c5a80", "#18374a"],
                contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
            })

        </script>

    </head>
    <body>
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


        <jsp:include page="header.jsp"></jsp:include>
            <h4>Restaurant Search Result</h4>

            <table class="tablesorter">

                <thead>
                <th width="200">Restaurant</td>
                <th width="200">Resort World Area</td>
                <th width="200">Type of Restaurant</td>
                <th width="200">Cuisine</td>
                <th width="200">Make Reservation</th>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="data">
                <tr>
                    <td width="200">&nbsp${data.restName}</td>
                    <td width="200">&nbsp${data.restNeighbourhood}</td>
                    <td width="200">&nbsp${data.restTypeOfPlace}</td>
                    <td width="200">&nbsp${data.restCuisine}</td>
                    <td width="200">
                        <br>
                        <form action="restaurantBook"><input class="small button" type="submit" value ="Make Reservation"/>
                            <input type="hidden" name="restId" value="${data.restId}"/>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
