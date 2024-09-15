<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Employee Profile</title>

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
          <button
              class="btn dropdown-toggle px-4 py-2 rounded-pill"
              style="font-weight: bold; background-color: white; color: grey; border-color: grey;"
              id="dropdownMenuButton1"
              data-bs-toggle="dropdown"
              aria-expanded="false">
              <i class="bi bi-person-circle"></i> ${employeeName}
          </button>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

        <li><a class="dropdown-item" href="editEmployeeDetails"><strong>Edit Your Details</strong></a></li>

        <li><a class="dropdown-item" href="viewComplaintRaiseDetails"><strong>View Complaint Details</strong></a></li>

        <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>

      </ul>
    </div>

  </div>


</nav>



    <!--Employee welcome page-->


<div class="text-center" style="color: green; position: fixed; top: 100px; left: 50%; transform: translateX(-50%); width: 100%;">
  <h5><b>${welcomeMsg}</b></h5>
</div>






</body>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


</html>