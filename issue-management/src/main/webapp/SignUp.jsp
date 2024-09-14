<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Sign up</title>

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


</style>
<script>
let fieldsChecks = {
  "firstName": false,
  "lastName": false,
  "email": false,
  "contactNumber": false,
  "alternateContactNumber": false,
  "address": false,
  "agree": false
};

function validateAndEnableSubmit() {
  let flag = Object.values(fieldsChecks).includes(false);

  if (!flag) {
    document.getElementById("submit").removeAttribute("disabled");
  } else {
    document.getElementById("submit").setAttribute("disabled", "");
  }
}

function firstNameValidation() {
  const element = document.getElementById("firstName");
  const error = document.getElementById("firstNameError");
  const nameRegex = /^[A-Za-z ]+$/;
  const value = element.value.trim(); // Trim to avoid issues with only spaces

  // Check if the input is empty
  if (value === '') {
    error.innerHTML = "First Name is required.";
    error.style.color = "red";
    fieldsChecks["firstName"] = false;
  }
  // Check if the input matches the regex and length constraints
  else if (!nameRegex.test(value)) {
    error.innerHTML = "Name should be alphabetic characters only.";
    error.style.color = "red";
    fieldsChecks["firstName"] = false;
  }
  // Check for length constraints
  else if (value.length < 3 || value.length > 30) {
    error.innerHTML = "First Name should be greater than 3 and less than 30 characters.";
    error.style.color = "red";
    fieldsChecks["firstName"] = false;
  }
  else {
    error.innerHTML = "";
    fieldsChecks["firstName"] = true;
  }

  validateAndEnableSubmit();
}

