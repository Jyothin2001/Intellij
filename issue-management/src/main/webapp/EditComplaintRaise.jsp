<%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

                        <a class="navbar-brand text-light" href="HomePage"><b>Home</b></a>
                        <a class="navbar-brand text-light" href="LogInPage"><b>SignIn</b></a>
                    </div>
                   <div class="dropdown">
                               <div class=" dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                               <b>${Username}</b>

                                 <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user-->
                                 <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80"
                                   class="rounded-circle profile-image" alt="Profile Image">

                               </div>

                               <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">


    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ChangePassword"><strong>Change Password</strong></a></li>
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/view-Page"><strong>View Your Details</strong></a></li>
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ComplaintRaisePage"><strong>Complaint Raise</strong></a></li>
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/viewComplaintRaise"><strong>View ComplaintRaise</strong></a></li>
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/HomePage"><strong>Log Out</strong></a></li>




                               </ul>
                             </div>

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


                <!--localhost:/issuemanagement/editComplaintRaise/updateComplaintDetails:it is not work
                localhost:/issuemanagement/updateComplaintDetails:it is working-->

          <form action="${pageContext.request.contextPath}/updateComplaintDetails" method="post">

                    <span style="color:green"><h4>${updateMsg}</h4></span>

                     <span style="color:red"><h4>${updateErrorMsg}</h4></span>

<!--from view  id should not go ::not declare it NoSuchValuePresentException-->
<input type="hidden" name="complaintId" value="${complaintRaiseDTO.complaintId}"/>


                <!---dropdown select issue-->
                <div style="margin-bottom:2px;">
                    <b>Complaint Type:</b>
                    <input type="text" class="form-control" id="complaintType" style="border-radius: 15px;" value="${complaintRaiseDTO.complaintType}" name="complaintType" readonly required>
                    </div>
                <br>

                <!---Country --->
                <div style="margin-bottom:2px;">
                    <b>Country:</b>
                    <input type="text"class="form-control"  id="countryName" style="border-radius: 15px;" name="country" value="${complaintRaiseDTO.country}"   readonly required>
                </div>
                <br>

                <!---State --->
                <div style="margin-bottom:2px;">
                    <b>State:</b>
                    <input type="text"  class="form-control" id="state" name="state" value="${complaintRaiseDTO.state}" style="border-radius: 15px;" readonly required>
                  </div>
                <br>

                <!---City --->
                <div style="margin-bottom:2px;">
                    <b>City:</b>
                    <input type="text" class="form-control" id="city" style="border-radius: 15px;" name="city" value="${complaintRaiseDTO.city}"  readonly required>
                     </div>
                     </br>

                <div style="margin-bottom:2px;">
                    <b>Area:</b>
                    <input type="text" class="form-control" id="area" style="border-radius: 15px;" name="area" value="${complaintRaiseDTO.area}"  readonly required>
                </div>
                <br>

                <div style="margin-bottom:2px;">
                    <b>Address:</b>
                    <input type="text" class="form-control"  style="border-radius: 15px;" id="address" value="${complaintRaiseDTO.address}"style="height: 80px" name="address"  readonly required>
                </div>


                <div style="margin-bottom:2px;">
                    <span id="descriptionError" class="text-danger"></span><br>
                    <label for="description"><b>Description:</b></label>
                    <textarea class="form-control" style="border-radius: 15px;" placeholder="Leave a comment here" name="description" value="${complaintRaiseDTO.description}"
                        id="description" style="height:80px" oninput="validateForm()" maxlength="300" onblur="validateForm()" required></textarea>
                </div>
                <br>


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