<%@ page  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-secondary">

      <div class="container-fluid">
      <!-- Add your logo here -->
                              <a class="navbar-brand" href="#">
                                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70" >
                              </a>

      <a class="navbar-brand text-dark" href="index.jsp"><b>Home</b></a>
        <a class="navbar-brand text-dark" href="EventRegister.jsp"><b>Event Register form</b></a>

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

<form action="submit" method ="post">


    <div class="text-primary"><strong><span style="color:blur;">${name}</span></strong></div>

    </form>
    </body>
    </html>
