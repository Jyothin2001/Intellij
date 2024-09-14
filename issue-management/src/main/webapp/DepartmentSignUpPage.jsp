<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Department Registration</title>

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
               const departmentName = document.getElementById('departmentName').value;
               const errorSpan = document.getElementById('DepartmentNameError');
               if (departmentName === '') {
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

  // Check if the email input is empty
  if (element.value.trim() === '') {
    error.innerHTML = "Email is required";
    error.style.color = "red";
    fieldsChecks["email"] = false;

  } else if (emailRegex.test(element.value)) {
    // If the email matches the regex
    error.innerHTML = "";
    fieldsChecks["email"] = true;
    emailAjaxValidation();

  } else {
    // If the email is invalid
    error.innerHTML = "Invalid email address.";
    error.style.color = "red";
    fieldsChecks["email"] = false;
  }

  validateAndEnableSubmit();
}

function contactNumberValidation() {
  const element = document.getElementById("contactNumber");
  const error = document.getElementById("contactNumberError");
  const mobileRegex = /^\d{10}$/;
  const value = element.value.trim(); // Trim to remove any extra spaces

  // Check if the input is empty
  if (value === '') {
    error.innerHTML = "Contact Number is required.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  // Check for non-numeric characters
  else if (/\D/.test(value)) {
    error.innerHTML = "Contact number must contain only digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  // Check if the input matches the 10-digit pattern
  else if (!mobileRegex.test(value)) {
    error.innerHTML = "Contact Number should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  else {
    error.innerHTML = "";
    fieldsChecks["contactNumber"] = true;
    numberAjaxValidation(); // Assuming this is for additional server-side validation
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
               request.open("GET", "http://localhost:8080/issue-management/subAdminEmailValidation/" + email);
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
               request.open("GET", "http://localhost:8080/issue-management/subAdminNumberValidation/" + contactNumber);
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
             const nonNumericPattern = /\D/; // Matches any non-numeric character

             // Check if the alternate contact number is empty
             if (alternateContactNumber.trim() === '') {
               errorSpan.textContent = 'Alternate contact number is required.';
               errorSpan.style.color = 'red';
               fieldsChecks["alternateContactNumber"] = false;
             }
             // Check for non-numeric characters
             else if (nonNumericPattern.test(alternateContactNumber)) {
               errorSpan.textContent = 'Alternate number must contain only digits.';
               errorSpan.style.color = 'red';
               fieldsChecks["alternateContactNumber"] = false;
             }
             // Check if the input matches the 10-digit pattern
             else if (!phonePattern.test(alternateContactNumber)) {
               errorSpan.textContent = 'Alternate number must be exactly 10 digits.';
               errorSpan.style.color = 'red';
               fieldsChecks["alternateContactNumber"] = false;
             }
             else {
               errorSpan.textContent = '';
               fieldsChecks["alternateContactNumber"] = true;
             }

             validateAndEnableSubmit();
           }


           function validateAgree() {
               const agree = document.getElementById('agree').checked;
               const errorSpan = document.getElementById('agreeError');
               if (!agree) {
                   errorSpan.textContent = 'You must agree  to the terms and conditions.';
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


<nav class="navbar navbar-light bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
    </a>

    <div class="dropdown">
                      <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                      Admin
                                  </button>

                       <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                       <li><a class="dropdown-item" href="viewUserDetails"><strong>View User Details</strong></a></li>
                      <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>View Complaint Raise Details</strong></a></li>
                      <li><a class="dropdown-item" href="viewSubAdminDepartmentDetails"><strong>view Department Admin Details</strong></a></li>
                       <li><a class="dropdown-item" href="getDepartmentName"><strong>Add Department Admin</strong></a></li>



                       </ul>
                     </div>

    </div>
  </div>
</nav>

    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h1 style= "color:blue; "><center>Registration</center></h1>
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

              <div class="text-success"><b>${saveDeptAdmin}</b></div>

<!-- Admin Name -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="AdminNameError" class="error-message"></span><br>
                  <label for="AdminName" class="form-label">Admin Name:</label>
                  <div class="input-icon">
                      <i class="fas fa-user"></i>
                      <input type="text" class="form-control" id="AdminName" onblur="validateAdminName()" name="adminName" placeholder="Enter Admin Name" />
                  </div>
              </div>
              <br>
              <!---dropdown select issue-->

                          <label for="departmentName" class="form-label">Department:</label>
                          <span id="DepartmentNameError" class="error-message"></span>
                 <select onblur="validateDepartmentName()" class="form-select custom-select-width" id="departmentName" name="departmentName">
                 <option value="">Select Department</option>
               <c:forEach items="${departments}" var="departmentName">
                 <option value="${departmentName.departmentName}">${departmentName.departmentName}</option>
               </c:forEach>
             </select>



              <!-- Email -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="emailError" class="error-message"></span><br>
                  <label for="email" class="form-label">Email:</label>
                  <div class="input-icon">
                      <i class="fa-regular fa-envelope"></i>
                      <input type="email" class="form-control" id="email"   onblur="emailValidation()" name="email" placeholder="Enter Your Email" />
                  </div>
              </div>

              <!-- Contact Number -->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="contactNumberError" class="error-message"></span><br>
                  <label for="contactNumber" class="form-label">Contact Number:</label>
                  <div class="input-icon">
                      <i class="fa-solid fa-phone"></i>
                      <input type="tel" class="form-control" id="contactNumber"   onblur="contactNumberValidation()" name="contactNumber" placeholder="Enter Contact Number" />
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
                  <input type="submit" class="btn btn-primary btn-lg" id="submit" value="Register" disabled >
              </div><br>

          </form>


     </div>
  </div>
 </body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>
