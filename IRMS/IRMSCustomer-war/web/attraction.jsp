<%-- 
    Document   : attraction
    Created on : 09-Oct-2013, 16:26:40
    Author     : Jieqiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
         <h1>Attraction</h1>
         <h6><Strong>In Coral Island Resort, we offer fantastic outdoor themepark Adventure World, indoor themepark Horror House, the Art Museum and the UnderWater Aquarium.</Strong></h6>
         <br>
         <div class="row">
            <div class="large-4 columns">
                <div class="panel callout">
                    <a class="th radius" href="/IRMSCustomer-war/images/attraction/OutdoorThemepark.jpg">
                        <img src="/IRMSCustomer-war/images/attraction/OutdoorThemepark.jpg">
                    </a>
                    <!-- <p>We provide every guest with an enriched ultimate dining experience to enjoy authentic Chinese cuisine</p>-->
                </div>
            </div>
            <div class="large-8 columns">
                <h4><a href="adventureWorld">Adventure World</a></h4>
                <p>Adventure World is the one of the biggest outdoor themeparks in the world. Ten different roller coasters and various performances provide you with thrill and joy.</p>
            </div>
        </div>
         <br>
         <div class="row">
            <div class="large-4 columns">
                <div class="panel callout">
                    <a class="th radius" href="/IRMSCustomer-war/images/attraction/IndoorThemepark.jpg">
                        <img src="/IRMSCustomer-war/images/attraction/IndoorThemepark.jpg">
                    </a>           
                </div>
            </div>
            <div class="large-8 columns">
                <h4><a href="horrorHouse">Horror House</a></h4>
                <p>Horror House is an indoor themepark putting you into the "home" of Vampires. "Real" vampires will be around to give you a great thrill.</p>
            </div>
        </div>
         <div class="row">
            <div class="large-4 columns">
                <div class="panel callout">
                    <a class="th radius" href="/IRMSCustomer-war/images/attraction/Museum.jpg">
                        <img src="/IRMSCustomer-war/images/attraction/Museum.jpg">
                    </a>    
                </div>
            </div>
            <div class="large-8 columns">
                <h4><a href="museum">the Art Museum</a></h4>
                <p>The art museum provides a large collection of local art masterpieces. It's a great opportunity to know about the Northeast culture.</p>
            </div>
        </div>
         <div class="row">
            <div class="large-4 columns">
                <div class="panel callout">
                    <a class="th radius" href="/IRMSCustomer-war/images/attraction/Aquarium.jpg">
                        <img src="/IRMSCustomer-war/images/attraction/Aquarium.jpg">
                    </a>    
                </div>
            </div>
            <div class="large-8 columns">
                <h4><a href="aquarium">the Underwater World</a></h4>
                <p>The Underwater World owns the most categories of deep-water creatures in Asia. Also, there are various performances daily.</p>
            </div>
        </div>
       
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
