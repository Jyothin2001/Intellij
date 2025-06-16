<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Employee Details</title>

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

let fieldsChecks = {
  "employeeName": false,
  "email": false,
  "contactNumber": false,
  "address": false,

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
  let value = element.value.trim(); // Trim any extra spaces

  if (value === '') {
    error.innerHTML = "Contact Number is required.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  else if (/\D/.test(value)) {
    error.innerHTML = "Contact number must contain only digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  else if (!mobileRegex.test(value)) {
    error.innerHTML = "Contact Number should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  else {
    // Trim leading zeroes if the user entered them
    value = value.replace(/^0+/, '');

    if (value.length !== 10) {
      error.innerHTML = "Contact number must be exactly 10 digits after trimming/removing 0.Not Allowing Number Start with 0";
      error.style.color = "red";
      fieldsChecks["contactNumber"] = false;
    } else {
      error.innerHTML = "";
      fieldsChecks["contactNumber"] = true;
      numberAjaxValidation(); // Assuming this is for additional server-side validation
    }
  }

  validateAndEnableSubmit();
}

function addressValidation() {
  const element = document.getElementById("address");
  const error = document.getElementById("addressError");
  const value = element.value.trim();

  if (value.length > 3 && value.length < 50) {
    error.innerHTML = "";
    fieldsChecks["address"] = true;
  } else {
    error.innerHTML = "Address should be between 3 and 300 characters.";
    error.style.color = "red";
    fieldsChecks["address"] = false;
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

document.getElementById("employeeName").addEventListener("blur", validateEmployeeName);
document.getElementById("email").addEventListener("blur", emailValidation);
document.getElementById("contactNumber").addEventListener("blur", contactNumberValidation);
document.getElementById("address").addEventListener("blur", addressValidation);


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
                    <button
                        class="btn dropdown-toggle px-4 py-2 rounded-pill"
                        style="font-weight: bold; background-color: white; color: grey; border-color: grey;"
                        id="dropdownMenuButton1"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                        <i class="bi bi-person-circle"></i> ${employeeName}
                    </button>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                  <li><a class="dropdown-item" href="editEmployeeDetails"><strong>Edit Your Details</strong></a></li>

                  <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>View Complaint Details</strong></a></li>

                  <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>

                </ul>
              </div>

</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h3 style= "color:blue; "><center>Edit Your Details</center></h3>
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

      <form action="updateEmployeeDetails" method="post">
          <div style="color:red;"><b>${errorMessageFetchingEmployeeDetails}</b></div>
          <div style="color:green;"><b>${UpdateEmployeeDetails}</b></div>

<input type="hidden" name="employee_id" value="${employeeDTO.employee_id}"/>
<input type="hidden" name="departmentDTO.id" value="${employeeDTO.departmentDTO.id}"/>

          <!-- Employee Name -->
                    <div style="margin-bottom:2px;" class="form-group">
                        <span id="employeeError" class="error-message"></span><br>
                        <label for="employeeName" class="form-label">Employee Name:</label>
                        <div class="input-icon">
                            <i class="fas fa-user"></i>
                            <input type="text" class="form-control" id="employeeName" onblur="validateEmployeeName()" name="employeeName" placeholder="Enter Name" />
                        </div>
                    </div>
                    </br>
<div style="margin-bottom:2px;">
                    <b>Address:</b>
                    <input type="text" class="form-control"  style="border-radius: 15px;" id="address" value="${complaintRaiseDTO.address}"style="height: 80px" name="address"  readonly required>
                </div>

          <!-- Employee Designation -->
          <div style="margin-bottom:2px;" class="form-group">
              <label for="employeeDesignation" class="form-label">Employee Designation:</label>
              <div class="input-icon">
                  <i class="fas fa-user"></i>
                  <i class="fa-regular fa-building"></i>
                  <input type="text" class="form-control" id="employeeDesignation" value="${employeeDTO.employeeDesignation}" readonly name="employeeDesignation"  />
              </div>
          </div>
          <br>

          <!-- Department Name -->
          <div style="margin-bottom:2px;" class="form-group">
          <label for="departmentName" class="form-label">Department:</label>
          <div class="input-icon">
          <i class="fa-regular fa-building"></i>
          <input type="text" class="form-control" id="departmentName" readonly value="${employeeDTO.departmentName}" name="departmentName"/>
          </div>
          </div>


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
          <br>


          <!-- Submit Button -->
          <div class="d-grid gap-2" style="margin-bottom:10px;">
              <input type="submit" class="btn btn-primary btn-lg" id="submit" disabled value="Register">
          </div>
          <br>

      </form>


     </div>
  </div>
 </body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>
