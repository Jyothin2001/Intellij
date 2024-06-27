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
        <a class="navbar-brand text-dark" href="FileUpload.jsp"><b>File upload </b></a>
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

    <div class="container mt-5">
        <h2>Feedback Form</h2>
        <form id="feedbackForm" name="recommend" action="feedback" method="post" class="needs-validation" novalidate>

<span style="color:red;">
<c:forEach items="${error}" var="objectError">
${objectError.defaultMessage}<br/>
</c:forEach>
</span>

            <!-- Textbox -->
            <div class="form-group">
            <span id="errorname"></span>


            <!--other wise it will add/new insert error comes can not convert int to integer-->
        <input type="hidden" name="id" value="${feedbackDto1.id!=null ? feedbackDto1.id:'' }"/>


                <label for="name">Name:</label>
                <input type="text" onblur="nameValidation()" value="${feedbackDto1.name}" class="form-control" id="name1" name="name" required>
                <div class="invalid-feedback">
                    Please enter your name.
                </div>
            </div>


            <!-- Dropdown -->
            <div class="form-group">
            <span id="errorservice"></span>
                <label for="service">Rate our service:</label>
                <select class="form-control" onblur="serviceValidation()" id="service" name="rating" required>
                    <option value="" disabled selected ${feedbackDto1.rating ==null ? 'selected' :'' }>Select an option</option>
                    <option value="excellent" ${feedbackDto1.rating eq 'excellent' ? 'selected' : ''}>Excellent</option>
                    <option value="good" ${feedbackDto1.rating eq 'good' ? 'selected' : ''}>Good</option>
                    <option value="average" ${feedbackDto1.rating eq 'average' ? 'selected' : ''}>Average</option>
                    <option value="poor" ${feedbackDto1.rating eq 'poor' ? 'selected' : ''}>Poor</option>
                </select>
                <div class="invalid-feedback">
                    Please select a rating.
                </div>
            </div>

                                       <!-- Textarea -->
                                        <div class="form-group">
                                        <span id="errorcomments"></span>
                                            <label for="comments">Comments:</label>
                                            <textarea class="form-control" id="comments" name="comments" rows="3" required>${feedbackDto1.comments}</textarea>
                                            <div class="invalid-feedback">
                                                Please provide your comments.
                                            </div>
                                        </div>


                <div class="invalid-feedback"  >
                    Please select an option.
                </div>


            <!-- Radio Buttons -->
                        <div class="form-group">
                        <span id="errortrecommend"></span>
                            <label>Would you recommend us to a friend?</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="recommend_yes" ${feedbackDto1.recommend eq 'yes' ? 'checked' : ''} name="recommend" value="yes" required>
                                <label class="form-check-label" for="recommend_yes">Yes</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="recommend_no" ${feedbackDto1.recommend eq 'no' ? 'checked' : ''} name="recommend" value="no" required>
                                <label class="form-check-label" for="recommend_no">No</label>
                            </div>


            <!-- Checkbox -->
                        <div class="form-group">
                        <span id="errorterms"></span>
                            <label for="terms">Agree to terms and conditions:</label><br>
                            <input type="checkbox" ${feedbackDto1.condition eq 'checked' ? 'checked' : ''}  id="terms"  onblur="termsValidation()" value="checked" name="condition" required>
                            <label for="terms">I agree</label>
                            <div class="invalid-feedback">
                                You must agree before submitting.
                            </div>
                        </div>
           <!-- Submit Button -->
            <button type="submit" class="btn btn-primary" id="submitBtn">Submit</button>
        </form>


    </div>



    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>
