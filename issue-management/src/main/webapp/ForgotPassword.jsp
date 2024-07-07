<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<!--Script link-->
 <script src="/issue-management/js/SignIn.js"></script>

</head>

    <body>
    <nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
      <div class="container-fluid">

      <!-- Add your logo here -->
                              <a class="navbar-brand" href="#">
                                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50" >
                              </a>

        <a class="navbar-brand text-light" href="SignUp.jsp"><b>Sign Up</b></a>
        <a class="navbar-brand text-light" href="SignIn.jsp"><b>Sign In</b></a>



        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon">jy</span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-link active" aria-current="page" href="#"></a>

          </div>
        </div>


         <form class="d-flex" action="forgotPassword" method="post">
                                                 <!--email: Email-->
                                <div style="margin-bottom:0px;" class="form-group">
                                   <span id="emailError"></span><br>
                                   <label for="email" class="form-label"><b></b></label>
                                   <div class="input-icon">
                                   <i class="fa-regular fa-envelope"></i>
                                   <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" placeholder="Enter Email">
                                 </div>
                                 </div>



                <button class="btn btn-outline-success" type="submit">Search</button>
              </form>

      </div>
    </nav>
    </body>

    </html>
