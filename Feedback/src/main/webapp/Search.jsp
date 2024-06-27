<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/Feedback/js/Feedback.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
      <div class="container-fluid">
      <!-- Add your logo here -->
                              <a class="navbar-brand" href="#">
                                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70" >
                              </a>

        <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
        <a class="navbar-brand text-dark" href="#"><b></b></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-link active" aria-current="page" href="#"></a>

          </div>
        </div>
      </div>
    </nav>


    <div class="container mt-5">
        <form id="feedbackForm" name="recommend" action="search" method="post" class="needs-validation" novalidate>
<!-- Dropdown -->
            <div class="form-group">
            <span id="errorservice"></span>
                <label for="service" style="width:450px; margin-left:300px;">Rate our service:</label>
                <select class="form-control" style="width:520px; margin-left:300px;"  onblur="serviceValidation()" id="service" name="rating" required>
                    <option value="" disabled selected ${feedbackDTO.rating ==null ? 'selected' :'' }>Select an option</option>
                    <option value="excellent" ${feedbackDTO.rating eq 'excellent' ? 'selected' : ''}>Excellent</option>
                    <option value="good" ${feedbackDTO.rating eq 'good' ? 'selected' : ''}>Good</option>
                    <option value="average" ${feedbackDTO.rating eq 'average' ? 'selected' : ''}>Average</option>
                    <option value="poor" ${feedbackDTO.rating eq 'poor' ? 'selected' : ''}>Poor</option>
                </select>
                <div class="invalid-feedback">
                    Please select a rating.
                </div>
            </div>


                 <div>
                  <input type="submit" style="width:90px; margin-left:500px;" id="submitBtn"  value="submit" class="btn btn-primary" >
                   </div>


        </form>
    </div>

<div class="d-flex justify-content-center mt-3 mb-2 align-items-center vh-80">
<div class="card" ><!--do not give width size externally card is automatically grow(responsive)--!>
  <div class="card-body">


  <strong style="color:green;">Search result for , ${Rating}</strong>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col"> Name </th>
      <th scope="col">Rating </th>
      <th scope="col">Comments</th>
      <th scope="col">Recommend</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
      <th scope="col">Download</th>
    </tr>
  </thead

  <tbody>



       <c:forEach items="${listOfRatings}" var="name1">
              <tr>
              <td>${name1.id}</td>
              <td>${name1.name}</td>
              <td>${name1.rating}</td>
              <td>${name1.comments}</td>
              <td>${name1.recommend}</td>
              <!--do not give method="" because by default anchor tag has Get--!>
              <td><a href="feedback-edit?id=${name1.id}">Edit </td>
              <td><a href="feedback-delete?id=${name1.id}">Delete</td>

                               <td>
                                <form action="download" method="get" >
                                        <input type="text" id="filename" name="fileName"  >
                                        <input type="submit" value="Download">
                                   </form>

</td>

              </tr>
         </c:forEach>

  </tbody>

</table>
</div>
</div>
</div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>
