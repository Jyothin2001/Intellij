<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User View page</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>
.form-width {
            width: 500px;
            margin-left: 550px;
            background-color:;
            border-radius:5;
        }

</style>

 <!-- Placeholder styling
 <style>
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }
 </style>-->


</head>
<body>

<nav class="navbar navbar navbar-light bg-primary " >
  <div class="container-fluid">
   <div class="navbar-header">

      <!-- Add your logo here -->
              <a class="navbar-brand" href="#">
                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
              </a>

   <a class="navbar-brand text-light" href="index.jsp"><b>Home</b></a>
   <a class="navbar-brand text-light" href="SignIn.jsp"><b>SignIn</b></a>
   <a class="navbar-brand text-light" href="Profile.jsp"><b>Profile</b></a>
 </div>
</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 5px;">
                   <h2 style= "color:blue; "><center>User Details</center></h2>
              </div>

       <!--text/word colors-->
         <div class="card-body text-dark">
                <strong class="card-title">Name: ${signUpDTO1.firstName} ${signUpDTO.lastName}</strong><br><br>
                <p class="card-text"><strong>Email: ${signUpDTO1.email}</strong></p>
                <p class="card-text"><strong>Contact Number: ${signUpDTO1.contactNumber}<strong></p>
                <p class="card-text"><strong>Alternative Contact Number: ${signUpDTO1.alternateContactNumber}</strong></p>
                <p class="card-text"><strong>Address: ${signUpDTO1.address}</strong></p>
            </div>
        </div>
    </div>


 </body>
</html>

