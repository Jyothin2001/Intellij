<%@ page isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <title>Profile</title>

    <!--Bootstrap link-->
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


      <style>
        .form-width {
          width: 500px;
          margin-left: 550px;
          background-color: ;
          border-radius: 5;
        }
      </style>
    </head>

    <body>

      <nav class="navbar  navbar-light bg-primary ">
        <div class="container-fluid">
         <div class="navbar-header">

            <!-- Add your logo here -->
            <a class="navbar-brand" href="#">
              <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100"
                height="50">
            </a>

            <a class="navbar-brand text-light" href="HomePage"><b>Log Out</b></a>
          </div>

          <div class="dropdown">
            <button class=" dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">${username}
            </button>

            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
               <li><a class="dropdown-item" href="viewUserDetails"><strong>ViewUserDetails</strong></a></li>

              <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>ViewRaiseComplaintDetails</strong></a></li>

              <li><a class="dropdown-item" href="AddDepartment"><strong>AddComplaints</strong></a></li>

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



      <div class="card border-dark container mt-5 mb-5 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width">
        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

        <div style="margin-top: 15px;">
          <h1 style="color:blue; ">
            <center></center>
          </h1>
        </div>


        <!--Form-->

        <form action="admin" method="post">

       <div style="color:green;"><h6><b>${AdminProfilePageMessage}</b></h6></div>

       </form>
       </div>
         </div>



       </body>



      <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


</html>