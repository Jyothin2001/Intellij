<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Sign up</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


 <!--css link-->
 <link rel="stylesheet" href="css/SignUp.css">

 <!-- Placeholder styling -->
 <style>
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }
 </style>

 <!--Script link-->

  <script>
  <!--<script src="/issue-management/js/SignUp.js">-->
  let fieldsChecks =
  {
          "email" : false,
  	    "password" : false,

  }

  function validate()
  {
      let flag = false;

      for(let [key, value] of Object.entries(fieldsChecks))
      {

          if(value === false)
          {
              flag = true;
              break;
          }
      }

      if(!flag)
      {
        document.getElementById("submit").removeAttribute("disabled");
      }
      else
      {
          document.getElementById("submit").setAttribute("disabled","");
      }
  }

        function emailValidation() {
            let element = document.getElementById("email");
            let error = document.getElementById("emailError");

            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            let trimmedValue = element.value.trim();

            if (trimmedValue === "") {
              error.innerHTML = "Please enter an email address.";
              error.style.color = "red";
              fieldsChecks["email"] = false;
            } else if (!emailRegex.test(trimmedValue)) {
              error.innerHTML = "Invalid email address.";
              error.style.color = "red";
              fieldsChecks["email"] = false;
            } else {
              error.innerHTML = "";
              fieldsChecks["email"] = true;
            }

            validate();
          }

            function passwordValidation()
            {
                let element = document.getElementById("password");
                let error = document.getElementById("passwordError");

               if(element.value.length != "0")
               {
                    error.innerHTML = "";
                    fieldsChecks["password"] = true;
                }
                else
                {
                    error.innerHTML = "please Enter password";
                    error.style.color="red"
                    fieldsChecks["password"] = false;


                }
                validate();
            }


            <!--this button disabled is not working in js file-->
                 function disableButton() {
             var accountLocked = "${accountLocked}";
             if (accountLocked === "true") {
                 document.getElementById("submit").disabled = true;
             }
         }
         window.onload = disableButton;

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

   <a class="navbar-brand text-light" href="index.jsp"><b>Home</b></a>
 </div>
</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 5px;">
                   <h2 style= "color:blue; "><center>Sign In</center></h2>
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
                   <div class="text-primary"><h6><b>${msg}</b></h6></div>
                      <div class="text-primary"><h6><b>${error}</b></h6></div>
                      <div class="text-primary"><h6><b>${accountLocked}</b></h6></div>

<!--Form-->
<form action="signin" method="post">

<input type="hidden" id="accountLocked" value="true">


                                   <!--email: Email-->
                <div style="margin-bottom:0px;" class="form-group">
                   <span id="emailError"></span><br>
                   <label for="email" class="form-label"><b></b></label>
                   <div class="input-icon">
                   <i class="fa-regular fa-envelope"></i>
                   <input type="email" class="form-control" id="email" onblur="emailValidation()" name="email" placeholder="Enter Email">
                 </div>
                 </div>

                         <!--Text: password-->
               <div style="margin-bottom:24px;" class="form-group">
                    <span id="passwordError"></span><br>
                    <label for="password" class="form-label"><b></b></label>
                    <div class="input-icon">
                    <i class="fa-solid fa-key"></i>
                    <input type="password" class="form-control"  id="password" onblur="passwordValidation()" name="password" placeholder="Enter password">
               </div>
               </div>

                   <div>
                   <a href="ForgotPassword">Forgot Password?</a>

                   </div>



              <div class="d-grid gap-2" style="margin-bottom:10px;">
                  <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="SignIn">
              </div>

         </form>
 </div>
  </div>

 </body>
</html>
