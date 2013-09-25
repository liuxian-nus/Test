<%-- 
    Document   : memberInfo
    Created on : Sep 21, 2013, 4:54:20 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h5> Welcome back, dear ${data.memberName}</h5>

        <div class="section-container vertical-tabs" data-section="vertical-tabs">
            <section>
                <p class="title" data-section-title><a href="#"><strong>Edit your personal profile</strong></a></p>
                <div class="content" data-section-content>
                   
                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#"><strong>Member Service</strong></a></p>
                <div class="content" data-section-content>
                    <p>Content of section 2.</p>
                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#"><strong>Section 3</strong></a></p>
                <div class="content" data-section-content>
                    <p>Content of section 3.</p>
                </div>
            </section>
        </div>


        <jsp:include page="footer.jsp"></jsp:include>

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
                    '.js><\/script>')
        </script>
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
