<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User edit page</title>

<!--BootStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon link-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-ND83p6+2LC9sNGvzFgiptEh0Wt3veCHpdwwvWY3Aj23FR5f4ob0C5sHbPkzJf6Hm" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-5mrLOimZlMFbbXUpiH8eAFKmKXbLqaW8GDoAWF+Q6h4Ec8Q2pSyyKhcvwwa3fznK" crossorigin="anonymous"></script>


<!--Script link
<script src="/issue-management/js/EditUserDetails.js"></script>-->

<script>
// FirstName
function firstNameValidation() {
    let element = document.getElementById("firstName");
    let error = document.getElementById("firstNameError");
    let nameRegex = /^[A-Za-z ]+$/;
    console.log(element);

    if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["firstName"] = true;
    } else {
        error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 3 and less than 30.";
        error.style.color = "red";
        fieldsChecks["firstName"] = false;
    }
    validateAndEnableSubmit();
}

// LastName
function lastNameValidation() {
    let element = document.getElementById("lastName");
    let error = document.getElementById("lastNameError");
    let nameRegex = /^[A-Za-z ]+$/;

    if (element.value.length > 1 && element.value.length <= 20 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["lastName"] = true;
    } else {
        error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 1 and less than 20.";
        error.style.color = "red";
        fieldsChecks["lastName"] = false;
    }
    validateAndEnableSubmit();
}

// Contact Number
function contactNumberValidation() {
    let element = document.getElementById("contactNumber");
    let error = document.getElementById("contactNumberError");
    let mobileRegex = /^\d{10}$/;

    if (mobileRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["contactNumber"] = true;
         numberAjaxValidation();
    } else {
        error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
        error.style.color = "red";
        fieldsChecks["contactNumber"] = false;
    }
    validateAndEnableSubmit();
}



// Alternate Contact Number
function alternateContactNumberValidation() {
    let element = document.getElementById("alternateContactNumber");
    let error = document.getElementById("altContactNbrError");
    let mobileRegex = /^\d{10}$/;

    if (mobileRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["alternateContactNumber"] = true;
    } else {
        error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
        error.style.color = "red";
        fieldsChecks["alternateContactNumber"] = false;
    }
    validateAndEnableSubmit();
}

// Address
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


//Ajax for number
//already wrote ajax in controller just make use of it here with same action(/validateNumber/)
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
<!--Add event listeners for input and blur events-->

  document.getElementById("firstName").addEventListener("input", firstNameValidation);
  document.getElementById("lastName").addEventListener("input", lastNameValidation);
  document.getElementById("contactNumber").addEventListener("input", contactNumberValidation);
  document.getElementById("alternateContactNumber").addEventListener("input", alternateContactNumberValidation);
  document.getElementById("address").addEventListener("input", addressValidation);


function validateAndEnableSubmit() {
    let isValid = true;

    // Check each field validity
    for (let field in fieldsChecks) {
        if (!fieldsChecks[field]) {
            isValid = false;
            break;
        }
    }

    // Enable/disable submit button based on overall form validity
    document.getElementById("submit").disabled = !isValid;
    }
</script>


<!--css link-->
<link rel="stylesheet" href="/issue-management/css/SignUp.css">

<style>
.form-check-input {
      /* Add any other styles you need, but avoid border-radius if you want the default checkbox look */
       /* Ensures default checkbox style is applied */

       appearance: auto;
    }


</style>
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
   <a class="navbar-brand text-light" href="SignIn.jsp"><b>SignIn</b></a>
 </div>
             <li class="nav-item">
             <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user--!>
             <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">
              </li>

</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >


              <div style = "margin-top: 15px;">
                   <h1 style= "color:blue; "><center>Edit user Details</center></h1>
              </div>

               <!--text/word colors-->
         <div class="card-body text-dark">


<!--Form-->

      <form action="edit-profile" method="post" enctype="multipart/form-data">

<div class="text-primary"><b>${msg}</b></div>
<span style="color:green"><strong>${profileUploadMsg}</strong></span>


           <!--Text: First Name-->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="firstNameError"></span><br>
                   <label for="firstName" class="form-label"><b></b></label>
                     <div class="input-icon">
                        <i class="fas fa-user"></i>
                     <input type="text" class="form-control" id="firstName" name="firstName"  onblur="firstNameValidation()" placeholder="Enter First Name" style="border-radius: 15px;" value="${signUpDTO.firstName}"/>
               </div>
               </div>


                 <!--Text: Last Name-->
               <div style="margin-bottom:2px;" class="form-group">
                    <span id="lastNameError"></span><br>
                    <label for="lastName" class="form-label"><b></b></label>
                    <div class="input-icon">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control"  id="lastName"  name="lastName" onblur="lastNameValidation()" placeholder="Enter last Name" style="border-radius: 15px;"  value="${signUpDTO.lastName}"/>
               </div>
               </div>

                    <!--email: Email-->
                <div style="margin-bottom:2px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email"   name="email"  style="border-radius: 15px;" value="${signUpDTO.email}" placeholder="Enter Your Email"/ disabled>
                 </div>
                 </div>


                  <!--tel: Contact Number-->
                <div  style="margin-bottom:2px;" class="form-group">
                     <span id="contactNumberError"></span><br>
                     <label for="contactNumber" class="form-label"><b></b></label>
                     <div class="input-icon">
                     <i class="fa-solid fa-phone"></i>
                     <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" onchange="numberAjaxValidation()" name="contactNumber"  style="border-radius: 15px;" value="${signUpDTO.contactNumber}" placeholder="Enter Contact Number"/>
                </div>
                </div>


                 <!--tel: Alternative Number-->
               <div style="margin-bottom:2px;" class="form-group">
                  <span id="altContactNbrError"></span><br>
                  <label for="alternateContactNumber" class="form-label"><b></b></label>
                  <div class="input-icon">
                  <i class="fa-solid fa-phone"></i>
                 <input type="tel"  class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()"  name="alternateContactNumber" style="border-radius: 15px;" value="${signUpDTO.alternateContactNumber}" placeholder="Enter Alternative Number"/>
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
                 <textarea class="form-control " id="address" placeholder="" onblur="addressValidation()"name="address" style="border-radius: 15px;" >${signUpDTO.address}</textarea>
                </div>
                </div><br>

              <!--file:file-->
                <div >
                <label for="file" class="form-label">Add image:</label>
                <div class="input-icon">
                <i class="fa-solid fa-file-arrow-up"></i>
                <input type="file" class="form-control" id="file" name="file">
                </div>
                </div></br>

                            <div class="d-grid gap-2" style="margin-bottom:10px;">
                                <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="Update">
                            </div>

         </form>


     </div>
  </div>
 </body>
</html>
