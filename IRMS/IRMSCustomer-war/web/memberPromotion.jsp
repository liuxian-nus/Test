<%-- 
    Document   : memberPromotion
    Created on : Nov 9, 2013, 4:33:18 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion Page</title>
         <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>
  
        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 


        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
         <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
