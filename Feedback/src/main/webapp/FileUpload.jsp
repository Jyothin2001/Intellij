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
        <!--Search-->

                <a class="navbar-brand text-dark" href="Search.jsp"><b>Search </b></a>
      </div>
    </nav>
    <!-- file upload:
             encrypt=saying we are sending multipart from form
             name=for saving -->

     <div class="text-primary"><strong><span style="color:blur;">${image}</span></strong></div>

    <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" id="filename" name="filename">
            <input type="submit" value="upload">
              </form>

              <form action="download" method="get" >
                                        <input type="text" id="filename" name="fileName"  >
                                        <input type="submit" value="Download">
                                   </form>

    </body>
    </html>
