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

<!-- Include this style in the <head> or your CSS file -->
<style>
    .dropdown-toggle.custom-dropdown-button {
        color: blue !important; /* Force text color to blue */
        border: 1px solid blue; /* Optional: Border color */
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
</style>

</style>
<script>

let fieldsChecks = {
  "employeeName": false,
  "employeeDesignation": false,
  "departmentName": false,
  "email": false,
  "contactNumber": false,
  "address": false,
  "agree": false
};

function validateAndEnableSubmit() {
  let flag = Object.values(fieldsChecks).includes(false);
  console.log("Fields checks:", fieldsChecks);
    console.log("Form valid:", !flag);


  if (!flag) {
    document.getElementById("submit").removeAttribute("disabled");
  } else {
    document.getElementById("submit").setAttribute("disabled", "");
  }
}

function validateEmployeeName() {
  const element = document.getElementById("employeeName");
  const error = document.getElementById("employeeError");
  const namePattern = /^[A-Za-z\s]+$/;
  const value = element.value.trim();

  if (value === '') {
    error.innerHTML = "Employee name is required.";
    error.style.color = "red";
    fieldsChecks["employeeName"] = false;
  } else if (!namePattern.test(value)) {
    error.innerHTML = "Name should only contain letters and spaces.";
    error.style.color = "red";
    fieldsChecks["employeeName"] = false;
  } else {
    error.innerHTML = "";
    fieldsChecks["employeeName"] = true;
  }

  validateAndEnableSubmit();
}

function validateEmployeeDesignation() {
  const element = document.getElementById("employeeDesignation");
  const error = document.getElementById("employeeDesignationError");
  const value = element.value.trim();

  if (value === '') {
    error.innerHTML = "Employee designation is required.";
    error.style.color = "red";
    fieldsChecks["employeeDesignation"] = false;
  } else {
    error.innerHTML = "";
    fieldsChecks["employeeDesignation"] = true;
  }

  validateAndEnableSubmit();
}

function validateDepartmentName() {
  const element = document.getElementById("departmentName");
  const error = document.getElementById("DepartmentNameError");
  const value = element.value.trim();

  if (value === '') {
    error.innerHTML = "Please select a department.";
    error.style.color = "red";
    fieldsChecks["departmentName"] = false;
  } else {
    error.innerHTML = "";
    fieldsChecks["departmentName"] = true;
  }

  validateAndEnableSubmit();
}

function emailValidation() {
  let element = document.getElementById("email");
  let error = document.getElementById("emailError");
  let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (element.value.trim() === '') {
    error.innerHTML = "Email is required.";
    error.style.color = "red";
    fieldsChecks["email"] = false;
  } else if (emailRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["email"] = true;
    emailAjaxValidation(); // Call AJAX validation
  } else {
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


function addressValidation() {
  const element = document.getElementById("address");
  const error = document.getElementById("addressError");
  const value = element.value.trim();

  if (value.length > 3 && value.length < 300) {
    error.innerHTML = "";
    fieldsChecks["address"] = true;
  } else {
    error.innerHTML = "Address should be between 3 and 300 characters.";
    error.style.color = "red";
    fieldsChecks["address"] = false;
  }

  validateAndEnableSubmit();
}

function agreeValidation() {
  const element = document.getElementById("agree");
  const error = document.getElementById("agreeError");

  if (element.checked) {
    error.innerHTML = "";
    fieldsChecks["agree"] = true;
  } else {
    error.innerHTML = "You must agree to the terms.";
    error.style.color = "red";
    fieldsChecks["agree"] = false;
  }

  validateAndEnableSubmit();
}

function emailAjaxValidation() {
  let email = document.getElementById("email").value;
  let error = document.getElementById("emailError");

  if (email === "") {
    error.innerHTML = "Please enter a valid email.";
    fieldsChecks["email"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateServerEmail/" + email);
  request.send();
  request.onload = function () {
    let response = this.responseText;
    error.innerHTML = response;

    if (response === "") {
      fieldsChecks["email"] = true;
    } else {
      fieldsChecks["email"] = false;
    }
    validateAndEnableSubmit();
  };
  request.onerror = function () {
    console.error("Request failed");
    error.innerHTML = "Validation failed. Please try again.";
    fieldsChecks["email"] = false;
    validateAndEnableSubmit();
  };
}

function numberAjaxValidation() {
  let contactNumber = document.getElementById("contactNumber").value;
  let error = document.getElementById("contactNumberError");

  if (contactNumber === "") {
    error.innerHTML = "Please enter a valid contact number.";
    fieldsChecks["contactNumber"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateServerContactNumber/" + contactNumber);
  request.send();
  request.onload = function () {
    let response = this.responseText;
    error.innerHTML = response;

    if (response === "") {
      fieldsChecks["contactNumber"] = true;
    } else {
      fieldsChecks["contactNumber"] = false;
    }
    validateAndEnableSubmit();
  };
  request.onerror = function () {
    console.error("Request failed");
    error.innerHTML = "Validation failed. Please try again.";
    fieldsChecks["contactNumber"] = false;
    validateAndEnableSubmit();
  };
}

// Event Listeners
document.getElementById("employeeName").addEventListener("blur", validateEmployeeName);
document.getElementById("employeeDesignation").addEventListener("blur", validateEmployeeDesignation);
document.getElementById("departmentName").addEventListener("change", validateDepartmentName);
document.getElementById("email").addEventListener("blur", emailValidation);
document.getElementById("contactNumber").addEventListener("blur", contactNumberValidation);
document.getElementById("address").addEventListener("blur", addressValidation);
document.getElementById("agree").addEventListener("change", agreeValidation);

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
              <button class="dropdown-toggle custom-dropdown-button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                 <span class="text-primary"> ${SubAdminName.adminName}</span>
              </button>

              <ul class="dropdown-menu dropdown-menu-end custom-dropdown-menu" aria-labelledby="dropdownMenuButton1">
                  <li><a class="dropdown-item" href="SubAdminChangePassword"><strong>Change Password</strong></a></li>
                  <li><a class="dropdown-item" href="get-Department-Names"><strong>Add Employee</strong></a></li>
                  <li><a class="dropdown-item" href="department-admin-complaintViewPage"><strong>View Complaint Details</strong></a></li>
                  <li><a class="dropdown-item" href="subAdminProfilePage"><strong>SunAdminProfilePage</strong></a></li>
              </ul>
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

      <form action="employeeRegistration" method="post">

          <div class="text-success"><b>${saveEmployee}</b></div>
          <div style="color:red;"><b>${saveDeptAdmin}</b></div>

          <!-- Employee Name -->
          <div style="margin-bottom:2px;" class="form-group">
              <span id="employeeError" class="error-message"></span><br>
              <label for="employeeName" class="form-label">Employee Name:</label>
              <div class="input-icon">
                  <i class="fas fa-user"></i>
                  <input type="text" class="form-control" id="employeeName" onblur="validateEmployeeName()" name="employeeName" placeholder="Enter Name" />
              </div>
          </div>

          <!-- Employee Designation -->
          <div style="margin-bottom:2px;" class="form-group">
              <span id="employeeDesignationError" class="error-message"></span><br>
              <label for="employeeDesignation" class="form-label">Employee Designation:</label>
              <div class="input-icon">
                  <i class="fas fa-user"></i>
                  <input type="text" class="form-control" id="employeeDesignation" onblur="validateEmployeeDesignation()" name="employeeDesignation" placeholder="Enter Designation" />
              </div>
          </div>
          <br>
          <!-- Dropdown select issue-->
          <label for="departmentName" class="form-label">Department:</label>
          <span id="DepartmentNameError" class="error-message"></span>
          <select class="form-select custom-select-width" id="departmentName" onblur="validateDepartmentName()" name="departmentName">
              <option value="">Select Department</option>
              <c:forEach items="${departments}" var="departmentName">
                  <option value="${departmentName.departmentName}">${departmentName.departmentName}</option>
              </c:forEach>
          </select>
<!-- Department
          <div style="margin-bottom:2px;" class="form-group">
              <span id="DepartmentNameError" class="error-message"></span><br>
              <label for="departmentName" class="form-label">Department:</label>
              <div class="input-icon">
                  <i class="fas fa-user"></i>
                  <input type="text" class="form-control" id="departmentName" onblur="validateDepartmentName()" name="departmentName" placeholder="Enter Department" />
              </div>
          </div>-->

          <!-- Email -->
          <div style="margin-bottom:2px;" class="form-group">
              <span id="emailError" class="error-message"></span><br>
              <label for="email" class="form-label">Email:</label>
              <div class="input-icon">
                  <i class="fa-regular fa-envelope"></i>
                  <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" placeholder="Enter Email" />
              </div>
          </div>

          <!-- Contact Number -->
          <div style="margin-bottom:2px;" class="form-group">
              <span id="contactNumberError" class="error-message"></span><br>
              <label for="contactNumber" class="form-label">Contact Number:</label>
              <div class="input-icon">
                  <i class="fa-solid fa-phone"></i>
                  <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" name="contactNumber" placeholder="Enter Contact Number" />
              </div>
          </div>


          <!-- Textarea: Address -->
          <div style="margin-bottom:2px;" class="form-group">
              <span id="addressError" class="error-message"></span><br>
              <b>Address:</b>
              <label for="address" class="form-floating"></label>
              <div class="input-icon">
                  <textarea class="form-control" id="address" placeholder="" onblur="addressValidation()" name="address" style="border-radius: 15px;"></textarea>
              </div>
          </div>

          <!-- Agree Terms -->
          <div>
              <span id="agreeError" class="error-message"></span><br>
              <input class="form-check-input" id="agree" type="checkbox" onchange="agreeValidation()" value="agree" />
              <b>I agree to </b><a href="#">Terms & Conditions</a>
          </div><br>

          <!-- Submit Button -->
          <div class="d-grid gap-2" style="margin-bottom:10px;">
              <input type="submit" class="btn btn-primary btn-lg" id="submit" disabled value="Register">
          </div><br>

      </form>


     </div>
  </div>
 </body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>