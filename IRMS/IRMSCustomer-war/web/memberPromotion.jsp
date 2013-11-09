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
    </head>
    <body>
        <div class="section-container tabs" data-section="vertical-tabs">
            <section>
                <p class="title" data-section-title><a href="#"><strong>Promotions </strong></a></p>
                <div class="content" data-section-content>
                    <p style="color:#4d4d4d">You have ${data.coin} coins accumulated.</p>

                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#" onclick="checkInfo()"><strong>Edit your personal profile</strong></a></p>
                <div class="content" data-section-content>
          </div>                      
            </section>

            <section>
                <p class="title" data-section-title><a href="#"><strong>Reset Password</strong></a></p>
                  <div class="content" data-section-content>
                    <p style="color:#4d4d4d">You have ${data.coin} coins accumulated.</p>

                </div>
            </section>


        </div>


        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 


        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
