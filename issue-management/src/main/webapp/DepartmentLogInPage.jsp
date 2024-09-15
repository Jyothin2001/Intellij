 <%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Department Admin Login</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome CDN Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>
    <!-- icons styling-->

             .form-container {
                 width: 300px;
                 margin: 0 auto;
                 padding: 20px;
                 border: 1px solid #ccc;
                 border-radius: 10px;
             }
             .form-group {
                 margin-bottom: 15px;
             }
             .form-label {
                 display: block;
                 margin-bottom: 5px;
                 font-weight: bold;
             }
             .form-control {
                 width: 100%;
                 padding: 10px;
                 font-size: 14px;
             }
             .small-placeholder::-webkit-input-placeholder {
                 font-size: 18px;
             }
             .small-placeholder:-moz-placeholder {
                 font-size: 18px;
             }
             .small-placeholder::-moz-placeholder {
                 font-size: 18px;
             }
             .small-placeholder:-ms-input-placeholder {
                 font-size: 18px;
             }
             .input-icon {
                 position: relative;
             }
             .input-icon i {
                 position: absolute;
                 left: 10px;
                 top: 50%;
                 transform: translateY(-50%);
                 font-size: 14px;
                 color: #aaa;
             }
             .input-icon input
             {
                 padding-left: 30px;
             }
             :root {
                 --blue-100: #cce4ff; <!-- var(--blue-100) Replace with your desired blue shade -->
             }

             .form-width {
                 width: 500px;
                 margin-left: 550px;
                 background-color:;
                 border-radius:5;
             }

     body
     {
     background-color:;
     }


    <!-- password styling-->
        .form-group {
            margin-bottom:24px;
            position: relative;
            width: 100%;
        }
        .password-container {
            position: relative;
            width: 100%; /* Full width of the parent container */
        }

        .form-control {
            width: 100%; /* Full width of the container */
            padding: 10px 40px 10px 10px; /* Add space on the right for the icon */
            border-radius: 15px;
            box-sizing: border-box;
        }

        .pass-icon {
            position: absolute;
            top: 50%;
            right: 10px; /* Space from the right edge */
            width: 25px; /* Adjust icon size */
            height: 25px;
            cursor: pointer;
            transform: translateY(-50%); /* Center vertically */
        }

    <!-- Placeholder styling
       ::placeholder {
         font-family: 'Arial', sans-serif;
         font-size: 14px;
         color: #6c757d;
         opacity: 1;
       }-->
</style>

<script>
    var a = 0;
    function pass() {
        if (a == 1) {
            // Hide password
            document.getElementById('password').type = 'password';
            document.getElementById('pass-icon').src = 'https://img.icons8.com/?size=100&id=121539&format=png&color=000000';
            a = 0;
        } else {
            // Show password
            document.getElementById('password').type = 'text';
            document.getElementById('pass-icon').src = 'https://img.icons8.com/?size=100&id=986&format=png&color=000000';
            a = 1;
        }
    }

    function emailValidation() {
        const emailInput = document.getElementById('email');
        const emailError = document.getElementById('emailError');
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(emailInput.value)) {
            emailError.textContent = 'Please enter a valid email address.';
            return false;
        } else {
            emailError.textContent = '';
            return true;
        }
    }

    function passwordValidation() {
        const passwordInput = document.getElementById('password');
        const passwordError = document.getElementById('passwordError');

        if (passwordInput.value.length < 8) {
            passwordError.textContent = 'Password must be at least 8 characters long.';
            return false;
        } else {
            passwordError.textContent = '';
            return true;
        }
    }

    function validateDepartmentName() {
        const departmentInput = document.getElementById('departmentName');
        const departmentError = document.getElementById('DepartmentNameError');

        if (departmentInput.value === "") {
            departmentError.textContent = 'Please select a department.';
            return false;
        } else {
            departmentError.textContent = '';
            return true;
        }
    }

    function validateForm() {
        const isEmailValid = emailValidation();
        const isPasswordValid = passwordValidation();
        const isDepartmentValid = validateDepartmentName();
        const submitButton = document.getElementById('submitButton');
        submitButton.disabled = !(isEmailValid && isPasswordValid && isDepartmentValid);
    }
</script>

</head>
<body>

<nav class="navbar navbar-light bg-primary">
    <div class="container-fluid">
        <div class="navbar-header">
            <!-- Add your logo here -->
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
            </a>
            <a class="navbar-brand text-light" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded form-width">
    <div style="margin-top: 5px;">
        <h2 style="color:blue;"><center>Login Page</center></h2>
    </div>

    <div class="card-body text-dark">
        <!-- Displaying messages -->
        <div class="text-success"><h6><b>${msg}</b></h6></div>
        <div class="text-danger"><h6><b>${errorMsg}</b></h6></div>
        <div class="text-danger"><h6><b>${accountError}</b></h6></div>
        <div class="text-success"><h6><b>${passwordResetMessage}</b></h6></div>
        <div class="text-danger"><b>${forgotPasswordMsg}</b></div>

        <!-- Form -->
        <form action="DepartmentLogInPage" method="post">

            <!-- Email -->
            <div class="form-group">
                <span class="error-message" id="emailError" style="color: red;"></span>
                <label for="email" class="form-label">Email:</label>
                <div class="input-icon">
                    <i class="fa-regular fa-envelope"></i>
                    <input type="email" class="form-control" id="email" name="email" style="border-radius: 15px;" placeholder="Enter Email" oninput="validateForm()" onblur="validateForm()">
                </div>
            </div>

            <!-- Password -->
            <div class="form-group">
                <span class="error-message" id="passwordError" style="color: red;"></span>
                <label for="password" class="form-label">Password:</label>
                <div class="password-container">
                    <input type="password" class="form-control" id="password" name="password" style="border-radius: 15px;" placeholder="Enter Password" oninput="validateForm()" onblur="validateForm()">
                    <img src="https://img.icons8.com/?size=100&id=121539&format=png&color=000000" onclick="pass()" class="pass-icon" id="pass-icon">
                </div>
            </div>

            <!-- Department Dropdown -->
            <div class="form-group">
                <span class="error-message" id="DepartmentNameError" style="color: red;"></span>
                <label for="departmentName" class="form-label">Department:</label>
                <select class="form-select custom-select-width" id="departmentName" name="departmentName" style="border-radius: 15px;" oninput="validateForm()" onchange="validateForm()">
                    <option  value="">Choose..</option>
                    <c:forEach items="${departments}" var="department">
                        <option value="${department.departmentName}">${department.departmentName}</option>
                    </c:forEach>
                </select>
            </div>

            <div style="text-align:center;">
                <a href="SubAdminForgotPasswordPage">Forgot Password?</a>
            </div><br>

            <div class="d-grid gap-2" style="margin-bottom:10px;">
                <input type="submit" class="btn btn-primary btn-lg" id="submitButton" disabled value="Login">
            </div>
        </form>
    </div>
</div>

</body>
</html>
