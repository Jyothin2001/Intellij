<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


 <!--css link-->
 <link rel="stylesheet" href="css/SignUp.css">

 <!-- Placeholder styling -->
 <style>
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }
 </style>

<!--Script link-->
  <script src="/issue-management/js/ForgotPassword.js"></script>


</head>
<body>

<nav class="navbar navbar navbar-light bg-primary " >
  <div class="container-fluid">
   <div class="navbar-header">

      <!-- Add your logo here -->
              <a class="navbar-brand" href="#">
                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
              </a>

   <a class="navbar-brand text-light" href="LogInPage"><b>Login</b></a>
 </div>

</div>
</nav>
    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >
    <img src="https://static.vecteezy.com/system/resources/previews/007/746/400/original/unlock-password-correct-success-login-concept-illustration-flat-design-eps10-modern-graphic-element-for-landing-page-empty-state-ui-infographic-icon-vector.jpg" style="height:250px;"  alt="Card Image" class="card-image">
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div>
                   <h2 style= "color:blue; "><center>Forgot Password</center></h2>
                  <p style="margin-bottom:5px;"> Enter your email we'll send you a new password to get back into your account.</p>
                </div>

       <!--text/word colors-->
         <div  class="card-body text-dark">


               <!--Displaying messages -->
                   <div class="text-success"><h6><b>${msg}</b></h6></div>

                   <div class="text-danger"><h6><b>${forgotPasswordError}</b></h6></div>

<!--Form-->
<form action="forgotPassword" method="post">

<!--<input type="hidden" id="accountLocked" value="true">-->


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" onblur="emailValidation()"  name="email" placeholder="Enter Email">
                 </div>
                 </div>
                 </br>

              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg "  value="Submit">
              </div>

         </form>

 </div>
  </div>

 </body>

</html>

