<%-- 
    Document   : entertainmentVenue
    Created on : Oct 22, 2013, 8:35:47 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Event Venue Booking</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>

        <jsp:include page="header.jsp"></jsp:include>
        <div class="row">
               <div class="row">
                <ul class="button-group round even-4">
                    <li><p class = "button small secondary"><strong>Select date and time ></strong></li>
                    <li><p class="button small disabled" ><strong>Select your seats ></strong></li>
                    <li><p class="button small secondary"><strong>Make payment ></strong></li>
                    <li><p class="button small secondary"><strong>Confirm your reservation ></strong></li>
                </ul>
            </div>
        <fieldset>
            <legend>Booking Summary</legend>
             <div class="large-4 columns">
                        <a class="th radius" href="/IRMS-war/images/Harry-Potter.jpg" >
                            <img src="/IRMS-war/images/Harry-Potter.jpg" width="100" height="100">
                        </a>
                    </div>
                    <div class="large-8 columns">
                        <h5><strong>${thisShow.showName}</strong></h5>
                        <p>
                            ${thisShow.showDescription}
                            <br>
                        <h6> <strong> Time Chosen: </strong> </h6> <fmt:formatDate type="both" pattern="dd MMM YYYY hh:mm a"value="${thisSchedule.startDateTime}" />
                       
                       </p>
                    </div>
        </fieldset>
        </div>

               
        </table>
        <script>
            // <c:forEach items="${showSchedules}" var="schedule">
            //     <input type="radio" name="schedule" value="${schedule.startDateTime}">${schedule.startDateTime}<br>
            //     var day=document.getElementById('day');
            //     var month=document.getElementById('month');
            //     var year=document.getElementById('year');
            //     var week=document.getElementById('week');
            //     var time=document.getElementById('time');
            //     var schedule="${schedule.startDateTime}";
            //     var temp = schedule.split(" ");
            //     day.innerHTML(temp[2]);
            //     month.innerHTML(temp[1]);
            //     year.innerHTML(temp[5]);
            //     week.innerHTML(temp[0]);
            //     time.innerHTML(temp[3]);               
            // </c:forEach>

            </script>
            <div class="row">
        <div id="container" class="large-7 columns">
            <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v4.7.2.min.js"></script>
            <script defer="defer">
            var text;
            var price1 = "${showTicket1.showTicketPrice}";
            var price2 = "${showTicket2.showTicketPrice}";
            var price3 = "${showTicket3.showTicketPrice}";
            var price4 = "${showTicket4.showTicketPrice}";
            var price5 = "${showTicket5.showTicketPrice}";
            var price6 = "${showTicket6.showTicketPrice}";
            var ticket1= "${showTicket1.showTicketQuota}";
            var ticket2= "${showTicket2.showTicketQuota}";
            var ticket3= "${showTicket3.showTicketQuota}";
            var ticket4= "${showTicket4.showTicketQuota}"; 
            var ticket5= "${showTicket5.showTicketQuota}";
            var ticket6= "${showTicket6.showTicketQuota}";
    var stage = new Kinetic.Stage({
                container: 'container',
                width: 500,
                height: 300,
                color: '#FFFFFF'
            });

            var layer = new Kinetic.Layer();

            var complexText = new Kinetic.Text({
                x: 200,
                y: 10,
                text: 'Stage',
                fontSize: 20,
                fontFamily: 'Calibri',
                fill: '#555',
                align: 'center'
            });

            var rect = new Kinetic.Rect({
                x: 100,
                y: 10,
                stroke: '#555',
                strokeWidth: 5,
                fill: '#ddd',
                width: 275,
                height: 25,
            });

            layer.add(rect);
            layer.add(complexText);
            //Seating Area~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly1 = new Kinetic.Polygon({
                points: [5, 50, 5, 240, 90, 150, 90, 50, 5, 50],
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
                x: 15,
                y: 100,
                text: 'Area1 Price:$' + price1+'\n Available Tickets:'+ticket1,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect1 = new Kinetic.Rect({
                x: 15,
                y: 100,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });
            // add the shape to the layer
            layer.add(poly1);

            // Area 2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly2 = new Kinetic.Polygon({
                points: [5, 250, 90, 250, 90, 160],
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
                x: 25,
                y: 190,
                text: 'Area2 Price:$' + price2+'\n Available Tickets:'+ticket2,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect2 = new Kinetic.Rect({
                x: 25,
                y: 190,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });
            // add the shape to the layer
            layer.add(poly2);
            //Area 3 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly3 = new Kinetic.Polygon({
                points: [100, 50, 100, 150, 375, 150, 375, 50],
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
                x: 150,
                y: 75,
                text: 'Area3 Price:$' + price3+'\n Available Tickets:'+ticket3,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect3 = new Kinetic.Rect({
                x: 150,
                y: 75,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });

            // add the shape to the layer
            layer.add(poly3);
            //Area 4~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly4 = new Kinetic.Polygon({
                points: [100, 160, 100, 250, 375, 250, 375, 160],
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
                x: 150,
                y: 200,
                text: 'Area4 Price:$' + price4+'\n Available Tickets:'+ticket4,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect4 = new Kinetic.Rect({
                x: 150,
                y: 200,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });


            // add the shape to the layer
            layer.add(poly4);
            // Area 5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly5 = new Kinetic.Polygon({
                points: [385, 50, 385, 150, 470, 240, 470, 50],
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
                x: 300,
                y: 100,
                text: 'Area5 Price:$' + price5+'\n Available Tickets:'+ticket5,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect5 = new Kinetic.Rect({
                x: 300,
                y: 100,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });
            // add the shape to the layer
            layer.add(poly5);
            //Area 6~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            var poly6 = new Kinetic.Polygon({
                points: [385, 160, 385, 250, 470, 250],
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
                x: 300,
                y: 200,
                text: 'Area6 Price:$' + price6+'\n Available Tickets:'+ticket6,
                fontSize: 12,
                fontFamily: 'Calibri',
                fill: '#555',
                width: 150,
                height: 45,
                padding: 5,
                align: 'center'
            });

            var rect6 = new Kinetic.Rect({
                x: 300,
                y: 200,
                fill: '#FFC0CB',
                width: 150,
                height: 45,
                shadowColor: 'black',
                shadowBlur: 10,
                shadowOffset: [5, 5],
                shadowOpacity: 0.2,
            });
            // add the shape to the layer
            layer.add(poly6);

            stage.add(layer);
            </script>
        </div>
                <div class="large-5 columns">
                    <form id="entertainmentTicket" action="entertainmentPay" method="POST">
                        <fieldset>
                            <legend>Ticket</legend>

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
                                    <td width="300">&nbsp ${showTicket1.showTicketPrice}</td>
                                    <td width="300">   
                                        <select style="width:80px" required name="ticket1" id="ticket1"/>
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
                                <td width="300">&nbsp ${showTicket2.showTicketPrice}</td>
                                <td width="300">   
                                    <select style="width:80px" required name="ticket2" id="ticket2"/>
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
                                <td width="300">&nbsp ${showTicket3.showTicketPrice}</td>
                                <td width="300">   
                                    <select style="width:80px" required name="ticket3" id="ticket3"/>
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
                                <td width="300">&nbsp ${showTicket4.showTicketPrice}</td>
                                <td width="300">   
                                    <select style="width:80px" required name="ticket4" id="ticket4"/>

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
                                <td width="300">&nbsp ${showTicket5.showTicketPrice}</td>
                                <td width="300">   
                                    <select style="width:80px" required name="ticket5" id="ticket5"/>

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
                                <td width="300">&nbsp ${showTicket6.showTicketPrice}</td>
                                <td width="300">   
                                    <select style="width:80px" required name="ticket6" id="ticket6"/>
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
                            <div class="large-2 large-offset-8 columns">
                                <input type="submit" value="Continue" class="small button secondary">
                            </div>
                        </div>
                    </fieldset>         
                </form>   
            </div>
       

    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
