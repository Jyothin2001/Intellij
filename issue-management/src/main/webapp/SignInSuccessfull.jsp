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
