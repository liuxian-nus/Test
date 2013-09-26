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
                        <form action="cateringConfirm">
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
                                                <input type="radio" name="dish2" value="5">Mushroom Broccoli<br>
                                                <input type="radio" name="dish2" value="6">Mixed Cabbage<br>
                                                <input type="radio" name="dish2" value="7">Braised Bean Curd<br>
                                                <input type="radio" name="dish2" value="8">Loh Han Vegetable<br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="large-6 large columns">
                                            <p><strong>3. Fish(White Dory)</strong><p>
                                                <input type="radio" name="dish3" value="9">Sweet & Sour Fish<br>
                                                <input type="radio" name="dish3" value="10">Sze Chuan Fish<br>
                                                <input type="radio" name="dish3" value="11">Lemon Fish<br>
                                                <input type="radio" name="dish3" value="12">Cereal Fish<br>
                                        </div>
                                        <div class="large-6 large columns">
                                            <p><strong>4. Chicken</strong><p>
                                                <input type="radio" name="dish4" value="13">Curry Chicken<br>
                                                <input type="radio" name="dish4" value="14">Honey Chicken<br>
                                                <input type="radio" name="dish4" value="15">Thai Mango Chicken<br>
                                                <input type="radio" name="dish4" value="16">Sweet & Sour Chicken<br>
                                        </div>
                                                                           
                                                                                                          
                                    </div>
                                    <div class ="row">
                                        <div class="large-6 large columns">
                                            <p><strong>5. Prawn(S40-45)</strong><p>
                                                <input type="radio" name="dish5" value="17">Tempura Prawn<br>
                                                <input type="radio" name="dish5" value="18">Sze Chuan Prawn<br>
                                                <input type="radio" name="dish5" value="19">Thai Style Prawn<br>
                                                <input type="radio" name="dish5" value="20">Cereal Prawn <br>
                                        </div>
                                        <div class="large-6 large columns">
                                            <p><strong>6. Deep Fried</strong><p>
                                                <input type="radio" name="dish6" value="21">Sotong Ball<br>
                                                <input type="radio" name="dish6" value="22">Fish Ball<br>
                                                <input type="radio" name="dish6" value="23">Spring Roll<br>
                                                <input type="radio" name="dish6" value="24">Sotong Yu Tiao<br>
                                        </div>
                                    </div>
                                    
                                    <div class ="row">
                                        <div class="large-6 large columns">
                                            <p><strong>7. Dessert</strong><p>
                                                <input type="radio" name="dish7" value="25">Almond Jelly W Longan<br>
                                                <input type="radio" name="dish7" value="26">Sago Honeydew<br>
                                                <input type="radio" name="dish7" value="27">Sea Coconut With Longan<br>
                                                <input type="radio" name="dish7" value="28">Bo Bo Cha Cha  <br>
                                        </div>
                                        <div class="large-6 large columns">
                                            <p><strong>8. Drinks</strong><p>
                                                <input type="radio" name="dish8" value="29">Fruit Punch<br>
                                                <input type="radio" name="dish8" value="30">Lime<br>
                                                <input type="radio" name="dish8" value="31">Orange<br>
                                                <input type="radio" name="dish8" value="32">Blackcurrant<br>
                                        </div>
                                    </div>
                                    
                                    <div class ="row">
                                        <div class="large-6 large columns">
                                            <p><strong>9. Others</strong><p>
                                                <input type="radio" name="dish9" value="33">Mini Custard Puff<br>
                                                <input type="radio" name="dish9" value="34">Mini Chocolate Puff<br>
                                                <input type="radio" name="dish9" value="35">Mini Chocolate Éclair <br>
                                                <input type="radio" name="dish9" value="36">Steamed Siew Mai<br>
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
