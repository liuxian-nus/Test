<%-- 
    Document   : memberInfoEditionConfirmation
    Created on : 27-Sep-2013, 11:29:16
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
           <h1>Your information has been updated.</h1>
          <!--          <p>
               <a href="memberInfo"> Please log in again. </a>
           </p>-->
           <div class="row">
               <div class="large-12 columns">
                   <form action="memberInfo">
                       <input class="button" type="Submit" value ="Go to member home page"/>
                       <input type="hidden" name="email" value="${data.memberEmail}"/>
                       <input type="hidden" name="loginStatus" value="${data2}"/>
                  </form>
           </div>
         </div>
         <jsp:include page="footer.jsp"></jsp:include>
      
    </body>
</html>
