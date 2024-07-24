<%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>User Sign up</title>

            <!--BootStrap link-->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

            <!--Font Awesome cdn icon link-->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />


            <!--Script link
            <script src="/issue-management/js/ComplaintRaise.js"></script>-->
<script>


        <!-- JavaScript validation functions-->


        function descriptionValidation() {
            const description = document.getElementById('description');
            const descriptionError = document.getElementById('descriptionError');
            if (description.value.trim().length < 10) {
                descriptionError.textContent = 'Description must be at least 10 characters long.';
                return false;
            } else {
                descriptionError.textContent = '';
                return true;
            }
        }


        function validateForm() {
            const isValidDescription = descriptionValidation();

            const submitButton = document.getElementById('submit');
            if (isValidDescription) {
                submitButton.disabled = false;
            } else {
                submitButton.disabled = true;
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            // Disable the submit button by default
            document.getElementById('submit').disabled = true;

            // Attach validation event listeners
            document.getElementById('description').addEventListener('input', validateForm);

        });

</script>

            <!--css link
            <link rel="stylesheet" href="/issue-management/css/SignUp.css">-->

            <style>
                .form-check-input {
                    /* Add any other styles you need, but avoid border-radius if you want the default checkbox look */
                    appearance: auto;
                    /* Ensures default checkbox style is applied */
                }

                        .form-width {
                          width: 500px;
                          margin-left: 550px;
                          background-color: ;
                          border-radius: 5;
                        }

            </style>
        </head>

        <body>

            <nav class="navbar navbar navbar-light bg-primary ">
                <div class="container-fluid">
                    <div class="navbar-header">

                        <!-- Add your logo here -->
                        <a class="navbar-brand" href="#">
                            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz"
                                width="100" height="50">
                        </a>

                        <a class="navbar-brand text-light" href="index.jsp"><b>Home</b></a>
                        <a class="navbar-brand text-light" href="SignIn.jsp"><b>Sign IN</b></a>
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
                                  <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>
                                  <li><a class="dropdown-item" href="PasswordReset.jsp"><strong>Password Reset</strong></a></li>
                                  <li><a class="dropdown-item" href="view-Page"><strong>View</strong></a></li>
                                  <li><a class="dropdown-item" href="viewComplaintRaise"><strong>view ComplaintRaise</strong></a></li>


                                   <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>
                                </ul>

                </div>
            </nav>


            <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width ">

               <!-- <div class="card-header">
                    <h3 style="font-family:Lucida Handwriting, cursive;;"><b>
                            <center>Sign In Form</center>
                        </b></h3>
                </div>-->

                <div style="margin-top: 15px;">
                    <h2 style="color:blue; ">
                        <center>Edit Complaint </center>
                    </h2>
                </div>

                <!--text/word colors-->
                <div class="card-body text-dark">

                    <!---showing errors--!>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>
<form action="complaintRaise" method="post">

                <!---dropdown select issue-->
                <div style="margin-bottom:2px;">
                    <span id="complaintTypeError" class="text-danger"></span>
                    <label for="complaintType" class="form-label"></label>
                    <b>Complaint Type:</b>
                    <select class="form-select custom-select-width" id="complaintType" value=${} name="complaintType" readonly required>
                        <option value="0">Select</option>
                        <option value="Electric issue">Electric issue</option>
                        <option value="Water Supply">Water Supply</option>
                        <option value="Network Problem">Network Problem</option>
                        <option value="System Problem">System Problem</option>
                        <option value="Water Problem">Water Problem</option>
                    </select>
                </div>
                <br>

                <!---Country --->
                <div style="margin-bottom:2px;">
                    <span id="countryNameError" class="text-danger"></span>
                    <label for="countryName" class="form-label"></label>
                    <b>Country:</b>
                    <select class="form-select custom-select-width" id="countryName" style="border-radius: 15px;" name="country" ${} placeholder="Enter country" readonly required>
                        <!-- Countries will be loaded here by JavaScript -->
                    </select><br>
                </div>

                <!---State --->
                <div style="margin-bottom:2px;">
                    <span id="stateNameError" class="text-danger"></span>
                    <label for="state" class="form-label"></label>
                    <b>State:</b>
                    <select class="form-select custom-select-width" id="state" name="state" ${} style="border-radius: 15px;" readonly required>
                        <!-- States will be loaded here by JavaScript -->
                    </select>
                </div>
                <br>

                <!---City --->
                <div style="margin-bottom:2px;">
                    <span id="cityNameError" class="text-danger"></span>
                    <label for="city" class="form-label"></label>
                    <b>City:</b>
                    <select class="form-select custom-select-width" id="city" style="border-radius: 15px;" name="city" ${} placeholder="Enter city" readonly required>
                        <!-- Cities will be loaded here by JavaScript -->
                    </select>
                </div>

                <div style="margin-bottom:2px;">
                    <span id="areaError" class="text-danger"></span><br>
                    <b>Area:</b>
                    <label for="area" class="form-label"></label>
                    <input type="text" class="form-control" id="area" style="border-radius: 15px;" name="area" ${} placeholder="Enter area" readonly required>
                </div>

                <div style="margin-bottom:2px;">
                    <span id="errorAddress" class="text-danger"></span><br>
                    <b>Address:</b>
                    <label for="address" class="form-floating"></label>
                    <textarea class="form-control" placeholder="Enter address" style="border-radius: 15px;" id="address" style="height: 80px" name="address"  readonly required></textarea>
                </div>
                <br>

                <div style="margin-bottom:2px;">
                    <span id="descriptionError" class="text-danger"></span><br>
                    <label for="description"><b>Description:</b></label>
                    <textarea class="form-control" style="border-radius: 15px;" placeholder="Leave a comment here" name="description"
                        id="description" style="height:80px" oninput="validateForm()" maxlength="300" onblur="validateForm()" required></textarea>
                </div>


                <div class="d-grid gap-2" style="margin-bottom:10px;">
                    <input type="submit" id="submit" value="Apply" class="btn btn-primary btn-lg">
                </div>
            </form>

            </div>
            </div>
        </body>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

        </html>