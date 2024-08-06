<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department Sign up</title>

<!--BootStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon link-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>

<!--css link-->
<link rel="stylesheet" href="/issue-management/css/SignUp.css">

<style>
.form-check-input {
      /* Add any other styles you need, but avoid border-radius if you want the default checkbox look */
      appearance: auto; /* Ensures default checkbox style is applied */
    }

.error-message {
            color: red;

        }

</style>
<script>
    fieldsChecks = {
               adminName: false,
               departmentName: false,
               email: false,
               contactNumber: false,
               alternateContactNumber: true, // Optional
               agree: false
           };

           function validateAdminName() {
               const adminName = document.getElementById('AdminName').value;
               const errorSpan = document.getElementById('AdminNameError');
               if (adminName.trim() === '') {
                   errorSpan.textContent = 'Admin Name is required.';
                   fieldsChecks["adminName"] = false;
               } else if (adminName.length < 3) {
                   errorSpan.textContent = 'Admin Name must be at least 3 characters long.';
                   fieldsChecks["adminName"] = false;
               } else {
                   errorSpan.textContent = '';
                   fieldsChecks["adminName"] = true;
               }
               validateAndEnableSubmit();
           }

           function validateDepartmentName() {
               const departmentName = document.getElementById('DepartmentName').value;
               const errorSpan = document.getElementById('DepartmentNameError');
               if (departmentName === '0') {
                   errorSpan.textContent = 'Please select a department.';
                   fieldsChecks["departmentName"] = false;
               } else {
                   errorSpan.textContent = '';
                   fieldsChecks["departmentName"] = true;
               }
               validateAndEnableSubmit();
           }

