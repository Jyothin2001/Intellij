<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Sign up</title>

<!--BootStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon link-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>


<!--Script link-->
  <script src="/issue-management/js/SignUp.js"></script>


<!--css link-->
<link rel="stylesheet" href="/issue-management/css/SignUp.css">

<style>
.form-check-input {
      /* Add any other styles you need, but avoid border-radius if you want the default checkbox look */
      appearance: auto; /* Ensures default checkbox style is applied */
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

   <a class="navbar-brand text-light" href="HomePage"><b>Home</b></a>
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
                     <input type="tel" class="form-control" id="contactNumber" oninput="contactNumberValidation()"  name="contactNumber"  style="border-radius: 15px;"  placeholder="Enter Contact Number"/>
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
              Already have an account?<a href="SignIn.jsp">Sign In</a>
              </div>

             <!--<div style="text-align:center;" class="proxima-nova">
                  Already have an account? <a href="SignIn.jsp">Sign In</a>
                </div>-->

         </form>


     </div>
  </div>
 </body>

</html>
