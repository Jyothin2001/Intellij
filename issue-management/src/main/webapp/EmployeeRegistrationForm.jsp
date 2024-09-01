<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Employee Registration</title>

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
   function validateEmployeeName() {
       const name = document.getElementById("employeeName").value;
       const nameError = document.getElementById("employeeError");

       const namePattern = /^[A-Za-z\s]+$/;
       if (name === "") {
           nameError.textContent = "Employee name is required.";
           nameError.style.color = "red";
           return false;
       } else if (!namePattern.test(name)) {
           nameError.textContent = "Name should only contain letters and spaces.";
           nameError.style.color = "red";
           return false;
       } else {
           nameError.textContent = "";
           return true;
       }
   }

   function validateDesignation() {
       const designation = document.getElementById("employeeDesignation").value;
       const designationError = document.getElementById("employeeDesignationError");

       if (designation === "") {
           designationError.textContent = "Employee designation is required.";
           designationError.style.color = "red";
           return false;
       } else {
           designationError.textContent = "";
           return true;
       }
   }

   function validateDepartmentName() {
       const departmentName = document.getElementById("departmentName").value;
       const departmentError = document.getElementById("DepartmentNameError");

       if (departmentName === "") {
           departmentError.textContent = "Please select a department.";
           departmentError.style.color = "red";
           return false;
       } else {
           departmentError.textContent = "";
           return true;
       }
   }

   function emailValidation() {
       const email = document.getElementById("email").value;
       const emailError = document.getElementById("emailError");

       const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
       if (email === "") {
           emailError.textContent = "Email is required.";
           emailError.style.color = "red";
           return false;
       } else if (!emailPattern.test(email)) {
           emailError.textContent = "Invalid email format.";
           emailError.style.color = "red";
           return false;
       } else {
           emailError.textContent = "";
           return true;
       }
   }

   function contactNumberValidation() {
       const contactNumber = document.getElementById("contactNumber").value;
       const contactNumberError = document.getElementById("contactNumberError");

       const phonePattern = /^[0-9]{10}$/;
       if (contactNumber === "") {
           contactNumberError.textContent = "Contact number is required.";
           contactNumberError.style.color = "red";
           return false;
       } else if (!phonePattern.test(contactNumber)) {
           contactNumberError.textContent = "Contact number must be 10 digits.";
           contactNumberError.style.color = "red";
           return false;
       } else {
           contactNumberError.textContent = "";
           return true;
       }
   }

   function addressValidation() {
       const address = document.getElementById("address").value;
       const addressError = document.getElementById("addressError");

       if (address.length < 5 || address.length > 100) {
           addressError.textContent = "Address must be between 5 and 50 characters.";
           addressError.style.color = "red";
           return false;
       } else {
           addressError.textContent = "";
           return true;
       }
   }

   function validateAgree() {
       const agree = document.getElementById("agree").checked;
       const agreeError = document.getElementById("agreeError");

       if (!agree) {
           agreeError.textContent = "You must agree to the terms and conditions.";
           agreeError.style.color = "red";
           return false;
       } else {
           agreeError.textContent = "";
           return true;
       }
   }

   function validateForm() {
       const isNameValid = validateEmployeeName();
       const isDesignationValid = validateDesignation();
       const isDepartmentValid = validateDepartmentName();
       const isEmailValid = emailValidation();
       const isContactValid = contactNumberValidation();
       const isAddressValid = addressValidation();
       const isAgreeValid = validateAgree();

       const isFormValid = isNameValid && isDesignationValid && isDepartmentValid && isEmailValid && isContactValid && isAddressValid && isAgreeValid;

       document.getElementById("submit").disabled = !isFormValid;

       return isFormValid;
   }

   // Attach blur event listeners for real-time validation
   document.getElementById("employeeName").addEventListener("blur", validateForm);
   document.getElementById("employeeDesignation").addEventListener("blur", validateForm);
   document.getElementById("departmentName").addEventListener("blur", validateForm);
   document.getElementById("email").addEventListener("blur", validateForm);
   document.getElementById("contactNumber").addEventListener("blur", validateForm);
   document.getElementById("address").addEventListener("blur", validateForm);
   document.getElementById("agree").addEventListener("change", validateForm);

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
</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h3 style= "color:blue; "><center>Registration Form</center></h3>
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

      <form action="employeeRegistration" method="post" >

              <div class="text-primary"><b>${saveEmployee}</b></div>


               <!-- Employee Name -->
                            <div style="margin-bottom:2px;" class="form-group">
                                <span id="employeeError" class="error-message"></span><br>
                                <label for="employeeName" class="form-label">Employee Name:</label>
                                <div class="input-icon">
                                    <i class="fas fa-user"></i>
                                    <input type="text" class="form-control" id="employeeName" onblur="validateEmployeeName()" name="employeeName" placeholder="Enter  Name" />
                                </div>
                            </div>


                             <!-- Employee Designation -->
                                          <div style="margin-bottom:2px;" class="form-group">
                                              <span id="employeeDesignationError" class="error-message"></span><br>
                                              <label for="employeeDesignation" class="form-label">Employee Designation:</label>
                                              <div class="input-icon">
                                                  <i class="fas fa-user"></i>
                                                  <input type="text" class="form-control" id="employeeDesignation" onblur="validateDesignation()" name="employeeDesignation" placeholder="Enter Designation" />
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
                      <input type="email" class="form-control" id="email"   onblur="emailValidation()" name="email" placeholder="Enter Email" />
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


             <!--textarea: address-->
              <div style="margin-bottom:2px;"  class="form-group">
                 <span id="addressError"></span><br>
                 <b>Address:</b>
                 <label for="address" class="form-floating"></label>
                 <div class="input-icon">
                 <!--words are not visible if other css override the placeholder -->
                 <!--<i class="fa fa-map-marker" aria-hidden="true"></i>-->
                 <textarea class="form-control " id="address" placeholder="" name="address" style="border-radius: 15px;" onblur="addressValidation()"></textarea>
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
                  <input type="submit" class="btn btn-primary btn-lg" id="submit" value="Register" >
              </div><br>

          </form>


     </div>
  </div>
 </body>

</html>
