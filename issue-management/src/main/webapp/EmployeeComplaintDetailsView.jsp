<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Complaint Details</title>

    <!-- Bootstrap link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .form-width {
            width: 500px;
            margin-left: auto;
            margin-right: auto;
            border-radius: 5px;
        }

        .dropdown-toggle.custom-dropdown-button {
            color: lightblue !important;
            border: 1px solid blue;
            background-color: transparent;
        }

        .dropdown-toggle.custom-dropdown-button::after {
            color: blue;
        }

        .dropdown-menu.custom-dropdown-menu .dropdown-item {
            color: black;
        }

        .dropdown-menu.custom-dropdown-menu .dropdown-item:hover {
            background-color: blue;
            color: white;
        }

        /* Optional: Ensure image alignment within the button */
        .dropdown-toggle.custom-dropdown-button .profile-image {
            vertical-align: middle;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.status-select').on('change', function () {
                var selectedValue = $(this).val();
                var complaintId = $(this).data('complaint-id');
                var employeeId = $(this).data('employee-id');

                // If the selected value is 'Completed', send OTP request to the server
                if (selectedValue === 'Completed') {
                    $.ajax({
                        url: '/sendOtp', // The URL of your Spring controller method
                        method: 'POST',
                        data: {
                            employeeId: employeeId,
                            complaintId: complaintId
                        },
                        success: function (response) {
                            if (response.status === 'success') {
                                // Show OTP modal if OTP is successfully sent
                                $('#otpModal').modal('show');
                            } else {
                                alert('Failed to send OTP. Please try again.');
                            }
                        },
                        error: function (xhr, status, error) {
                            console.error('Error sending OTP:', error);
                            alert('An error occurred while sending the OTP.');
                        }
                    });
                }
            });

            // Handle OTP form submission
            $('#otpForm').on('submit', function (e) {
                e.preventDefault();
                var otpValue = $('#otp').val();

                if (otpValue) {
                    alert('OTP submitted: ' + otpValue);
                    // You can proceed with further actions
                    $('#otpModal').modal('hide');
                } else {
                    alert('Please enter the OTP.');
                }
            });
        });
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

        <div class="dropdown">
            <button class="dropdown-toggle dropdown-toggle-custom custom-dropdown-button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="text-primary">${departmentName}</span>
            </button>

            <ul class="dropdown-menu dropdown-menu-end custom-dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="updateEmployeeDetails"><strong>Edit Employee Details</strong></a></li>
                <li><a class="dropdown-item" href="get-Department-Names"><strong></strong></a></li>
                <li><a class="dropdown-item" href="subAdminProfilePage"><strong></strong></a></li>
                <li><a class="dropdown-item" href="HomePage"><strong>Log Out</strong></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="text-success text-center mb-4">
        <strong>${message}</strong>
    </div>

    <div class="text-success text-center mb-4">
        <strong>${msg}</strong>
    </div>

    <div class="text-danger text-center mb-4">
        <strong>${error}</strong>
    </div>

    <div class="text-danger text-center mb-4">
        <strong>${empNames}</strong>
    </div>

    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded">
        <div class="card">
            <div style="margin-top: 15px;">
                <h3 style="color:blue;">
                    <center>View Complaint Raise Details</center>
                </h3>
            </div>

            <div class="card-body text-dark">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>S.NO</th>
                                <th>ID</th>
                                <th>Complaint Type</th>
                                <th>Country</th>
                                <th>State</th>
                                <th>City</th>
                                <th>Area</th>
                                <th>Address</th>
                                <th>Description</th>
                                <th>User Id</th>
                                <th>Status</th>
                                <th>Submit</th>
                            </tr>
                        </thead>

                        <tbody class="table-hover">
                            <c:forEach var="viewRaiseComplaintUsers" items="${viewComplaintDetailsByEmployee}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${viewRaiseComplaintUsers.complaintId}</td>
                                    <td>${viewRaiseComplaintUsers.complaintType}</td>
                                    <td>${viewRaiseComplaintUsers.country}</td>
                                    <td>${viewRaiseComplaintUsers.state}</td>
                                    <td>${viewRaiseComplaintUsers.city}</td>
                                    <td>${viewRaiseComplaintUsers.area}</td>
                                    <td>${viewRaiseComplaintUsers.address}</td>
                                    <td>${viewRaiseComplaintUsers.description}</td>
                                    <td>${viewRaiseComplaintUsers.signUpDTO.id}</td>

                                    <td>
                                        <select class="form-select status-select" style="width:150px;" name="status"
                                            data-complaint-id="${viewRaiseComplaintUsers.complaintId}"
                                            data-employee-id="${viewRaiseComplaintUsers.employeeDTO.employee_id}">
                                            <option value="Select">Select</option>
                                            <option value="Pending" ${viewRaiseComplaintUsers.status=='Pending' ? 'selected' : ''}>Pending</option>
                                            <option value="Processing" ${viewRaiseComplaintUsers.status=='Processing' ? 'selected' : ''}>Processing</option>
                                            <option value="Completed" ${viewRaiseComplaintUsers.status=='Completed' ? 'selected' : ''}>Completed</option>
                                        </select>
                                    </td>


                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap Modal for OTP Input -->
        <div class="modal fade" id="otpModal" tabindex="-1" aria-labelledby="otpModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="otpModalLabel">Enter OTP</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="otpForm">
                            <div class="mb-3">
                                <label for="otp" class="form-label">OTP</label>
                                <input type="text" class="form-control" id="otp" name="otp" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-JrOo7lIW3x2wTwn/8zDkBsd+yUlDhSQaQZr2gBsiQZ2J1K3I8MNoJ78Dh5/fT9kk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-32z2w2gA8KKs8i6jZPguv2UX7/y6Cz3s8luR6aG4V7LqYHQs59RMj+W9EKT6+U7X" crossorigin="anonymous"></script>
</body>
</html>
