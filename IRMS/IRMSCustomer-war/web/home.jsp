<%-- 
    Document   : home
    Created on : Sep 10, 2013, 9:43:48 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

<title>Slate Theme - Free CSS Template</title>
<meta name="keywords" content="slate, theme, darkcyan, teal color, free templates, web design, CSS, HTML" />
<meta name="description" content="Slate Theme is using darkcyan or teal color free template by templatemo.com" />
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

        
    </head>
    <body>
        <div id="templatemo_wrapper">
	<div id="templatemo_header">
    	<div id="site_title"><h1><a href="http://www.templatemo.com">Free CSS Templates</a></h1></div>
        <div class="cleaner"></div>
    </div> <!-- end of header -->
    <div id="templatemo_menu" class="ddsmoothmenu">
        <ul>
            <li><a href="index.html" class="selected">Home</a></li>
            <li><a href="about.html" >Hotel</a>
                <ul>
                    <li><a href="#">Sub menu 1</a></li>
                    <li><a href="#">Sub menu 2</a></li>
                    <li><a href="#">Sub menu 3</a></li>
                   <li><a href="#">Sub menu 1</a></li>
              	</ul>
          </li>
            <li><a href="portfolio.html">Restaurant</a>
                <ul>
                    <li><a href="http://www.templatemo.com/page/1">Sub menu 1</a></li>
                    <li><a href="http://www.templatemo.com/page/2">Sub menu 2</a></li>
                    <li><a href="http://www.templatemo.com/page/3">Sub menu 3</a></li>
                    <li><a href="http://www.templatemo.com/page/4">Sub menu 4</a></li>
                    <li><a href="http://www.templatemo.com/page/5">Sub menu 5</a></li>
              	</ul>
            </li>
            <li><a href="blog.html" >Entertainment</a></li>
            <li><a href="contact.html">Attraction</a></li>
             <li><a href="blog.html" >Shopping</a></li>
           <li><a href="blog.html" >Restaurant</a></li>
            <li><a href="blog.html" >Conventions</a></li>
            <li><a href="blog.html">Memebers</a></li>
        </ul>
        </div>
        
       <!-- <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="Search" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
            </form>
        </div>
        <br style="clear: left" />
    </div> <!-- end of templatemo_menu -->
    <div id="templatemo_slider">
   
        <div id="slider" class="nivoSlider">
            <img src="images/slider/01.jpg" alt="Slider 01" title="Nam porttitor, felis ac posuere sagittis, diam nisl convallis." />
            <a href="#"><img src="images/slider/02.jpg" alt="Slider 02" title="Fusce sagittis, arcu a condimentum gravida." /></a>
            <img src="images/slider/03.jpg" alt="Slider 03" title="Donec purus eros, dictum nec accumsan quis, auctor non eros."/>
            <img src="images/slider/04.jpg" alt="Slider 04" title="Donec dignissim imperdiet orci sit amet posuere." />
        </div>
        <div id="htmlcaption" class="nivo-html-caption">
            <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
        </div>
        <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
		<script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
        <script type="text/javascript">
        $(window).load(function() {
			$('#slider').nivoSlider({
				effect:'random', // Specify sets like: 'fold,fade,sliceDown'
				slices:15, // For slice animations
				boxCols: 8, // For box animations
				boxRows: 4, // For box animations
				animSpeed:500, // Slide transition speed
				pauseTime:3000, // How long each slide will show
				startSlide:0, // Set starting Slide (0 index)
				directionNav:true, // Next and Prev navigation
				directionNavHide:false, // Only show on hover
				controlNav:false, // 1,2,3... navigation
				controlNavThumbs:false, // Use thumbnails for Control Nav
				controlNavThumbsFromRel:false, // Use image rel for thumbs
				controlNavThumbsSearch: '.jpg', // Replace this with...
				controlNavThumbsReplace: '_thumb.jpg', // ...this in thumb Image src
				keyboardNav:true, // Use left and right arrows
				pauseOnHover:true, // Stop animation while hovering
				manualAdvance:false, // Force manual transitions
				captionOpacity:0.8, // Universal caption opacity
				prevText: 'Prev', // Prev directionNav text
				nextText: 'Next', // Next directionNav text
				beforeChange: function(){}, // Triggers before a slide transition
				afterChange: function(){}, // Triggers after a slide transition
				slideshowEnd: function(){}, // Triggers after all slides have been shown
				lastSlide: function(){}, // Triggers when last slide is shown
				afterLoad: function(){} // Triggers when slider has loaded
			});
		});
        </script>   
    </div>
    
    <div id="templatemo_main">
    	<div class="col one_third">
        	<h2>Welcome To Slate</h2>
            <p><em>Sed dignissim ligula eu dolor rhoncus. Sed ac nulla vitae enim eleifend aliquam sed velit.</em></p>
            <p><a href="http://www.templatemo.com" target="_parent">Slate Theme</a> is free css template provided by <a href="http://www.templatemo.com" target="_parent">templatemo.com</a> for your personal or commercial websites. Credit goes to <a href="http://www.photovaco.com/" target="_blank">Free Photos</a> for photos. Duis dolor nisi, commodo eu auctor vehicula, condimentum quis risus. Nunc id lacus vel risus interdum mollis. Aenean leo ipsum, vulputate ac cursus eu, volutpat vel ante.</p>
            <a href="#" class="more">More</a>
        </div>
        <div class="col one_third">
        	<h2>Services</h2>
            <p>Cras consequat neque quis dolor feugiat vehicula. Aliquam erat volutpat. Phasellus adipiscing elit pharetra lobortis. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
            <ul class="templatemo_list">
            	<li>Fusce bibendum suscipit justo quis</li>
                <li>Nam sagittis euismod dui pellentesque</li>
                <li>Lacus a lectusa suspendisse luctus</li>
                <li>Maecenas tempus ipsum id orci dictum</li>
                <li>Nunc pharetra aliquam vehicula erat</li>
			</ul>
            <a href="#" class="more">More</a>	
        </div>
        <div class="col one_third last_box">
        	<h2>Coral Membership</h2>
            <img src="images/templatemo_image_01.jpg" alt="Image 01" class="image_frame" />
            <p><em>Curabitur turpis sapien, auctor sit amet tincidunt et, vestibulum amet dapibus eros.</em></p>
            <a href="#" class="more">More</a>	
        </div>
        
        <div class="cleaner hr_divider"></div>
        
    	<div class="col one_fourth">
        	<h3>Promotion1</h3>
            <img src="images/templatemo_image_05.jpg" alt="Image 01" class="image_frame" />
        	Aliquam semper lacus a eros commodo vestibulum interdum mi pretium. Aliquam ac risus leo.
		</div>
        <div class="col one_fourth">
        	<h3>Promotion2</h3>
            <img src="images/templatemo_image_02.jpg" alt="Image 02" class="image_frame" />
        	Phasellus aliquet enim sed ligula faucibus nec tempus turpis bibendum. Duis vitae vel elit accumsan.
        </div>
        <div class="col one_fourth">
        	<h3>Promotion3</h3>
            <img src="images/templatemo_image_03.jpg" alt="Image 03" class="image_frame" />
        	Donec eleifend erat sed enim sodales tincidunt suscipit metus ornare inceptos himenaeos.
        </div>
        <div class="col one_fourth last_box">
        	<h3>Promotion4</h3>
            <img src="images/templatemo_image_04.jpg" alt="Image 04" class="image_frame" />
        	Fusce malesuada interdum facilisis. Donec accumsan lectus id posuere. Morbi vel dictum purus. 
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
                <li><a href="#">Edward</a> on <a href="#">Sed facilisis tempus nulla sit </a></li>
			</ul>
        </div>
        <div class="col one_fourth">
        	<h5>Blogroll</h5>
            <ul class="footer_link">
            	<li><a href="#">Free CSS Templates</a></li>
                <li><a href="#">Web Design Resources</a></li>
            	<li><a href="#">Free Flash Templates</a></li>
                <li><a href="#">Website Templates</a></li>
                <li><a href="#">Free Web Gallery</a></li>
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

