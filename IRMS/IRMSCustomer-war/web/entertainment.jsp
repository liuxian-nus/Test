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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
       
            <script>
                $(document).ready(function() {
                var array = ["2013-10-15", "2013-11-15", "2013-10-30"];

                $('input').datepicker({
                    beforeShowDay: function(date) {
                        var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
                        return [array.indexOf(string) == -1]
                    }
                });
                });  
            </script>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Entertainment!</h1>
            <input type="text" id="input" name="startDate"/>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
