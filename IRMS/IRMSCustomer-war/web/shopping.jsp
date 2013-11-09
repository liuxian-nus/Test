<%-- 
    Document   : shopping
    Created on : Nov 10, 2013, 12:10:26 AM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="/IRMSCustomer-war/js/jquery-latest.js"></script> 
        <script type="text/javascript" src="/IRMSCustomer-war/js/jquery.tablesorter.js"></script>
        <link rel="stylesheet" href="/IRMSCustomer-war/css/style.css" type="text/css" media="screen" /> 
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.min.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/foundation.css" type="text/css" media="screen" />
        <link href="/IRMSCustomer-war/css/templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/IRMSCustomer-war/css/ddsmoothmenu.css" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="/IRMSCustomer-war/css/normalize.css" type="text/css" media="screen" /> 
        <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>




    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <div class="row">
                <div class="section-container vertical-tabs" data-section="vertical-tabs">
                    <section class="active">
                        <p class="title" data-section-title><a href="#">Accessories</a></p>
                       <div class="content" data-section-content>
                            <c:forEach items="${LingrieList}" var="lingrie">            
                          
                                    <div class="large-6 columns">
                                        <h5><strong>${lingrie.outletName}</strong></h5>
                                    </div>
                                         
                        </c:forEach>

                    </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Banks</a></p>
                        <div class="content" data-section-content>

                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Childrens Fashion</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 2.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Electronics</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">General</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Handbags and Shoes</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Jewelry</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Lifestyle and Gifts</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Lingerie</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Mens Fashion</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Sports Beauty and Wellness</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Watches</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
                    <section>
                        <p class="title" data-section-title><a href="#">Womens Fashion</a></p>
                        <div class="content" data-section-content>
                            <p>Content of section 3.</p>
                        </div>
                    </section>
            </div>

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
