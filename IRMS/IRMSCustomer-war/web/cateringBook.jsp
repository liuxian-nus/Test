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
            <style type="text/css">
                form {
                    margin: 0;
                    padding: 0;
                    color: #000000;
                }
            </style>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h5>Catering book</h5>

            <div class="section-container auto" data-section>
                <section>
                    <p class="title" data-section-title><a href="#panel1"><strong>Deluxe Package</strong></a></p>
                    <div class="content" data-section-content>
                        <form action="cateringCheck">
                            <h6><strong>Please Select Dish for your Deluxe Package</strong><h6>
                                    <p>Deluxe Package $13.99 ($14.97 w/GST)*/$14.99 ($16.04 w/GST) per Pax for 9 courses (Min:30 Pax)</p>
                                    <div class="row">
                                        <div class="large-6 large columns">
                                            <p><strong>1.Noodle/Rice</strong><p>
                                                <input type="radio" name="dish1" value="1">Thai Pineapple Rice<br>
                                                <input type="radio" name="dish1" value="2">Yong Chow Fried Rice<br>
                                                <input type="radio" name="dish1" value="3">Mee Goreng<br>
                                                <input type="radio" name="dish1" value="4">Fried Hong Kong Mee<br>
                                        </div>
                                        <div class="large-6 large columns">
                                            <p><strong>2. Bean Curd/Vegetable</strong><p>
                                                <input type="radio" name="dish2" value="1">Mushroom Broccoli<br>
                                                <input type="radio" name="dish2" value="2">Mixed Cabbage<br>
                                                <input type="radio" name="dish2" value="3">Braised Bean Curd<br>
                                                <input type="radio" name="dish2" value="4">Neo's Tofu With Broccoli<br>
                                        </div>
                                    </div>
                                                                       <div class="row">
                                        <div class="large-6 large columns">
                                            <p><strong>1.Noodle/Rice</strong><p>
                                                <input type="radio" name="dish1" value="1">Thai Pineapple Rice<br>
                                                <input type="radio" name="dish1" value="2">Yong Chow Fried Rice<br>
                                                <input type="radio" name="dish1" value="3">Mee Goreng<br>
                                                <input type="radio" name="dish1" value="4">Fried Hong Kong Mee<br>
                                        </div>
                                        <div class="large-6 large columns">
                                            <p><strong>2. Bean Curd/Vegetable</strong><p>
                                                <input type="radio" name="dish2" value="1">Mushroom Broccoli<br>
                                                <input type="radio" name="dish2" value="2">Mixed Cabbage<br>
                                                <input type="radio" name="dish2" value="3">Braised Bean Curd<br>
                                                <input type="radio" name="dish2" value="4">Neo's Tofu With Broccoli<br>
                                        </div>
                                    </div>
                                    <input type="submit" value ="book" class="small button"/>
                                <input type="hidden" name="courseNumber" value="9"/>
                                    </form>
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
