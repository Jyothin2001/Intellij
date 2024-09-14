<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

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
        </div>

        <div class="dropdown">
            <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown"
                    aria-expanded="false">
                Admin
            </button>

            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">


                <li><a class="dropdown-item" href="SubAdminChangePassword"><strong>Change Password</strong></a></li>

               <li><a class="dropdown-item" href="get-Department-Names"><strong>Add Employee</strong></a></li>
               <li><a class="dropdown-item" href="subAdminProfilePage"><strong> Profile Page</strong></a></li>



            </ul>
        </div>

    </div>


</nav>

<div class="container mt-4">
                   <div class=" text-success text-center mb-4">
                       <strong>${message}</strong>
                   </div>

<div class=" text-success text-center mb-4">
                       <strong>${msg}</strong>
                   </div>

<div class=" text-danger text-center mb-4">
                       <strong>${error}</strong>
                   </div>

<div
        class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded  ">
    <div class="card">
        <div style="margin-top: 15px;">
            <h3 style="color:blue; ">
                <center>View Complaint Raise Details </center>

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
                        <th>Allocate Employee</th>
                        <th>Status</th>
                        <th>Submit</th>
                        <th>Delete</th>

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
                                <form action="updateEmployee" method="post">

                                    <input type="hidden" name="complaintId" value="${viewRaiseComplaintUsers.complaintId}">

                                    <select class="form-select status-select" style=" width:200px;" name="employee_id">
                                        <option value="Select">Select</option>
                                        <c:forEach var="employee" items="${employeeNames}">
                                            <!--conditions for retain name like both table id's should match-->
                                            <option value="${employee.employee_id}" ${viewRaiseComplaintUsers.employeeDTO !=null &&
                                                    viewRaiseComplaintUsers.employeeDTO.employee_id==employee.employee_id ? 'selected' : '' }>
                                            ${employee.employeeName}
                                            </option>

                                        </c:forEach>
                                    </select>
                      </td>
                      <td>
                                <select class="form-select status-select" style=" width:150px;" name="status">
                                    <option value="Select">Select</option>
                                    <option value="Pending" ${viewRaiseComplaintUsers.status=='Pending' ? 'selected' : '' }>
                                    Pending</option>
                                    <option value="In Process" ${viewRaiseComplaintUsers.status=='In Process' ? 'selected' : '' }>
                                    In Process</option>
                                    <option value="Completed" ${viewRaiseComplaintUsers.status=='Completed' ? 'selected' : '' }>
                                    Completed
                                    </option>
                                </select>
                       </td>

                       <td>
                       <button type="submit" class="btn btn-primary">Submit</button>
                       </td>
                      </form>



                             <td>

                                        <form action="deactivateEmployeeStatus/${viewRaiseComplaintUsers.employeeDTO.employee_id}" method="post">
                                         <input type="hidden" name="employee_id" value="${viewRaiseComplaintUsers.employeeDTO.employee_id}" />
                                             <button type="submit" class="btn btn-danger">Delete${viewRaiseComplaintUsers.employeeDTO.employee_id}</button>
                                         </form>

                                         <!--<c:if test="${viewRaiseComplaintUsers.employeeDTO != null}">
                                             <form action="deactivateEmployeeStatus/${viewRaiseComplaintUsers.employeeDTO.employee_id}" method="post">
                                                 <input type="hidden" name="employee_id" value="${viewRaiseComplaintUsers.employeeDTO.employee_id}" />
                                                 <button type="submit" class="btn btn-danger">Delete ${viewRaiseComplaintUsers.employeeDTO.employee_id}</button>
                                             </form>
                                         </c:if>-->




                              </td>

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
<!--<c:forEach var="employee" items="${employeeList}">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td>
                <form action="${pageContext.request.contextPath}/employee/deactivate/${employee.employee_id}" method="post">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
-->