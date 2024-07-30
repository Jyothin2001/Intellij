<%@ page isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <title>Complaint Raise View</title>

      <!--BootStrap link-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

 <style>
        .form-width {
          width: 1000px;
          margin-left: 300px;
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

                    <!--get action = edit?email=${signUpDTO.email}-->
                   <li><h4 style="display: block; text-align: center; color: blue;">${UserFirstName}    ${UserLastName}</h4></li>
                                 <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit Your Details </strong></a></li>
                                 <li><a class="dropdown-item" href="PasswordReset"><strong>Password Reset</strong></a></li>
                                 <li><a class="dropdown-item" href="view-Page"><strong>View Your Details</strong></a></li>
                                 <li><a class="dropdown-item" href="ComplaintRaisePage"><strong>Complaint Raise</strong></a></li>
                                    <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li><!--not working get=action(logout)-->




                     <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>
                  </ul>
                </div>

              </div>


            </nav>


      <div
        class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width ">

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

        <div style="margin-top: 15px;">
          <h3 style="color:blue; ">
            <center>Complaint Raise View</center>
          </h3>
        </div>

        <!--text/word colors-->
        <div class="card-body text-dark">

        <div class="table-responsive">

          <table class="table table-bordered table-striped ">
            <thead class="thead-dark">
              <tr>
                <th>S.NO</th>
                <th>ID</th>
                <th>Complaint Type</th>
                <th>Country</th>
                <th>State</th>
                <th>City</th>
                <th>Area</th>
                <th>Address</th>
                <th>Description</th>
                <th>Edit</th>
              </tr>
            </thead>

            <tbody class="table-hover">
              <c:forEach var="complaintRaise" items="${viewComplaintRaise}" varStatus="status">
                <tr>

                  <td>${status.index+1}</td>
                  <td>${complaintRaise.complaintId}</td>
                  <td>${complaintRaise.complaintType}</td>
                  <td>${complaintRaise.country}</td>
                  <td>${complaintRaise.state}</td>
                  <td>${complaintRaise.city}</td>
                  <td>${complaintRaise.area}</td>
                  <td>${complaintRaise.address}</td>
                  <td>${complaintRaise.description}</td>
                 <!--return page path http://localhost:8080/myapp, then ${pageContext.request.contextPath} would return /myapp.-->

                  <td><a href="${pageContext.request.contextPath}/editComplaintRaise/${complaintRaise.complaintId}">Edit</a></td>

                </tr>
              </c:forEach>
            </tbody>
            </div>
          </table>
        </div>
      </div>
    </body>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    </html>