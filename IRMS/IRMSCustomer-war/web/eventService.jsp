<%-- 
    Document   : eventService
    Created on : Nov 11, 2013, 8:58:34 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <div class="row">
                <div class="section-container vertical-tabs" data-section="vertical-tabs">
                    <section class="active">
                        <p class="title" data-section-title><a href="#">Audio&Video</a></p>
                        <div class="content" data-section-content>
                            <table class="tablesorter">
                                <thead>
                                <th width="200">Serial Number</td>
                                <th width="400">Event Service</td>
                                <th width="200">Unit Price</td>
                                    </thead>
                                <tbody>
                                <c:forEach items="${AVList}" var="data">
                                <tr>
                                    <td width="200">&nbsp${data.serviceId}</td>
                                    <td width="400">&nbsp${data.serviceName}</td>
                                    <td width="200">&nbsp${data.serviceCost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section>
                    <p class="title" data-section-title><a href="#">Floral&Landscaping </a></p>
                    <div class="content" data-section-content>
                        <table class="tablesorter">
                            <thead>
                            <th width="200">Serial Number</td>
                            <th width="400">Event Service</td>
                            <th width="200">Unit Price</td>
                                </thead>
                            <tbody>
                            <c:forEach items="${FLList}" var="data">
                                <tr>
                                    <td width="200">&nbsp${data.serviceId}</td>
                                    <td width="400">&nbsp${data.serviceName}</td>
                                    <td width="200">&nbsp${data.serviceCost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </section>
                 <section>
                    <p class="title" data-section-title><a href="#">Personnel </a></p>
                    <div class="content" data-section-content>
                        <table class="tablesorter">
                            <thead>
                            <th width="200">Serial Number</td>
                            <th width="400">Event Service</td>
                            <th width="200">Unit Price</td>
                                </thead>
                            <tbody>
                            <c:forEach items="${PEList}" var="data">
                                <tr>
                                    <td width="200">&nbsp${data.serviceId}</td>
                                    <td width="400">&nbsp${data.serviceName}</td>
                                    <td width="200">&nbsp${data.serviceCost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
