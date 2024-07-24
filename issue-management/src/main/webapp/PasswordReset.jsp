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

 <!-- Placeholder styling -->
 <style>
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }
 </style>

<!--Script link
  <script src="/issue-management/js/PasswordReset.js"></script>-->
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

   <a class="navbar-brand text-light" href="index.jsp"><b>Home</b></a>
 </div>
              <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user-->
              <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">

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

                     <div class="text-danger"><h6><b>${passwordResetError}</b></h6></div>



<!--Form-->
<form action="resetPassword" method="post">


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" autocomplete="email" style="border-radius: 15px;" placeholder="Enter Your Email">
                 </div>
                 </div>

                         <!--Text: old password-->
               <div style="margin-bottom:0px;" class="form-group">
                    <span id="oldPasswordError"></span><br>
                    <label for="password" class="form-label"><b></b></label>
                    <div class="input-icon">
                    <i class="fa-solid fa-key"></i>
                    <input type="password" class="form-control"  id="oldPassword" onblur="oldPasswordValidation()" autocomplete="old-password" name="oldPassword" style="border-radius: 15px;" placeholder="Old password">
               </div>
               </div>

                                             <!--Text: new password-->
                              <div style="margin-bottom:0px;" class="form-group">
                                   <span id="newPasswordError"></span><br>
                                   <label for="password" class="form-label"><b></b></label>
                                   <div class="input-icon">
                                   <i class="fa-solid fa-key"></i>
                                   <input type="password" class="form-control"  id="newPassword" onblur="newPasswordValidation()" autocomplete="new-password" name="newPassword" style="border-radius: 15px;" placeholder="New Password">
                              </div>
                              </div>

                                                          <!--Text: confirm password-->
                                             <div style="margin-bottom:24px;" class="form-group">
                                             <span id="confirmPasswordError"></span><br>
                                                  <label for="password" class="form-label"><b></b></label>
                                                  <div class="input-icon">
                                                  <i class="fa-solid fa-key"></i>
                                                  <input type="password" class="form-control"  id="confirmPassword" autocomplete="confirm-password" onblur="confirmPasswordValidation()" name="confirmPassword" style="border-radius: 15px;" placeholder="Confirm Password">
                                             </div>
                                             </div>


              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="Submit">
              </div>

         </form>
 </div>
  </div>

 </body>
</html>

