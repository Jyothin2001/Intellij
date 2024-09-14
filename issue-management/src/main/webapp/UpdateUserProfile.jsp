<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User edit page</title>

<!--BootStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon link-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>


<!--Script link
<script src="/issue-management/js/EditUserDetails.js"></script>-->


<script>

// Function to handle profile image click
function handleImageClick()
 {
    document.getElementById('file').click();
}


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
/* Hide the file input */
#file {
  display: none;
}


/* Style the profile image as a clickable element */
.profile-image-container {
  position: relative;
  display: inline-block;
  margin: 0 auto;
}

.profile-image {
  cursor: pointer;
  border: 2px solid #007bff;
  padding: 5px;
  display: block;
  margin: 10px auto;
}

/* Style the "+" icon */
.add-icon {
  position: absolute;
  top:70px;
  left: 60px;
  font-size: 30px;

  color: white;
  background-color: blue;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}</style>
</head>

<body>

<nav class="navbar navbar-light bg-primary">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <!-- Logo and Home link -->
        <div class="d-flex align-items-center">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
            </a>
            <a class="navbar-brand text-light ms-3" href="HomePage"><b>Home</b></a>
        </div>

        <!-- Dropdown for user profile -->
        <div class="dropdown">
            <a class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <!-- Profile image -->
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80" class="rounded-circle" alt="Profile Image">
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                <li><h4 style="display: block; text-align: center; color: blue;">${UserFirstName}    ${UserLastName}</h4></li>
                              <li><a class="dropdown-item" href="ChangePassword"><strong>Password Reset</strong></a></li>
                              <li><a class="dropdown-item" href="view-Page"><strong>View Your Details</strong></a></li>
                              <li><a class="dropdown-item" href="ComplaintRaisePage"><strong>Complaint Raise</strong></a></li>
                              <li><a class="dropdown-item" href="viewComplaintRaise"><strong>View ComplaintRaise</strong></a></li>
                                 <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li><!--not working get=action(logout)-->

<li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal"><strong>View Your Details</strong></a></li>
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

              <div style = "margin-top: 15px;">
                   <h2 style= "color:blue; "><center>Edit Profile</center></h2>
              </div>

               <!--text/word colors-->
         <div class="card-body text-dark">

<!-- Display users profile image -->
   <center> <div class="profile-image-container">
      <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="100" height="100" class="rounded-circle " alt="Profile Image" id="profileImage" >
      <span class="add-icon" onclick="handleImageClick()">+</span>
    </div>
    </center>
    <br>
    <br>


<!--Form-->

      <form action="updateUserProfile" method="post" enctype="multipart/form-data">

       <div class="text-success"><b>${updateMsg}</b></div>
       <span style="color:green"><strong>${profileUploadMsg}</strong></span>
        <span style="color:red"><strong>${errorMessageFetchingUserDetails}</strong></span>
        <span style="color:red"><strong>${errorUploadMsg}</strong></span>
        <span style="color:red"><strong>${errorMessageProfile}</strong></span>


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
                   <input type="email" class="form-control" id="email"  name="email"  style="border-radius: 15px;" value="${signUpDTO.email}" placeholder="Enter Your Email" disabled>
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

                <div class="d-grid gap-2" style="margin-bottom:10px;">
                                 <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="Update">
                                 </div>

<!-- File: Image -->
      <div>
        <label for="file" class="form-label"></label>
       <!-- <div class="input-icon">
          <i class="fa-solid fa-file-arrow-up"></i>-->
          <input type="file" class="form-control" id="file" name="file">
        </div>
      </div></br>


         </form>


     </div>
  </div>
 </body>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>
