<%-- 
    Document   : cateringBook
    Created on : Sep 26, 2013, 8:27:00 PM
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
        <h5>Catering book</h5>

        <div class="section-container auto" data-section>
            <section>
                <p class="title" data-section-title><a href="#panel1">Section 1</a></p>
                <div class="content" data-section-content>
                    <p>Content of section 1.</p>
                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#panel2">Section 2</a></p>
                <div class="content" data-section-content>
                    <p>Content of section 2.</p>
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
