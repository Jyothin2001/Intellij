<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Sign up</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<!--Script link-->

<style>
.form-width {
            width: 500px;
            margin-left: 550px;
            background-color:;
            border-radius:5;
        }

</style>
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
 </div>
 <div class="dropdown">

  <div class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

      <!--<img src="${pageContext.request.contextPath}/images/profileicon.jpg" alt="Profile" width="30" height="30" class="rounded-circle"> <!-- Add your profile icon path -->
      <img src="https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg" alt="Profile" width="40" height="40" class="rounded-circle"> <!-- Add your profile icon path -->

             </div>
   <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
   <!--Retain user entered email in editPage , action=edit, ?=may be to differentiate,email=through email-->

     <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>
     <li><a class="dropdown-item" href="PasswordReset.jsp"><strong>Password Reset</strong></a></li>
     <li><a class="dropdown-item" href="view-Page"><strong>View</strong></a></li>
   </ul>
 </div>

 </div>


</nav>


    <div class="card border-dark container mt-5 mb-5 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width" >
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h1 style= "color:blue; "><center></center></h1>
              </div>


<!--Form-->

      <form action="signin" method="post">

<!--update-->
     <!-- <input type="hidden" name="id" value="${contactDTO.id !=null ? contactDTO.id:''}"/>--!>

<!----within page we need response we can use this line..
if we need another page to response the create another result page--!>

      <div class="text-primary"><h6><b>${msg}</b></h6></div>

      <div class="text-primary"><h6><b>${ErrorMsg}</b></h6></div>
       </form>

     </div>
  </div>



       </body>
</html>
