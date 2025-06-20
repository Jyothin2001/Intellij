<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Sign up</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

 <style>
 <!-- icons styling-->

         .form-container {
             width: 300px;
             margin: 0 auto;
             padding: 20px;
             border: 1px solid #ccc;
             border-radius: 10px;
         }
         .form-group {
             margin-bottom: 15px;
         }
         .form-label {
             display: block;
             margin-bottom: 5px;
             font-weight: bold;
         }
         .form-control {
             width: 100%;
             padding: 10px;
             font-size: 14px;
         }
         .small-placeholder::-webkit-input-placeholder {
             font-size: 18px;
         }
         .small-placeholder:-moz-placeholder {
             font-size: 18px;
         }
         .small-placeholder::-moz-placeholder {
             font-size: 18px;
         }
         .small-placeholder:-ms-input-placeholder {
             font-size: 18px;
         }
         .input-icon {
             position: relative;
         }
         .input-icon i {
             position: absolute;
             left: 10px;
             top: 50%;
             transform: translateY(-50%);
             font-size: 14px;
             color: #aaa;
         }
         .input-icon input
         {
             padding-left: 30px;
         }
         :root {
             --blue-100: #cce4ff; <!-- var(--blue-100) Replace with your desired blue shade -->
         }

         .form-width {
             width: 500px;
             margin-left: 550px;
             background-color:;
             border-radius:5px;
         }

 body
 {
 background-color:;
 }

<!-- password styling-->
    .form-group {
        margin-bottom:24px;
        position: relative;
        width: 100%;
    }
    .password-container {
        position: relative;
        width: 100%; /* Full width of the parent container */
    }

    .form-control {
        width: 100%; /* Full width of the container */
        padding: 10px 40px 10px 10px; /* Add space on the right for the icon */
        border-radius: 15px;
        box-sizing: border-box;
    }

    .pass-icon {
        position: absolute;
        top: 50%;
        right: 10px; /* Space from the right edge */
        width: 25px; /* Adjust icon size */
        height: 25px;
        cursor: pointer;
        transform: translateY(-50%); /* Center vertically */
    }

<!-- Placeholder styling
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }-->



 </style>


<script>

<!--password-->
var a;
function pass()
{
if(a==1)
{
document.getElementById('password').type='password';
document.getElementById('pass-icon').src='https://img.icons8.com/?size=100&id=121539&format=png&color=000000';
a=0;
}
else
{
document.getElementById('password').type='text';
document.getElementById('pass-icon').src='https://img.icons8.com/?size=100&id=986&format=png&color=000000';
a=1;
}
}

  <!--this button disabled is not working in js file-->


                           function disableButton() {
                       var accountLocked = "${accountLocked}";
                       if (accountLocked === "true") {
                           document.getElementById("signinsubmit").disabled = true;
                       }
                   }
                   window.onload = disableButton;

                    <!--email and password validation-->

                  function validateEmail(email) {
                      const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                      return re.test(email);
                  }

                  function validatePassword(password) {
                      return password.length >= 6;  // Example rule: Password must be at least 6 characters long
                  }

                  function validateForm() {
                      const email = document.getElementById('email').value;
                      const password = document.getElementById('password').value;
                      const submitBtn = document.getElementById('submitBtn');
                      const emailError = document.getElementById('emailError');
                      const passwordError = document.getElementById('passwordError');

                      let isValid = true;

                      // Check if email is empty
                      //trim() removes whitespace from both ends of a string. This includes spaces, tabs, and newline characters.
                      if (email.trim() === '') {
                          emailError.textContent = 'Email is required';
                          emailError.style.color = "red";
                          isValid = false;
                      } else if (!validateEmail(email)) {
                          emailError.textContent = 'Invalid email address';
                          emailError.style.color = "red";
                          isValid = false;
                      } else {
                          emailError.textContent = '';
                          console.log("valid email");
                      }

                      // Check if password is empty
                      if (password.trim() === '') {
                          passwordError.textContent = 'Password is required';
                          passwordError.style.color = "red";
                          isValid = false;
                      } else if (!validatePassword(password)) {
                          passwordError.textContent = 'Password must be at least 6 characters long';
                          passwordError.style.color = "red";
                          isValid = false;
                      } else {
                          passwordError.textContent = '';
                          console.log("valid password");
                      }

                      submitBtn.disabled = !isValid;
                  }


 </script>
                        <!--submitBtn.disabled = !isValid;
                           !isValid evaluates to !false which is true.
                           submitBtn.disabled = true; (The button is disabled).-->
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

      <!-- <div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div class="card-header">
                   <h3 style= "color:blue; "><center>Admin Login</center></h3>
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
                      <div style="color:red;"><h6><b>${errorAdminMessage}</b></h6></div>




<!--Form-->
<form action="admin" method="post">


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" onblur="validateForm()" name="email" style="border-radius: 15px;" placeholder="Enter Email">
                 </div>
                 </div>


                         <!--Text: password-->
                    <div class="form-group">
                             <span id="passwordError"></span><br>
                             <label for="password" class="form-label"><b></b></label>
                             <div class="password-container">
                                 <input type="password" class="form-control" id="password" oninput="validateForm()" style="border-radius: 15px;" name="password" placeholder="Enter password">
                                 <img src="https://img.icons8.com/?size=100&id=121539&format=png&color=000000" onclick="pass()" class="pass-icon" id="pass-icon">
                             </div>
                         </div>




              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg " id="submitBtn"  value="Submit" disabled>

              </div>
                      </form>

 </div>
  </div>



 </body>
</html>

