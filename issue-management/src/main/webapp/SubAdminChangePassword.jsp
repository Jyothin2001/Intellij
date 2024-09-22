<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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


<!-- Placeholder styling
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;-->
   }

   <!-- Include this style in the <head> or your CSS file -->

       .dropdown-toggle.custom-dropdown-button {
           color: lightblue !important; /* Blue text color for button */
           border: 1px solid blue; /* Optional: Border color for the button */
           background-color: transparent; /* Ensure button background is transparent */
       }

       .dropdown-toggle.custom-dropdown-button::after {
           color: blue; /* Dropdown arrow color */
       }

       .dropdown-menu.custom-dropdown-menu .dropdown-item {
           color: black; /* Default option text color */
       }

       .dropdown-menu.custom-dropdown-menu .dropdown-item:hover {
           background-color: blue; /* Background color on hover */
           color: white; /* Text color on hover */
       }

       /* Optional: Ensure image alignment within the button */
       .dropdown-toggle.custom-dropdown-button .profile-image {
           vertical-align: middle;
       }


 </style>

<script>
let fieldsChecks = {
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
var a=0;
function oldPass()
{
console.log('Current state of a:', a); // Debugging line
if(a==1)//close
{
document.getElementById('oldPassword').type='password';
document.getElementById('old-pass-icon').src='https://img.icons8.com/?size=100&id=121539&format=png&color=000000';
a=0;
}
else
{
document.getElementById('oldPassword').type='text';
document.getElementById('old-pass-icon').src='https://img.icons8.com/?size=100&id=986&format=png&color=000000';
a=1;//open
}
console.log('New state of a:', a); // Debugging line
}


var c=0;// 0 means password is hidden, 1 means password is visible

function newPass()
{
console.log('Current state of c:', c); // Debugging line
if(c==1)
{
document.getElementById('newPassword').type='password';
document.getElementById('new-pass-icon').src='https://img.icons8.com/?size=100&id=121539&format=png&color=000000';
c=0;
}
else
{
document.getElementById('newPassword').type='text';
document.getElementById('new-pass-icon').src='https://img.icons8.com/?size=100&id=986&format=png&color=000000';
c=1;
}
console.log('New state of c:', c); // Debugging line
}


var b;
function confirmPass()
{
console.log('Current state of b:', b); // Debugging line
if(b==1)
{
document.getElementById('confirmPassword').type='password';
document.getElementById('confirm-pass-icon').src='https://img.icons8.com/?size=100&id=121539&format=png&color=000000';
b=0;
}
else
{
document.getElementById('confirmPassword').type='text';
document.getElementById('confirm-pass-icon').src='https://img.icons8.com/?size=100&id=986&format=png&color=000000';
b=1;
}
console.log('New state of b:', b); // Debugging line
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


 </div>

 <div class="dropdown">
                         <button class=" dropdown-toggle custom-dropdown-button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"><span class="text-primary">${SubAdminName.adminName}<span>


                           <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user
                           <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80"
                             class="rounded-circle profile-image" alt="Profile Image">-->

                         </button>

                         <ul class="dropdown-menu dropdown-menu-end custom-dropdown-menu" aria-labelledby="dropdownMenuButton1">

                           <!--Retain user entered email in editPage , action=edit, ?=may be to differentiate,email=through email-->
                          <!--get action = edit?email=${signUpDTO.email} for get=give action name, post=.jsp-->


                                        <li><a class="dropdown-item" href="get-Department-Names"><strong>Add Employee</strong></a></li>

                                        <li><a class="dropdown-item" href="department-admin-complaintViewPage"><strong>View Complaint Details</strong></a></li>
                                        <li><a class="dropdown-item" href="subAdminProfilePage"><strong>Profile Page</strong></a></li>
                                        <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>


                         </ul>
                       </div>

</div>
</nav>

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

                     <div class="text-danger"><h6><b>${error}</b></h6></div>
                     <div class="text-success"><h6><b>${msg}</b></h6></div>



<!--Form-->
<form action="subAdminChangePassword" method="post">


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email"  name="email" value="${sessionScope.subAdminEmail}" readonly  style="border-radius: 15px;" placeholder="Enter Your Email">
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
                    <img src="https://img.icons8.com/?size=100&id=121539&format=png&color=000000" onclick="oldPass()" class="pass-icon" id="old-pass-icon">
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
                                   <img src="https://img.icons8.com/?size=100&id=121539&format=png&color=000000" onclick="newPass()" class="pass-icon" id="new-pass-icon">
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
                                                  <input type="password" class="form-control"  id="confirmPassword" autocomplete="confirm-password" oninput="confirmPasswordValidation()" name="confirmPassword" style="border-radius: 15px;" placeholder="Confirm Password">
                                                  <img src="https://img.icons8.com/?size=100&id=121539&format=png&color=000000" onclick="confirmPass()" class="pass-icon" id="confirm-pass-icon">
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

