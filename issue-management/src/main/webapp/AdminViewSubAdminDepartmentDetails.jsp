<%@ page isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>View Department Admin Details</title>

      <!--BootStrap link-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

 <style>
        .form-width {
          width: 900px;
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
                 <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                 Admin
                             </button>

                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                                <li><a class="dropdown-item" href="AddDepartment"><strong>Add Departments</strong></a></li>
                 <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>View Complaint Raise Details</strong></a></li>
                  <li><a class="dropdown-item" href="getDepartmentName"><strong>Add Department Admin</strong></a></li>
                  <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>

                  </ul>
                </div>

              </div>


            </nav>


      <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width ">
<div class="card">
        <div style="margin-top: 15px;">
          <h3 style="color:blue; ">
            <center>View Sub Admin department Details</center>
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
                                        <th>Name</th>
                                        <th>Department Name</th>
                                        <th>Email</th>
                                        <th>Contact Number</th>
                                        <th>AltContact Number</th>


              </tr>
            </thead>

            <tbody class="table-hover">
              <c:forEach var="subDepartmentDetails" items="${subDepartmentDetails}" varStatus="status">
                <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${subDepartmentDetails.id}</td>
                                           <td>${subDepartmentDetails.adminName}</td>
                                           <td>${subDepartmentDetails.departmentName}</td>
                                            <td>${subDepartmentDetails.email}</td>
                                            <td>${subDepartmentDetails.contactNumber}</td>
                                            <td>${subDepartmentDetails.alternateContactNumber}</td>


                 <!--return page path http://localhost:8080/myapp, then ${pageContext.request.contextPath} would return /myapp.-->


                </tr>
              </c:forEach>
            </tbody>
            </div>
          </table>
        </div>
      </div>
    </body>
    <div>
    <div>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    </html>