function lastNameValidation() {
  const element = document.getElementById("lastName");
  const error = document.getElementById("lastNameError");
  const nameRegex = /^[A-Za-z ]+$/;
  const value = element.value.trim(); // Trim to avoid issues with only spaces

  // Check if the input is empty
  if (value === '') {
    error.innerHTML = "Last Name is required.";
    error.style.color = "red";
    fieldsChecks["lastName"] = false;
  }
  // Check if the input matches the regex (alphabetic characters only)
  else if (!nameRegex.test(value)) {
    error.innerHTML = "Name should be alphabetic characters only.";
    error.style.color = "red";
    fieldsChecks["lastName"] = false;
  }
  // Check for length constraints
  else if (value.length < 1 || value.length > 10) {
    error.innerHTML = "Last Name should be between 1 and 20 characters.";
    error.style.color = "red";
    fieldsChecks["lastName"] = false;
  }
  else {
    error.innerHTML = "";
    fieldsChecks["lastName"] = true;
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
    emailAjaxValidation();
    fieldsChecks["email"] = true;

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

function alternateContactNumberValidation() {
  const element = document.getElementById("alternateContactNumber");
  const error = document.getElementById("altContactNbrError");
  const mobileRegex = /^\d{10}$/;
  const nonNumericPattern = /\D/; // Matches any non-numeric character

  // Check if the input is empty
  if (element.value.trim() === '') {
    error.innerHTML = "Alternate contact number is required.";
    error.style.color = "red";
    fieldsChecks["alternateContactNumber"] = false;
  }
  // Check for non-numeric characters
  else if (nonNumericPattern.test(element.value)) {
    error.innerHTML = "Alternate contact number must contain only digits.";
    error.style.color = "red";
    fieldsChecks["alternateContactNumber"] = false;
  }
  // Check if the input matches the 10-digit pattern
  else if (!mobileRegex.test(element.value)) {
    error.innerHTML = " contact number should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["alternateContactNumber"] = false;
  }
  else {
    error.innerHTML = "";
    fieldsChecks["alternateContactNumber"] = true;
  }

  validateAndEnableSubmit();
}

function addressValidation() {
  let element = document.getElementById("address");
  let error = document.getElementById("addressError");

  if (element.value.length > 3 && element.value.length < 300) {
    error.innerHTML = "";
    fieldsChecks["address"] = true;
  } else {
    error.innerHTML = "Address should be greater than 3 and less than 300 characters.";
    error.style.color = "red";
    fieldsChecks["address"] = false;
  }
  validateAndEnableSubmit();
}

function agreeValidation() {
  let element = document.getElementById("agree");
  let error = document.getElementById("agreeError");

  if (element.checked) {
    error.innerHTML = "";
    fieldsChecks["agree"] = true;
  } else {
    error.innerHTML = "Please agree to the terms.";
    error.style.color = "red";
    fieldsChecks["agree"] = false;
  }
  validateAndEnableSubmit();
}

function emailAjaxValidation() {
  console.log("Validate email");
  let email = document.getElementById("email").value;
  let error = document.getElementById("emailError");

  if (email == "") {
    error.innerHTML = "Please Enter Valid email";
    fieldsChecks["email"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateEmail/" + email);
  request.send();
  request.onload = function ()
  {
    let ref = this.responseText;
    error.innerHTML = ref;

    if (ref === "") {
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
  console.log("Validate contact number");
  let contactNumber = document.getElementById("contactNumber").value;
  let error = document.getElementById("contactNumberError");

  if (contactNumber == "") {
    error.innerHTML = "Please enter valid contactNumber";
    fieldsChecks["contactNumber"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateNumber/" + contactNumber);
  request.send();
  request.onload = function () {
    let ref = this.responseText;
    error.innerHTML = ref;

    if (ref === "") {
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
   <a class="navbar-brand text-light" href="LogInPage"><b>Log In</b></a>
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

      <form action="signUp" method="post">

<div class="text-primary"><b>${msg}</b></div>
<div class="text-success"><b>${emailMsg}</b></div>
<div class="text-success"><b>${emailErrorMsg}</b></div>


           <!--Text: First Name-->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="firstNameError"></span><br>
                   <label for="firstName" class="form-label"><b></b></label>
                     <div class="input-icon">
                        <i class="fas fa-user"></i>
                     <input type="text" class="form-control" id="firstName" onblur="firstNameValidation()" name="firstName"   placeholder="Enter First Name" style="border-radius: 15px;" />
               </div>
               </div>


                 <!--Text: Last Name-->
               <div style="margin-bottom:2px;" class="form-group">
                    <span id="lastNameError"></span><br>
                    <label for="lastName" class="form-label"><b></b></label>
                    <div class="input-icon">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control"  id="lastName" onblur="lastNameValidation()" name="lastName"  placeholder="Enter last Name" style="border-radius: 15px;" />
               </div>
               </div>

                    <!--email: Email-->
                <div style="margin-bottom:2px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" oninput="emailValidation()"    name="email"  style="border-radius: 15px;"  placeholder="Enter Your Email"/>
                 </div>
                 </div>


                  <!--tel: Contact Number-->
                <div  style="margin-bottom:2px;" class="form-group">
                     <span id="contactNumberError"></span><br>
                     <label for="contactNumber" class="form-label"><b></b></label>
                     <div class="input-icon">
                     <i class="fa-solid fa-phone"></i>
                     <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()"  name="contactNumber"  style="border-radius: 15px;"  placeholder="Enter Contact Number"/>
                </div>
                </div>


                 <!--tel: Alternative Number-->
               <div style="margin-bottom:2px;" class="form-group">
                  <span id="altContactNbrError"></span><br>
                  <label for="alternateContactNumber" class="form-label"><b></b></label>
                  <div class="input-icon">
                  <i class="fa-solid fa-phone"></i>
                 <input type="tel"  class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternateContactNumber" style="border-radius: 15px;"  placeholder="Enter Alternative Number"/>
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


              <!--checkbox: Agree-->
            <div>
                <span  id="agreeError"></span>
                <label  for="agree" ></label></br>
                <input  class="form-check-input"  id="agree" type="checkbox" onchange="agreeValidation()"  style="border-radius: 15px;"  type="checkbox"  value="agree" / >
                <b>I agree to </b><a href="#"> Terms & condition</a>
                </label>
           </div>
           </div><br>


              <div class="d-grid gap-2" style="margin-bottom:10px;">
                                <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="SignUp">
                            </div><br>


              <div style="text-align:center;">
              Already have an account?<a href="LogInPage">Sign In</a>
              </div>

             <!--<div style="text-align:center;" class="proxima-nova">
                  Already have an account? <a href="LogInPage">Sign In</a>
                </div>-->

         </form>


     </div>
  </div>
 </body>

</html>