function emailValidation() {
  let element = document.getElementById("email");
  let error = document.getElementById("emailError");
  let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (emailRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["email"] = true;

  } else {
    error.innerHTML = "Invalid email address.";
    error.style.color = "red";
    fieldsChecks["email"] = false;
  }
  validateAndEnableSubmit();
}
function contactNumberValidation() {
  let element = document.getElementById("contactNumber");
  let error = document.getElementById("contactNumberError");
  let mobileRegex = /^\d{10}$/;

  if (mobileRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["contactNumber"] = true;

  } else {
    error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  validateAndEnableSubmit();
}


           function emailAjaxValidation() {
               console.log("Validate email");
               let email = document.getElementById("email").value;
               let error = document.getElementById("emailError");

               if (email.trim() === '') {
                   error.innerHTML = "Please enter a valid email";
                   fieldsChecks["email"] = false;
                   validateAndEnableSubmit();
                   return;
               }

               const request = new XMLHttpRequest();
               request.open("GET", "http://localhost:8080/issue-management/validateEmail/" + encodeURIComponent(email));
               request.send();
               request.onload = function() {
                   let ref = this.responseText;
                   error.innerHTML = ref;

                   if (ref === "") {
                       fieldsChecks["email"] = true;
                   } else {
                       fieldsChecks["email"] = false;
                   }
                   validateAndEnableSubmit();
               };
               request.onerror = function() {
                   console.error("Request failed");
                   error.innerHTML = "Validation failed. Please try again.";
                   fieldsChecks["email"] = false;
                   validateAndEnableSubmit();
               };
           }

           function numberAjaxValidation() {
               console.log("Validate contact number");
               let contactNumber = document.getElementById("contactNumber").value;
               let error = document.getElementById("contactNumberError");

               if (contactNumber.trim() === '') {
                   error.innerHTML = "Please enter a valid contact number";
                   fieldsChecks["contactNumber"] = false;
                   validateAndEnableSubmit();
                   return;
               }

               const request = new XMLHttpRequest();
               request.open("GET", "http://localhost:8080/issue-management/validateNumber/" + encodeURIComponent(contactNumber));
               request.send();
               request.onload = function() {
                   let ref = this.responseText;
                   error.innerHTML = ref;

                   if (ref === "") {
                       fieldsChecks["contactNumber"] = true;
                   } else {
                       fieldsChecks["contactNumber"] = false;
                   }
                   validateAndEnableSubmit();
               };
               request.onerror = function() {
                   console.error("Request failed");
                   error.innerHTML = "Validation failed. Please try again.";
                   fieldsChecks["contactNumber"] = false;
                   validateAndEnableSubmit();
               };
           }

           function validateAlternateContactNumber() {
               const alternateContactNumber = document.getElementById('alternateContactNumber').value;
               const errorSpan = document.getElementById('altContactNbrError');
               const phonePattern = /^\d{10}$/;
               if (alternateContactNumber.trim() !== '' && !phonePattern.test(alternateContactNumber)) {
                   errorSpan.textContent = 'Alternate Number must be 10 digits.';
                   fieldsChecks["alternateContactNumber"] = false;
               } else {
                   errorSpan.textContent = '';
                   fieldsChecks["alternateContactNumber"] = true;
               }
               validateAndEnableSubmit();
           }

           function validateAgree() {
               const agree = document.getElementById('agree').checked;
               const errorSpan = document.getElementById('agreeError');
               if (!agree) {
                   errorSpan.textContent = 'You must agree to the terms and conditions.';
                   fieldsChecks["agree"] = false;
               } else {
                   errorSpan.textContent = '';
                   fieldsChecks["agree"] = true;
               }
               validateAndEnableSubmit();
           }

           function validateAndEnableSubmit() {
               const isValidForm = Object.values(fieldsChecks).every(check => check);
               document.getElementById('submit').disabled = !isValidForm;
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
   <a class="navbar-brand text-light" href="DepartmentLogInPage"><b>Log In</b></a>
 </div>
</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h1 style= "color:blue; "><center>Sign up Form</center></h1>
              </div>

               <!--text/word colors-->
         <div class="card-body text-dark">

             <!---showing errors--!>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>
<!--Form-->

      <form action="DepartmentSignUp" method="post" onsubmit="return validateForm()">

              <div class="text-primary"><b>${saveDeptAdmin}</b></div>

              <!-- Admin Name -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="AdminNameError" class="error-message"></span><br>
                  <label for="AdminName" class="form-label">Admin Name:</label>
                  <div class="input-icon">
                      <i class="fas fa-user"></i>
                      <input type="text" class="form-control" id="AdminName" onblur="validateAdminName()" name="adminName" placeholder="Enter Admin Name" />
                  </div>
              </div>

              <!-- Department Name -->
              <div style="margin-bottom:2px;">
                  <span id="DepartmentNameError" class="error-message"></span><br>
                  <label for="DepartmentName" class="form-label">Department Name:</label>
                  <select class="form-select" id="DepartmentName" onblur="validateDepartmentName()" name="departmentName" required>
                      <option value="0">Select</option>
                      <option value="Electric issue">Electric issue</option>
                      <option value="Water Supply">Water Supply</option>
                      <option value="Network Problem">Network Problem</option>
                      <option value="System Problem">System Problem</option>
                      <option value="Water Problem">Water Problem</option>
                  </select>
              </div>

              <!-- Email -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="emailError" class="error-message"></span><br>
                  <label for="email" class="form-label">Email:</label>
                  <div class="input-icon">
                      <i class="fa-regular fa-envelope"></i>
                      <input type="email" class="form-control" id="email" oninput="emailValidation()" name="email" placeholder="Enter Your Email" />
                  </div>
              </div>

              <!-- Contact Number -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="contactNumberError" class="error-message"></span><br>
                  <label for="contactNumber" class="form-label">Contact Number:</label>
                  <div class="input-icon">
                      <i class="fa-solid fa-phone"></i>
                      <input type="tel" class="form-control" id="contactNumber" oninput="contactNumberValidation()" name="contactNumber" placeholder="Enter Contact Number" />
                  </div>
              </div>

              <!-- Alternate Contact Number -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="altContactNbrError" class="error-message"></span><br>
                  <label for="alternateContactNumber" class="form-label">Alternate Number:</label>
                  <div class="input-icon">
                      <i class="fa-solid fa-phone"></i>
                      <input type="tel" class="form-control" id="alternateContactNumber" onblur="validateAlternateContactNumber()" name="alternateContactNumber" placeholder="Enter Alternative Number" />
                  </div>
              </div>

              <!-- Agree Terms -->
              <div>
                  <span id="agreeError" class="error-message"></span><br>
                  <input class="form-check-input" id="agree" type="checkbox" onchange="validateAgree()" value="agree" />
                  <b>I agree to </b><a href="#">Terms & Conditions</a>
              </div><br>

              <!-- Submit Button -->
              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg" id="submit" value="Sign Up" >
              </div><br>

          </form>


     </div>
  </div>
 </body>

</html>
