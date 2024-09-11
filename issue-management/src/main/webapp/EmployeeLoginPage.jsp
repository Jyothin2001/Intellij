<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome library for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    .oval-btn {
        border-radius: 40px;
        padding: 10px 20px;
        font-size: 1.2em;
    }

    .bold-text {
        font-weight: bold;
    }

    body {
        background-color: #f8f9fa; /* Light background color */
    }

    .card {
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1); /* Soft shadow for card */
        border-radius: 20px;
    }

    .card-header h3 {
        font-weight: bold;
        color: #343a40; /* Dark color for the header */
    }

    .input-group-text {
        background-color: #343a40;
        color: #fff; /* White icon color */
    }

    #captcha {
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
        border: 1px solid #ced4da;
        height: calc(2.25rem + 2px);
    }

    #captchaImage {
        border: 1px solid #ced4da;
        border-right: none;
        padding: 3px;
        height: calc(2.25rem + 2px);
        background-color: #e9ecef; /* Light grey background for CAPTCHA image */
    }

    #refreshCaptcha {
        margin-left: 10px;
        cursor: pointer;
        color: #007bff;
    }

    #refreshCaptcha:hover {
        color: black; /* Darker blue on hover */
    }

    .form-label {
        font-weight: bold;
    }

    .container {
        max-width: 500px;
    }

    .navbar-brand img {
        border-radius: 10px;
    }

</style>

<script>
    function disableButton() {
        var accountLocked = "${accountLocked}";
        if (accountLocked === "true") {
            document.getElementById("signInButton").disabled = true;
        }
    }
    window.onload = disableButton;

    function refreshCaptcha() {
        document.getElementById('captchaImage').src = 'captcha?' + Math.random();
    }

    let fieldsChecks = {
      "email": false,
      "captcha": false,

    };

    function validateAndEnableSubmit() {
      let flag = Object.values(fieldsChecks).includes(false);
      console.log("Fields checks:", fieldsChecks);
        console.log("Form valid:", !flag);


      if (!flag) {
        document.getElementById("signInButton").removeAttribute("disabled");
      } else {
        document.getElementById("signInButton").setAttribute("disabled", "");
      }
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
      } else {
        error.innerHTML = "Invalid email address.";
        error.style.color = "red";
        fieldsChecks["email"] = false;
      }

      validateAndEnableSubmit();
    }

    function validateCaptcha()
    {
   let element= document.getElementById("captcha").value;
   let errorMsg=document.getElementById("captchaError");

   if(element.trim()==='')
   {
   errorMsg.innerHTML = "Captcha is required.";
          errorMsg.style.color = "red";
           fieldsChecks["captcha"] = false;
   }
   else if(element.length !== 6)
   {
   errorMsg.innerHTML = "Captcha must be exactly 6 characters long.";
             errorMsg.style.color = "red";
              fieldsChecks["captcha"] = false;
   }
   else
    {
               errorMsg.innerHTML = "";
               fieldsChecks["captcha"] = true;

             }

    validateAndEnableSubmit();
    }

</script>

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container mt-5 mb-5 justify-content-center">
    <div class="card-header text-center">
        <h3>Log In</h3>
    </div>

    <div class="card-body text-dark">

        <span style="color:green"><strong>${msg}</strong></span>
        <span style="color:red"><strong>${captchaError}</strong></span>
        <span style="color:red"><strong>${accountError}</strong></span>
        <span style="color:red"><strong>${emailNotFound}</strong></span>
        <span style="color:red"><strong>${generatedOTP}</strong></span>
         <span style="color:red"><strong>${failedToGenerateOTPError}</strong></span>




        <form action="generateOtp" method="post">
            <!-- Email Field -->
            <div class="mb-3">
            <span id="emailError" ></span>

                <label for="email" class="form-label">Email ID:</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" name="email" oninput="emailValidation()" placeholder="Enter email ID" required>
                </div>
            </div>

            <!-- CAPTCHA Field -->
            <div class="mb-3">
            <span id="captchaError"></span>
                <label for="captcha" class="form-label">Enter CAPTCHA:</label>
                <div class="input-group">
                    <img src="captcha" alt="CAPTCHA Image" id="captchaImage">
                    <input type="text" class="form-control" id="captcha" name="captcha" oninput="validateCaptcha()" placeholder="Enter CAPTCHA" required>
                    <span id="refreshCaptcha" onclick="refreshCaptcha()"><i class="fas fa-sync-alt"></i></span>
                </div>
            </div>

            <!-- Submit Button -->
            <div class="d-flex justify-content-center mt-3">
                <button type="submit" class="btn btn-dark oval-btn bold-text" id="signInButton" disabled>Next</button>
            </div>
        </form>


    </div>
</div>
</body>
</html>
