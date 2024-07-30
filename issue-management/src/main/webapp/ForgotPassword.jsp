<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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

   <a class="navbar-brand text-light" href="HomePage"><b>Home</b></a>
 </div>
 <div class="dropdown">
                          <div class=" dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">


                            <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user-->
                            <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80"
                              class="rounded-circle profile-image" alt="Profile Image">

                          </div>

                          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                            <!--Retain user entered email in editPage , action=edit, ?=may be to differentiate,email=through email-->
                           <!--get action = edit?email=${signUpDTO.email} for get=give action name, post=.jsp-->

                           <li><h4 style="display: block; text-align: center; color: blue;">${UserFirstName}    ${UserLastName}</h4></li>
                            <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit Your Details </strong></a></li>
                            <li><a class="dropdown-item" href="PasswordReset"><strong>Password Reset</strong></a></li>
                            <li><a class="dropdown-item" href="ComplaintRaisePage"><strong>Complaint Raise</strong></a></li>
                            <li><a class="dropdown-item" href="viewComplaintRaise"><strong>View ComplaintRaise</strong></a></li>
                               <li><a class="dropdown-item" href="HomePage"><strong>Log j Out</strong></a></li><!--not working get=action(logout)-->


                             <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> View your Details</strong></a></li>
                          </ul>
                        </div>

</div>
</nav>
 <!-- ******************************************************************************** --!>

    <div class="modal fade" id="ViewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header" style="background-color: #007bff; color: white; padding: 15px;">
            <h5 class="modal-title" id="exampleModalLabel">USER PROFILE</h5>
            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close" style="background: none; border: none; color: white; font-size: 1.5rem;">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          <center> <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80"
                          class="rounded-circle profile-image" alt="Profile Image"></center>

            <div class="profile-info" style="font-size: 16px; line-height: 1.5;">
              <p><strong>Name:</strong> ${signUpDTO.firstName} ${signUpDTO.lastName}</p>
              <p><strong>Email:</strong> ${signUpDTO.email}</p>
              <p><strong>Contact Number:</strong> ${signUpDTO.contactNumber}</p>
              <p><strong>Alternative Contact Number:</strong> ${signUpDTO.alternateContactNumber}</p>
              <p><strong>Address:</strong> ${signUpDTO.address}</p>
            </div>
          </div>
         <!-- <div class="modal-footer" style="border-top: 1px solid #dee2e6;">
            <button type="button" class="btn btn-dark" data-bs-dismiss="modal" style="transition: background-color 0.3s;">Close</button>
          </div>-->
        </div>
      </div>
    </div>
    <!-- End of View Profile Modal -->


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
                   <div class="text-primary"><h6><b>${msg}</b></h6></div>

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
                   <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" placeholder="Enter Email">
                 </div>
                 </div>
                 </br>

              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg "   value="Submit">
              </div>

         </form>

 </div>
  </div>

 </body>
</html>

