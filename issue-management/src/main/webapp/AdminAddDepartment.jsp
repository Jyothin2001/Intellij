<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Add Department</title>

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
</head>
<script>
    let fieldsChecks = {
                departmentName: false,
                address: false,
                area: false
            };

            function validateForm() {
                // Validate Department Name
                const departmentName = document.getElementById('departmentName');
                const departmentError = document.getElementById('departmentError');
                const departmentNameValue = departmentName.value.trim();
                if (departmentNameValue === "") {
                    departmentError.innerHTML = "Department name is required.";
                    departmentError.style.color = "red";
                    console.log("Department name not entered");
                    fieldsChecks["departmentName"] = false;
                } else {
                    departmentError.innerHTML = "";
                    console.log("Correct department name");
                    fieldsChecks["departmentName"] = true;
                }

                // Validate Address
                const address = document.getElementById('address');
                const addressError = document.getElementById('addressError');
                const addressValue = address.value.trim();
                if (addressValue === "") {
                    addressError.innerHTML = "Address is required.";
                    addressError.style.color = "red";
                    console.log("Address not entered");
                    fieldsChecks["address"] = false;
                } else {
                    addressError.innerHTML = "";
                    console.log("Correct address");
                    fieldsChecks["address"] = true;
                }

                // Validate Area
                const area = document.getElementById('area');
                const areaError = document.getElementById('areaError');
                const areaValue = area.value.trim();
                if (areaValue === "") {
                    areaError.innerHTML = "Area is required.";
                    areaError.style.color = "red";
                    console.log("Area not entered");
                    fieldsChecks["area"] = false;
                } else {
                    areaError.innerHTML = "";
                    console.log("Correct area");
                    fieldsChecks["area"] = true;
                }

                // Enable or disable the submit button based on field validation
                document.getElementById('submit').disabled = !Object.values(fieldsChecks).every(Boolean);
            }
        </script>

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
                  <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                  Admin
                              </button>

                   <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                   <li><a class="dropdown-item" href="viewUserDetails"><strong>View User Details</strong></a></li>
                  <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>View Complaint Raise Details</strong></a></li>
                   <li><a class="dropdown-item" href="getDepartmentName"><strong>AddDepartment Admin</strong></a></li>

                   </ul>
                 </div>

</div>
</nav>


    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;"><b><center>Sign In Form</center></b></h3>
        </div>-->

              <div style = "margin-top: 15px;">
                   <h2 style= "color:blue; "><center>Add Department</center></h2>
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

      <form action="saveDepartment" method="post">

<div class="text-primary"><b>${msgDepartment}</b></div>
<div class="text-danger"><b>${errorDepartment}</b></div>

           <!--Text: First Name-->
              <div style="margin-bottom:2px;" class="form-group">
                  <span id="departmentError"></span><br>
                  <b></b>
                   <label for="departmentName" class="form-label"><b></b>Department Name:</label>
                     <div class="input-icon">
                        <!--<i class="fas fa-user"></i>-->
                     <input type="text" class="form-control" id="departmentName" name="departmentName"  onblur="validateForm()" placeholder="Enter Department Name" style="border-radius: 15px;" />
               </div>
               </div>



                <!--textarea: address-->
              <div style="margin-bottom:2px;"  class="form-group">
                 <span id="addressError"></span><br>
                 <b></b>
                 <label for="address" class="form-label">Address:</label>
                 <div class="input-icon">
                 <!--words are not visible if other css override the placeholder -->
                 <!--<i class="fa fa-map-marker" aria-hidden="true"></i>-->
                 <textarea class="form-control " id="address" placeholder="Enter Address" onblur="validateForm()" name="address" style="border-radius: 15px;" ></textarea>
                </div>
                </div>

<!--text: Area-->
              <div style="margin-bottom:2px;"  class="form-group">
                 <span id="areaError"></span><br>
                 <b></b>
                 <label for="area" class="form-label">Area:</label>
                 <div class="input-icon">
                 <!--words are not visible if other css override the placeholder
                 <i class="fa fa-map-marker" aria-hidden="true"></i>-->
                 <input type="text" class="form-control " id="area" onblur="validateForm()" placeholder="Enter Area" name="departmentArea" style="border-radius: 15px;" >
                </div>
                </div>
<br>



              <div class="d-grid gap-2" style="margin-bottom:10px;">
                                <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="Log In" disabled>
                            </div><br>




         </form>


     </div>
  </div>
 </body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>
