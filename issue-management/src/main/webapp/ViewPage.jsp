<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User View page</title>

<!--BooteStrap link-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--Font Awesome cdn icon-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>
.form-width {
            width: 500px;
            margin-left: 550px;
            background-color:;
            border-radius:5;
        }

</style>

 <!-- Placeholder styling
 <style>
   ::placeholder {
     font-family: 'Arial', sans-serif;
     font-size: 14px;
     color: #6c757d;
     opacity: 1;
   }
 </style>-->


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
                         <div class=" dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">


                           <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user-->
                           <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80"
                             class="rounded-circle profile-image" alt="Profile Image">

                         </div>

                         <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                           <!--Retain user entered email in editPage , action=edit, ?=may be to differentiate,email=through email-->
                          <!--get action = edit?email=${signUpDTO.email} for get=give action name, post=.jsp-->

                          <li><h4 style="display: block; text-align: center; color: blue;">${UserFirstName}    ${UserLastName}</h4></li>
                           <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit Your Details </strong></a></li>
                           <li><a class="dropdown-item" href="ChangePassword"><strong>Password Reset</strong></a></li>
                           <li><a class="dropdown-item" href="ComplaintRaisePage"><strong>Complaint Raise</strong></a></li>
                           <li><a class="dropdown-item" href="viewComplaintRaise"><strong>View ComplaintRaise</strong></a></li>
                              <li><a class="dropdown-item" href="HomePage"><strong>Log j Out</strong></a></li><!--not working get=action(logout)-->


                            <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> View your Details</strong></a></li>
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

    <div class="card container mt-5 mb-3 shadow-lg p-4 bg-body rounded form-width card border-warning mb-3">
        <div class="card-header text-center" style="background-color: #FF7F50; color: white;">
            <h2 class="mb-0" style="font-family: 'Lucida Handwriting', cursive;">User Details</h2>
        </div>
        <div class="card-body text-dark">
            <div class="d-flex align-items-center mb-4">
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" class="profile-img rounded-circle border border-2 border-dark me-3" style="width: 80px; height: 80px;">
                <h4 class="card-title mb-0">${signUpDTO.firstName} ${signUpDTO.lastName}</h4>
            </div>
            <div class="mb-3">
                <i class="bi bi-envelope-fill me-2 text-primary"></i>
                <strong>Email:</strong> ${signUpDTO.email}
            </div>
            <div class="mb-3">
                <i class="bi bi-telephone-fill me-2 text-primary"></i>
                <strong>Contact Number:</strong> ${signUpDTO.contactNumber}
            </div>
            <div class="mb-3">
                <i class="bi bi-phone-vibrate-fill me-2 text-primary"></i>
                <strong>Alternative Contact Number:</strong> ${signUpDTO.alternateContactNumber}
            </div>
            <div class="mb-3">
                <i class="bi bi-geo-alt-fill me-2 text-primary"></i>
                <strong>Address:</strong> ${signUpDTO.address}
            </div>
        </div>
    </div>


 </body>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>

