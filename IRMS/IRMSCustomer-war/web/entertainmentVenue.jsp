<%-- 
    Document   : entertainmentVenue
    Created on : Oct 22, 2013, 8:35:47 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Venue Booking</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>

        <jsp:include page="header.jsp"></jsp:include>
            <div id="container"></div>
            <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v4.7.2.min.js"></script>
            <script defer="defer">
                var text;
                var price1 = 168;
                var price2 = 68;
                var price3 = 268;
                var price4 = 198;
                var price5 = 168;
                var price6 = 68;
                var stage = new Kinetic.Stage({
                    container: 'container',
                    width: 1000,
                    height: 600,
                });

                var layer = new Kinetic.Layer();

                var complexText = new Kinetic.Text({
                    x: 450,
                    y: 30,
                    text: 'Stage',
                    fontSize: 24,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    align: 'center'
                });

                var rect = new Kinetic.Rect({
                    x: 200,
                    y: 20,
                    stroke: '#555',
                    strokeWidth: 5,
                    fill: '#ddd',
                    width: 550,
                    height: 50,
                });

                layer.add(rect);
                layer.add(complexText);
//Seating Area~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly1 = new Kinetic.Polygon({
                    points: [10, 100, 10, 480, 180, 300, 180, 100, 10, 100],
                    fill: '#B5B5B5',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.5
                });

                poly1.on('mouseup', function() {
                    text = 'This is the 1st area';
                    console.log(text);
                });

                poly1.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect1);
                    layer.add(text1);
                    layer.draw();
                });

                poly1.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text1.remove();
                    rect1.remove();
                    layer.draw();
                });

                var text1 = new Kinetic.Text({
                    x: 30,
                    y: 200,
                    text: 'Area1 Price:$' + price1 + "  ",
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 200,
                    padding: 10,
                    align: 'center'
                });

                var rect1 = new Kinetic.Rect({
                    x: 30,
                    y: 200,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });
                // add the shape to the layer
                layer.add(poly1);

// Area 2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly2 = new Kinetic.Polygon({
                    points: [10, 500, 180, 500, 180, 320],
                    fill: '#4F4F4F',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.5
                });

                poly2.on('mouseup', function() {
                    text = 'This is the 2st area';
                    console.log(text);
                });

                poly2.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect2);
                    layer.add(text2);
                    layer.draw();
                });

                poly2.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text2.remove();
                    rect2.remove();

                    layer.draw();
                });

                var text2 = new Kinetic.Text({
                    x: 50,
                    y: 380,
                    text: 'Area2 Price:$' + price2,
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 180,
                    padding: 10,
                    align: 'center'
                });

                var rect2 = new Kinetic.Rect({
                    x: 50,
                    y: 380,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });
                // add the shape to the layer
                layer.add(poly2);
//Area 3 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly3 = new Kinetic.Polygon({
                    points: [200, 100, 200, 300, 750, 300, 750, 100],
                    fill: '#FFFFFF',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.7
                });

                poly3.on('mouseup', function() {
                    text = 'This is the 3rd area';
                    console.log(text);
                });

                poly3.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect3);
                    layer.add(text3);
                    layer.draw();
                });

                poly3.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text3.remove();
                    rect3.remove();

                    layer.draw();
                });

                var text3 = new Kinetic.Text({
                    x: 300,
                    y: 150,
                    text: 'Area3 Price:$' + price3,
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 180,
                    padding: 10,
                    align: 'center'
                });

                var rect3 = new Kinetic.Rect({
                    x: 300,
                    y: 150,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });

                // add the shape to the layer
                layer.add(poly3);
