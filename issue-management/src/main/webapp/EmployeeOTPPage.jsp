<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome library for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    .oval-btn {
        border-radius: 40px;
        padding: 10px 20px;
        font-size: 1.2em;
    }

    .bold-text {
        font-weight: bold;
    }

    body {
        background-image: url("https://pics.craiyon.com/2023-09-12/789e617172884be1b6955a4f15b7eac5.webp"); /* Replace with your image URL */
        background-size: cover;
      background-size: 100%; /* Adjust the percentage to make the image smaller */

        background-position: center;
        background-repeat: no-repeat;
        background-color: #f8f9fa; /* Fallback background color */
    }

    .card {
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1); /* Soft shadow for card */
        border-radius: 20px;
        background-color: #ffffff; /* White background for card */
        opacity: 0.9; /* Slight transparency */
    }

    .card-header h3 {
        font-weight: bold;
        color: #343a40; /* Dark color for the header */
    }

    .input-group-text {
        background-color: #343a40;
        color: #fff; /* White icon color */
    }

    .form-label {
        font-weight: bold; /* Dark color for label */
    }

    .container {
        max-width: 500px; /* For card width */
    }

    .timer {
        font-weight: bold;
        color: red;
    }

</style>

<script>
    // Set the countdown time in seconds (5 minutes = 300 seconds)
    var countdownTime = 30;

    function startCountdown() {
        var timerElement = document.getElementById("timer");
        var otpField = document.getElementById("otp");
        var submitButton = document.querySelector("input[type='submit']");

        var countdownInterval = setInterval(function () {
            var minutes = Math.floor(countdownTime / 60);
            var seconds = countdownTime % 60;

            // Add leading zeros to seconds
            seconds = seconds < 10 ? '0' + seconds : seconds;

            // Display the countdown timer
            timerElement.textContent = minutes + ":" + seconds;

            if (countdownTime <= 0) {
                clearInterval(countdownInterval);
                timerElement.textContent = "Time's up!";
                otpField.disabled = true; // Disable OTP field
                submitButton.disabled = true; // Disable submit button
                 resendButton.disabled = false; // Enable Resend Code button
            }

            countdownTime--;
        }, 1000);
    }

    // Start the countdown when the page loads
    window.onload = startCountdown;

       function resendOTP() {
           var email = $("input[name='email']").val(); // Get the email from the input field
           var otpField = document.getElementById("otp");
           var submitButton = document.querySelector("input[type='submit']");
           var resendButton = document.getElementById("resendButton");

           $.post("resendOtp", { email: email }, function(response) {
               alert(response); // Show response message

               countdownTime = 300; // Reset countdown time to 5 minutes (300 seconds)
               otpField.disabled = false; // Re-enable OTP field
               submitButton.disabled = false; // Re-enable submit button
               resendButton.disabled = true; // Disable Resend Code button again

               startCountdown(); // Restart countdown
           }).fail(function() {
               alert("Error: Could not resend OTP");
           });
       }

</script>

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container mt-5 mb-5 justify-content-center">
    <div class="card-header text-center">
        <h3>Log In</h3>
    </div>

    <div class="card-body text-dark">

        <span style="color:green"><strong>${generatedOTP}</strong></span>
        <span style="color:red"><strong>${failed}</strong></span>
        <span style="color:red"><strong>${emailNotFound}</strong></span>

        <form action="otpVerification" method="POST">
            <div class="mb-3">
                <label for="otp" class="form-label">OTP:</label>
                <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required>
            </div>

      <input type="hidden" name="email" value="${email}">


            <!-- Timer Display -->
            <div class="mb-3 text-center">
                <span class="timer" id="timer">5:00</span> <!-- Initial timer value -->
            </div>

            <div class="d-flex justify-content-center mt-3">
                <input type="submit" value="Login" class="btn btn-dark oval-btn bold-text">
            </div><br>
                        <!-- Resend Code Button -->
                        <div class="mb-3 text-center">
                            <button type="button" id="resendButton" class="btn btn-secondary" disabled onclick="resendOTP()">Resend Code</button>
                        </div>

        </form>

    </div>
</div>
</body>
</html>
