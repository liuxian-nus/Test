<%-- 
    Document   : hotel
    Created on : Sep 11, 2013, 12:20:59 AM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Slate Theme, Portfolio Theme</title>
<meta name="keywords" content="slate, portfolio theme, darkcyan, teal color, free templates, website design, CSS, HTML" />
<meta name="description" content="Slate Portfolio, free web design template provided by templatemo.com" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />

<script language="javascript" type="text/javascript">
function clearText(field){

    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js">

/***********************************************
* Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

<!--////// CHOOSE ONE OF THE 3 PIROBOX STYLES  \\\\\\\-->
<link href="css_pirobox/white/style.css" media="screen" title="shadow" rel="stylesheet" type="text/css" />
<!--<link href="css_pirobox/white/style.css" media="screen" title="white" rel="stylesheet" type="text/css" />
<link href="css_pirobox/black/style.css" media="screen" title="black" rel="stylesheet" type="text/css" />-->
<!--////// END  \\\\\\\-->

<!--////// INCLUDE THE JS AND PIROBOX OPTION IN YOUR HEADER  \\\\\\\-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/piroBox.1_2.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$().piroBox({
			my_speed: 600, //animation speed
			bg_alpha: 0.5, //background opacity
			radius: 4, //caption rounded corner
			scrollImage : false, // true == image follows the page, false == image remains in the same open position
			pirobox_next : 'piro_next', // Nav buttons -> piro_next == inside piroBox , piro_next_out == outside piroBox
			pirobox_prev : 'piro_prev',// Nav buttons -> piro_prev == inside piroBox , piro_prev_out == outside piroBox
			close_all : '.piro_close',// add class .piro_overlay(with comma)if you want overlay click close piroBox
			slideShow : 'slideshow', // just delete slideshow between '' if you don't want it.
			slideSpeed : 4 //slideshow duration in seconds(3 to 6 Recommended)
	});
});
</script>
<!--////// END  \\\\\\\-->

</head>

<body>

<div id="templatemo_wrapper">
	<div id="templatemo_header">
    	<div id="site_title"><h1>Free CSS Templates</a></h1></div>
        <div class="cleaner"></div>
    </div> <!-- end of header -->
    <!--Start of menu bar-->
       <div id="templatemo_menu" class="ddsmoothmenu">
        <ul>
            <li><a href="home.jsp" >Home</a></li>
            <li><a href="hotel.jsp" class="selected">Hotel</a>
                <ul>
                    <li><a href="#">Sub menu 1</a></li>
                    <li><a href="#">Sub menu 2</a></li>
                    <li><a href="#">Sub menu 3</a></li>
                   <li><a href="#">Sub menu 1</a></li>
              	</ul>
          </li>
            <li><a href="restaurant.html">Restaurant</a>
                <ul>
                    <li><a href="#">Sub menu 1</a></li>
                    <li><a href="#">Sub menu 2</a></li>
                    <li><a href="#">Sub menu 3</a></li>
                    <li><a href="#">Sub menu 4</a></li>
                    <li><a href="#">Sub menu 5</a></li>
              	</ul>
            </li>
            <li><a href="entertainment.jsp" >Entertainment</a></li>
            <li><a href="attraction.jsp">Attraction</a></li>
             <li><a href="shopping.jsp" >Shopping</a></li>
           <li><a href="restaurant.jsp" >Restaurant</a></li>
            <li><a href="conventions.jsp" >Conventions</a></li>
            <li><a href="members.html">Members</a></li>
        </ul>
        </div> <!-- end of templatemo_menu -->
    
    <div id="templatemo_main">
    	<div class="gallery_box">
            <h2>Web Designs</h2>
            <ul class="gallery">
                <li><a class="pirobox" href="images/gallery/01.jpg"><img src="images/gallery/01.jpg" alt="Image 01" /></a></li>
                <li><a class="pirobox" href="images/gallery/02.jpg"><img src="images/gallery/02.jpg" alt="Image 02" /></a></li>
                <li><a class="pirobox" href="images/gallery/03.jpg"><img src="images/gallery/03.jpg" alt="Image 03" /></a></li>
                <li><a class="pirobox" href="images/gallery/04.jpg"><img src="images/gallery/04.jpg" alt="Image 04" /></a></li>
            </ul>
			<div class="cleaner"></div>
            <a class="more" href="#">More</a><a href="http://es.onlyimage.com" title="imágenes" class="gallery_box_link"  >imágenes</a>
        </div>    
        <div class="gallery_box">
            <h2>3D Animations</h2>
            <ul class="gallery">
                <li><a class="pirobox" href="images/gallery/05.jpg"><img src="images/gallery/05.jpg" alt="Image 05" /></a></li>
                <li><a class="pirobox" href="images/gallery/06.jpg"><img src="images/gallery/06.jpg" alt="Image 06" /></a></li>
                <li><a class="pirobox" href="images/gallery/07.jpg"><img src="images/gallery/07.jpg" alt="Image 07" /></a></li>
                <li><a class="pirobox" href="images/gallery/08.jpg"><img src="images/gallery/08.jpg" alt="Image 08" /></a></li>
            </ul>
            <div class="cleaner"></div>
            <a class="more" href="#">More</a><a href="http://es.onlyimage.com" title="imágenes" class="gallery_box_link"  >imágenes</a>
        </div>
        <div class="gallery_box">
            <h2>T-Shirt Designs</h2>
            <ul class="gallery">
                <li><a class="pirobox" href="images/gallery/09.jpg"><img src="images/gallery/09.jpg" alt="Image 09" /></a></li>
                <li><a class="pirobox" href="images/gallery/10.jpg"><img src="images/gallery/10.jpg" alt="Image 10" /></a></li>
                <li><a class="pirobox" href="images/gallery/11.jpg"><img src="images/gallery/11.jpg" alt="Image 11" /></a></li>
                <li><a class="pirobox" href="images/gallery/12.jpg"><img src="images/gallery/12.jpg" alt="Image 12" /></a></li>
            </ul>
            <div class="cleaner"></div>
            <a class="more" href="#">More</a><a href="http://es.onlyimage.com" title="imágenes" class="gallery_box_link"  >imágenes</a>
        </div>  
        <div class="cleaner"></div>
    </div>
    
    <div id="templatemo_bottom">
    	<div class="col one_fourth">
        	<h5>Recent Posts</h5>
            <ul class="footer_link">
            	<li><a href="#">Morbi posuere dictum diam</a></li>
                <li><a href="#">Suscipit porta mauris</a></li>
                <li><a href="#">Luctus tincidunt iaculis</a></li>
                <li><a href="#">Ut porta, lacus at mattis</a></li>
                <li><a href="#">Non molestie turpis magna</a></li>
			</ul>
        </div>
        <div class="col one_fourth">
        	<h5>Recent Comments</h5>
            <ul class="footer_link">
            	<li><a href="#">Jones</a> on <a href="#">Vivamus ac augue eros</a></li>
                <li><a href="#">Steven</a> on <a href="#">Curabitur imperdiet lacus</a></li>
                <li><a href="#">Susan</a> on <a href="#">Duis nec justo ut eros dignissim</a></li>
                <li><a href="#">Collin</a> on <a href="#">Suscipit dui integer imperdiet</a></li>
                <li><a href="#">Edward</a> on <a href="#">Sed a,</a> Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</li>
			</ul>
        </div>
        <div class="col one_fourth">
        	<h5>Blogroll</h5>
            <ul class="footer_link">
            	<li><a href="http://www.templatemo.com/page/1">Free CSS Templates</a></li>
                <li><a href="http://www.webdesignmo.com/blog">Web Design Resources</a></li>
            	<li><a href="http://www.flashmo.com">Free Flash Templates</a></li>
                <li><a href="http://www.templatemo.com">Website Templates</a></li>
                <li><a href="http://www.koflash.com">Free Web Gallery</a></li>
			</ul>
        </div>
        <div class="col one_fourth last_box">
        	<h5>Follow Us</h5>
            <ul class="footer_link">
            	<li><a href="#" class="facebook social">Facebook</a></li>
                <li><a href="#" class="linkedin social">Linkedin</a></li>
                <li><a href="#" class="myspace social">Myspace</a></li>
                <li><a href="#" class="youtube social">Youtube</a></li>
                <li><a href="#" class="vimeo social">Vimeo</a></li>
			</ul>
        </div>
        <div class="cleaner"></div>
    </div>
    
    <div id="templatemo_footer">
    	Copyright © 2048 Your Company Name | Designed by <a href="http://www.templatemo.com" target="_parent">Free CSS Templates</a>
    </div>
</div>


<script type='text/javascript' src='js/logging.js'></script>
</body>

</html>