//Area 4~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly4 = new Kinetic.Polygon({
                    points: [200, 320, 200, 500, 750, 500, 750, 320],
                    fill: '#000000',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.5
                });

                poly4.on('mouseup', function() {
                    text = 'This is the 3rd area';
                    console.log(text);
                });

                poly4.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect4);
                    layer.add(text4);
                    layer.draw();
                });

                poly4.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text4.remove();
                    rect4.remove();

                    layer.draw();
                });

                var text4 = new Kinetic.Text({
                    x: 300,
                    y: 400,
                    text: 'Area4 Price:$' + price4,
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 180,
                    padding: 10,
                    align: 'center'
                });

                var rect4 = new Kinetic.Rect({
                    x: 300,
                    y: 400,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });


                // add the shape to the layer
                layer.add(poly4);
                // Area 5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly5 = new Kinetic.Polygon({
                    points: [770, 100, 770, 300, 940, 480, 940, 100],
                    fill: '#B5B5B5',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.5
                });

                poly5.on('mouseup', function() {
                    text = 'This is the 5th area';
                    console.log(text);
                });

                poly5.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect5);
                    layer.add(text5);
                    layer.draw();
                });

                poly5.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text5.remove();
                    rect5.remove();

                    layer.draw();
                });

                var text5 = new Kinetic.Text({
                    x: 800,
                    y: 200,
                    text: 'Area5 Price:$' + price5,
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 180,
                    padding: 10,
                    align: 'center'
                });

                var rect5 = new Kinetic.Rect({
                    x: 800,
                    y: 200,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });
                // add the shape to the layer
                layer.add(poly5);
//Area 6~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                var poly6 = new Kinetic.Polygon({
                    points: [770, 320, 770, 500, 940, 500],
                    fill: '#4F4F4F',
                    stroke: 'black',
                    strokeWidth: 2,
                    opacity: 0.5
                });
                poly6.on('mouseup', function() {
                    text = 'This is the 6th area';
                    console.log(text);
                });

                poly6.on('mouseover', function() {
                    this.setStroke('white');
                    this.setStrokeWidth(5);

                    layer.add(rect6);
                    layer.add(text6);
                    layer.draw();
                });

                poly6.on('mouseout', function() {
                    this.setStroke('null');
                    this.setStrokeWidth(0);
                    text6.remove();
                    rect6.remove();

                    layer.draw();
                });

                var text6 = new Kinetic.Text({
                    x: 800,
                    y: 400,
                    text: 'Area6 Price:$' + price4,
                    fontSize: 18,
                    fontFamily: 'Calibri',
                    fill: '#555',
                    width: 180,
                    padding: 10,
                    align: 'center'
                });

                var rect6 = new Kinetic.Rect({
                    x: 800,
                    y: 400,
                    fill: '#FFC0CB',
                    width: 180,
                    height: 35,
                    shadowColor: 'black',
                    shadowBlur: 10,
                    shadowOffset: [10, 10],
                    shadowOpacity: 0.2,
                });
                // add the shape to the layer
                layer.add(poly6);

                stage.add(layer);
            </script>
            <div class="row">
                <form id="member" action="memberInfo" method="POST">
                    <fieldset>
                        <legend>Please Select Your Ticket</legend>
                        <p>${message}</p>
                    <table>

                        <thead>
                        <th width="300">Area</th>
                        <th width="300">Ticket Price</th>
                        <th width="300">Number of Tickets</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="300">&nbsp Area 1</td>
                                <td width="300">&nbsp $168</td>
                                <td width="300">   
                                    <select style="width:100px" required name="ticket1" id="ticket1"/>
                        <option value="0">0</option>            
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>

                        </tr>
                        <tr>
                            <td width="300">&nbsp Area 2</td>
                            <td width="300">&nbsp $68</td>
                            <td width="300">   
                                <select style="width:100px" required name="ticket2" id="ticket2"/>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>


                        </tr>

                        <tr>
                            <td width="300">&nbsp Area 3</td>
                            <td width="300">&nbsp $268</td>
                            <td width="300">   
                                <select style="width:100px" required name="ticket3" id="ticket3"/>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>

                        </tr>

                        <tr>
                            <td width="300">&nbsp Area 4</td>
                            <td width="300">&nbsp $198</td>
                            <td width="300">   
                                <select style="width:100px" required name="ticket4" id="ticket4"/>

                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>

                        </tr>
                        <tr>
                            <td width="300">&nbsp Area 5</td>
                            <td width="300">&nbsp $168</td>
                            <td width="300">   
                                <select style="width:100px" required name="ticket5" id="ticket5"/>

                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>

                        </tr>
                        <tr>
                            <td width="300">&nbsp Area 6</td>
                            <td width="300">&nbsp $68</td>
                            <td width="300">   
                                <select style="width:100px" required name="ticket6" id="ticket6"/>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        </select></td>

                        </tr>
                        </tbody>
                    </table>
                    <div class="row">
                        <div class="large-2 large-offset-10 columns">
                            <input type="submit" value="Continue" class="small button secondary">
                        </div>
                    </div>
                </fieldset>         
            </form>   
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
