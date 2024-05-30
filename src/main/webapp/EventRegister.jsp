<%@ page  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Event Registration Form</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- Bootstrap JS (Optional, for certain Bootstrap features) -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
    <h2>Event Registration Form</h2>
    <form action="submit" method="post" onsubmit="validateForm()" class="needs-validation" novalidate>
        <div class="form-group">
        <span style="color:red;">
                                                    <c:forEach items="${error}" var="objectError">
                                                        ${objectError.defaultMessage}
                                                    </c:forEach>
                                       </span>
            <label for="fullname">Full Name:</label>
            <input type="text" class="form-control"   id="fullname" name="fullname">
            <div class="invalid-feedback" id="fullname-feedback">Please enter your full name.</div>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email">
            <div class="invalid-feedback" id="email-feedback">Please enter a valid email address.</div>
        </div>

        <div class="form-group">
            <label>Gender:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="male" name="gender" value="male">
                <label class="form-check-label" for="male">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="female" name="gender" value="female">
                <label class="form-check-label" for="female">Female</label>
            </div>
            <div class="invalid-feedback" id="gender-feedback">Please select your gender.</div>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>


        <div class="form-group">
            <label for="event">Event:</label>
            <select class="form-control" id="event" name="event">
                <option value="">Select event</option>
                <option value="Weddings">Weddings</option>
                <option value="Birthday parties">Birthday parties</option>
                <option value="Anniversaries">Anniversaries</option>
                <option value="Seminars">Seminars</option>
                <option value="Religious gatherings.">Religious gatherings.</option>
            </select>
            <div class="invalid-feedback" id="event-feedback">Please select an event.</div>
        </div>
        <div class="form-group">
                    <label for="comments">Comments:</label>
                    <textarea class="form-control" id="comments" name="comments" rows="4"></textarea>
                </div>


        <button type="submit" class="btn btn-primary" id="submitBtn"  >Submit</button>
    </form>
</div>


</body>
</html>
