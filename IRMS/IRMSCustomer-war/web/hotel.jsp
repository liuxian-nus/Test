<%-- 
    Document   : home
    Created on : Sep 16, 2013, 2:22:54 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(document).ready(function() {
                $("#reg-form").submit(function() {
                    if ($("#input-name").val().length < 1) {
                        $("#input-name").addClass("error");
                    }
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/IRMSCustomer-war/js/bootstrap-datepicker.js"></script>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>



            <div class="section-container tabs" data-section="tabs">
                <!--first section: orchard hotel -->
                <section class="active">
                    <p class="title" data-section-title><a href="#"><strong>Orchard Hotel </strong></a></p>
                    <div class="content" data-section-content>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Orchard Suite</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_01.png">
                                    </a>
                                    <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">
                                <h5><a href="#" style="color:#4d4d4d">Orchard Suite</a></h5>
                                <p style="color:#4d4d4d">Perched on the top floor of Orchard Hotel, the Orchard Suite is the epitome of luxury and exclusivity. 
                                    Set against the best view point of the island, guests can enjoy this stately suite with its spacious living
                                    and dining areas and a fully serviced open pantry that offers the ultimate in comfort and luxury. The adjacent media room 
                                    provides a cozy and more casual relaxation zone that comes complete with top of the line audio visual equipment for an 
                                    unparalleled home entertainment experience. The suite’s two majestic bedrooms each feature direct access to a spacious dressing
                                    room and vanity area, walk in wardrobes and oversized ensuite bathrooms.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe Suite</strong></h5>

                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_02.png">
                                    </a>
                                    <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe Suite</a></h5>
                                <p style="color:#4d4d4d">Our one bedroom Deluxe Suites feature a luxurious king-sized bed, spacious formal living and dining area
                                    as well as an open pantry. Decked out in delicately muted colours to create a warm, homely feel, and a bathroom that features
                                    both a circular shower and a large bath, Orchard Hotel’s Deluxe Suites elevate the hotel experience to an entirely new level.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_03.png">
                                    </a>
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe</a></h5>
                                <p style="color:#4d4d4d">Michael Graves’ signature is firmly stamped in all our Deluxe Rooms, from the wash basin and the shower 
                                    stall to the gorgeous furnishings and art pieces that adorn the walls. In the bathrooms, the classic black and white marble
                                    flooring, as well as the flower-motif mosaic wall is a sight to behold.Designed in delicately muted colours for a warm and cosy
                                    feel, each room comes with a pull-out writing desk, a hidden vanity mirror, and ample storage space for personal effects.</p>

                            </div>
                        </div>
                    </div>
                </section>
                <!--second section: marina hotel -->
                <section>
                    <p class="title" data-section-title><a href="#"><strong>Marina Hotel </strong></a></p>
                    <div class="content" data-section-content>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Chairman Suite</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_01.png">
                                    </a>
                                    <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Chairman Suite</a></h5>
                                <p style="color:#4d4d4d">The Chairman Suites offer panoramic views of the resort 
                                    and its multitude attractions. Decorated in a soothing palette of silver and ivory, the suite’s exquisitely designed interior 
                                    balances contemporary decor with delicate oriental touches. Guests can relax in the spacious formal living and dining areas 
                                    with an open concept pantry or proceed to the entertainment room decked out with top of the line audio visual equipment 
                                    providing unrivalled home entertainment. Boasting a dramatic champagne toned ceiling with silver leaf motifs, the master 
                                    bedroom opens up to an adjacent oversized ensuite bathroom offering stunning views of the resort.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe Suite</strong></h5>

                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_02.png">
                                    </a>
                                    <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe Suite</a></h5>
                                <p style="color:#4d4d4d">Our one bedroom Deluxe Suites feature a luxurious king-sized bed, spacious formal living and 
                                    dining area as well as an open pantry. Decked out in delicately muted colours to create a warm, homely feel, and a bathroom 
                                    that features both a circular shower and a large bath, Marina Hotel’s Deluxe Suites elevate the hotel experience to an 
                                    entirely new level.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_03.png">
                                    </a>
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe</a></h5>
                                <p style="color:#4d4d4d">Michael Graves’ signature is firmly stamped in all our Deluxe Rooms, from the wash basin and the shower 
                                    stall to the gorgeous furnishings and art pieces that adorn the walls. In the bathrooms, the classic black and white marble
                                    flooring, as well as the flower-motif mosaic wall is a sight to behold.Designed in delicately muted colours for a warm and cosy
                                    feel, each room comes with a pull-out writing desk, a hidden vanity mirror, and ample storage space for personal effects.</p>

                            </div>
                        </div>
                    </div>
                </section>
                <!--third section: beachview hotel -->
                <section>
                    <p class="title" data-section-title><a href="#"><strong>BeachView Hotel </strong></a></p>
                    <div class="content" data-section-content>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe Suite</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_01.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_01.png">
                                    </a>
                                    <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe Suite</a></h5>
                                <p style="color:#4d4d4d">Our one bedroom Deluxe Suites feature a luxurious king-sized bed, spacious formal living and dining area 
                                    as well as an open pantry. Decked out in delicately muted colours to create a warm, homely feel, and a bathroom that features
                                    both a circular shower and a large bath, BeachView Hotel’s Deluxe Suites elevate the hotel experience to an entirely new level.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Deluxe</strong></h5>

                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_02.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_02.png">
                                    </a>
                                    <!--<p>Come and get yourself indulged in the fabulous and sumptuous variety of French cuisine featured by a vibrant combination of contemporary culinary technique with innovative twist</p>-->
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Deluxe</a></h5>
                                <p style="color:#4d4d4d">Michael Graves’ signature is firmly stamped in all our Deluxe Rooms, from the wash basin and the shower 
                                    stall to the gorgeous furnishings and art pieces that adorn the walls. In the bathrooms, the classic black and white marble
                                    flooring, as well as the flower-motif mosaic wall is a sight to behold.Designed in delicately muted colours for a warm and cosy
                                    feel, each room comes with a pull-out writing desk, a hidden vanity mirror, and ample storage space for personal effects.</p>

                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <div class="panel">
                                    <h5><strong>Superior</strong></h5>
                                    <a class="th radius" href="/IRMSCustomer-war/images/gallery/food_03.png">
                                        <img src="/IRMSCustomer-war/images/gallery/food_03.png">
                                    </a>
                                </div>
                            </div>
                            <div class="large-8 columns">

                                <h5><a href="#" style="color:#4d4d4d">Superior</a></h5>
                                <p style="color:#4d4d4d"></p>

                            </div>
                        </div>
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
