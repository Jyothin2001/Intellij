<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Reset</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


 <!--css link-->
 <link rel="stylesheet" href="css/SignUp.css">


 <style>

 <!-- password styling-->
     .form-group {
         margin-bottom: 24px;
         position: relative;
         width: 100%;
     }
     .oldPassword {
         position: relative;
         width: 100%;
     }
     <!--.password input {
         width: 100%;
         padding: 10px;
         padding-right: 40px; /* Space for the icon */
         outline: none;
         border: 1px solid #000;
         border-radius: 15px;
         box-sizing: border-box;
     }-->
     .pass-icon {
         position: absolute;
         top: 52%;
         right: 40px;
         width: 25px;
         height: 25px; /* Ensures the icon fits within the field */
         cursor: pointer;
         transform: translateY(-50%);
     }

 \
<!-- Placeholder styling
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;-->
   }
 </style>

<script>
let fieldsChecks = {
    "email": false,
    "oldPassword": false,
    "newPassword": false,
    "confirmPassword": false
};

function validateAndEnableSubmit() {
    let allFieldsValid = Object.values(fieldsChecks).every(value => value);
    let submitButton = document.getElementById("submit");

    if (allFieldsValid) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "true");
    }
}



function emailValidation() {
          let element = document.getElementById("email");
          let error = document.getElementById("emailError");

          let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
          let trimmedValue = element.value.trim();

          if (trimmedValue === "") {
            error.innerHTML = "Please enter an email address.";
            error.style.color = "red";
            fieldsChecks["email"] = false;
          } else if (!emailRegex.test(trimmedValue)) {
            error.innerHTML = "Invalid hh email address.";
            error.style.color = "red";
            fieldsChecks["email"] = false;
          } else {
            error.innerHTML = "";
            fieldsChecks["email"] = true;
          }

          validateAndEnableSubmit();
        }



function oldPasswordValidation() {
    console.log("oldPasswordValidation")
    let oldPassword = document.getElementById("oldPassword");
    let error = document.getElementById("oldPasswordError");

    //let oldPasswordRegex =/^[A-Za-z0-9]+$/;
    // Updated regex to include special characters
      //  let oldPasswordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,}$/;
      //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{8,}$/;
      let oldPasswordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;


    if (oldPassword.value === "") {
        error.innerHTML = "Password field cannot be empty.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    } else if (oldPasswordRegex.test(oldPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["oldPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    }


    validateAndEnableSubmit();
}

function newPasswordValidation() {
    console.log("newPasswordValidation");
    let newPassword = document.getElementById("newPassword");
    let error = document.getElementById("newPasswordError");

    let newPasswordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    // Get the value of the old password field
    let oldPassword = document.getElementById("oldPassword");

    if (newPassword.value === "") {
        error.innerHTML = "Password field cannot be empty.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    } else if (newPassword.value === oldPassword.value) {
        error.innerHTML = "New password cannot be the same as the old password.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    } else if (newPasswordRegex.test(newPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["newPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    }

    validateAndEnableSubmit();
}

function confirmPasswordValidation() {
    let newPassword = document.getElementById("newPassword");
    let confirmPassword = document.getElementById("confirmPassword");
    let error = document.getElementById("confirmPasswordError");

    if (confirmPassword.value === "") {
        error.innerHTML = "Confirm Password field cannot be empty.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    } else if (confirmPassword.value === newPassword.value && fieldsChecks["newPassword"]) {
        error.innerHTML = "";
        fieldsChecks["confirmPassword"] = true;
    } else {
        error.innerHTML = "Passwords do not match.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    }

    validateAndEnableSubmit();
}

<!--password-->
var a;
function oldPass()
{
if(a==1)
{
document.getElementById('oldPassword').type='password';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=0;
}
else
{
document.getElementById('oldPassword').type='text';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=1;
}
}

var a;
function newPass()
{
if(a==1)
{
document.getElementById('newPassword').type='password';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=0;
}
else
{
document.getElementById('newPassword').type='text';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=1;
}
}

var a;
function confirmPass()
{
if(a==1)
{
document.getElementById('confirmPassword').type='password';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=0;
}
else
{
document.getElementById('confirmPassword').type='text';
document.getElementById('pass-icon').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s';
a=1;
}
}



</script>

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
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 5px;">
                   <h2 style= "color:blue; "><center>Password Reset</center></h2>
              </div>

       <!--text/word colors-->
         <div class="card-body text-dark">

             <!---showing errors--!>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>

               <!--Displaying messages -->

                     <div class="text-danger"><h6><b>${passwordResetError}</b></h6></div>



<!--Form-->
<form action="resetPassword" method="post">


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" value="${sessionScope.signedInUserEmail}" readonly autocomplete="email" style="border-radius: 15px;" placeholder="Enter Your Email">
                 </div>
                 </div>


                         <!--Text: old password-->
               <div style="margin-bottom:0px;" >
                    <span id="oldPasswordError"></span><br>
                    <label for="password" class="form-label"><b></b></label>
                   <div class=" form-group ">
                    <div class="input-icon">
                    <i class="fa-solid fa-key"></i>
                    <input type="password" class="form-control"  id="oldPassword" onblur="oldPasswordValidation()" autocomplete="old-password" name="oldPassword" style="border-radius: 15px;" placeholder="Old password">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s" onclick="oldPass()" class="pass-icon" id="pass-icon">
               </div>
               </div>
               </div>

                                             <!--Text: new password-->
                              <div style="margin-bottom:0px;" class="form-group">
                                   <span id="newPasswordError"></span><br>
                                   <label for="password" class="form-label"><b></b></label>
                                   <div class=" form-group ">
                                   <div class="input-icon">
                                   <i class="fa-solid fa-key"></i>
                                   <input type="password" class="form-control"  id="newPassword" onblur="newPasswordValidation()" autocomplete="new-password" name="newPassword" style="border-radius: 15px;" placeholder="New Password">
                                   <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s" onclick="newPass()" class="pass-icon" id="pass-icon">
                              </div>
                              </div>
                              </div>

                                                          <!--Text: confirm password-->
                                             <div style="margin-bottom:24px;" class="form-group">
                                             <span id="confirmPasswordError"></span><br>
                                                  <label for="password" class="form-label"><b></b></label>
                                                  <div class=" form-group ">
                                                  <div class="input-icon">
                                                  <i class="fa-solid fa-key"></i>
                                                  <input type="password" class="form-control"  id="confirmPassword" autocomplete="confirm-password" onblur="confirmPasswordValidation()" name="confirmPassword" style="border-radius: 15px;" placeholder="Confirm Password">
                                                  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcget2ZJS21scl6Hz7Es5tzZFqiiMSPACqWw&s" onclick="confirmPass()" class="pass-icon" id="pass-icon">
                                             </div>
                                             </div>
                                             </div>


              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="Submit">
              </div>

         </form>
 </div>
  </div>

 </body>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>

