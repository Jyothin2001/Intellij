<%@ page isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>View User Details</title>

      <!--BootStrap link-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

      <style>
        .form-width {
          width: 500px;
          margin-left: 500px;
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
            <a class="navbar-brand text-light" href="AdminPage"><b>Admin</b></a>
          </div>

          <div class="dropdown">
            <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown"
              aria-expanded="false">
              Admin
            </button>

            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
              <li><a class="dropdown-item" href="viewUserDetails"><strong>View User Details</strong></a></li>
              <li><a class="dropdown-item" href="viewSubAdminDepartmentDetails"><strong>view Department Admin Details</strong></a></li>
              <li><a class="dropdown-item" href="AddDepartment"><strong>Add Department</strong></a></li>
              <li><a class="dropdown-item" href="getDepartmentName"><strong>Add Department Admin</strong></a></li>

              <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>
            </ul>
          </div>

        </div>


      </nav>
      <div
        class="card border-dark container mt-5 mb-3 justify-content-center  border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width ">

        <!--<div class="card-header">
           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
        </div>-->

        <div style="margin-top: 15px;">
          <h3 style="color:blue; ">
            <center>Search</center>
          </h3>
        </div>

        <!--text/word colors-->
        <div class="card-body text-dark">


          <form action="searchComplaintTypeAndCity" method="post">

            <!---dropdown select issue-->

            <select class="form-select custom-select-width" id="complaintType" style="width:400px;"
              name="complaintType">
              <option value="">Select Department</option>
              <c:forEach items="${departments}" var="departmentName">
                <option value="${departmentName.departmentName}">${departmentName.departmentName}</option>
              </c:forEach>
            </select>
            <br>

            <select class="form-select custom-select-width" id="city" style="width:400px;" name="city">
              <option value="">Select a city</option>
              <c:forEach items="${cities}" var="city">
                <option value="${city}">${city}</option>
              </c:forEach>
            </select>


            <!---City
                             <div style="margin-bottom:2px;">
                                 <span id="cityNameError" class="text-danger"></span>
                                 <label for="city" class="form-label"></label>
                                 <b>City:</b>
                                 <input type="text" class="form-control" id="city" style="width:400px;" name="city">

                             </div>-->
            <br>

            <div class="d-grid gap-2" style="margin-bottom:10px; align-item:center;width:100px;">
              <input type="submit" id="submit" value="Apply" class="btn btn-primary ">
            </div>

          </form>
        </div>
      </div>


       <div class="container mt-4">
                   <div class=" text-success text-center mb-4">
                       <strong>${NoComplaints}</strong>
                   </div>
                   <div class=" text-success text-center mb-4">
                                          <strong>${msg}</strong>
                                      </div>
        <div
          class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded  ">
          <div class="card">
            <div style="margin-top: 15px;">
              <h3 style="color:blue; ">
                <center>View Complaint Raise Details</center>
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
                      <th>User Id</th>
                      <th>Allocate Department</th>
                      <th>Status</th>
                      <th>Submit</th>

                    </tr>
                  </thead>

                  <tbody class="table-hover">
                    <c:forEach var="viewRaiseComplaintUsers" items="${viewRaiseComplaint}" varStatus="status">
                      <tr>
                        <td>${status.index + 1}</td>
                        <td>${viewRaiseComplaintUsers.complaintId}</td>
                        <td>${viewRaiseComplaintUsers.complaintType}</td>
                        <td>${viewRaiseComplaintUsers.country}</td>
                        <td>${viewRaiseComplaintUsers.state}</td>
                        <td>${viewRaiseComplaintUsers.city}</td>
                        <td>${viewRaiseComplaintUsers.area}</td>
                        <td>${viewRaiseComplaintUsers.address}</td>
                        <td>${viewRaiseComplaintUsers.description}</td>
                        <td>${viewRaiseComplaintUsers.signUpDTO.id}</td>

                        <td>
                          <form action="updateDepartment" method="post">
                            <input type="hidden" name="complaintId" value="${viewRaiseComplaintUsers.complaintId}">

                            <select class="form-select status-select" style=" width:200px;" name="departmentId">
                              <option value="Select">Select</option>
                              <c:forEach var="department" items="${departments}">
                                <!--conditions for retain name like both table id's should match-->
                                <option value="${department.id}" ${viewRaiseComplaintUsers.departmentDTO !=null &&
                                  viewRaiseComplaintUsers.departmentDTO.id==department.id ? 'selected' : '' }>
                                  ${department.departmentName}</option>

                              </c:forEach>
                            </select>
                        </td>
                        <td>
                          <select class="form-select status-select" style=" width:150px;" name="status">
                            <option value="Select">Select</option>
                            <option value="Completed" ${viewRaiseComplaintUsers.status=='Completed' ? 'selected' : '' }>
                              Completed</option>
                            <option value="In Process" ${viewRaiseComplaintUsers.status=='In Process' ? 'selected' : ''
                              }>
                              In Process</option>
                            <option value="Not Completed" ${viewRaiseComplaintUsers.status=='Not Completed' ? 'selected' : '' }>
                              Not Completed</option>
                          </select>
                        </td>
                        <td>
                          <button type="submit" class="btn btn-primary">Submit</button>
                        </td>
                        </form>


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
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
          integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
          crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
          integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
          crossorigin="anonymous"></script>

    </html>