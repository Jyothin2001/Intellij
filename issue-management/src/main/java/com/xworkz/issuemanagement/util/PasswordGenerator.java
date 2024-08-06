package com.xworkz.issuemanagement.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.security.SecureRandom;


import java.security.SecureRandom;
@Slf4j
public class PasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String SPECIAL_CHARACTERS = "@$!%*?&";
    private static final int PASSWORD_LENGTH = 8; // Adjust the length as needed
    private static final SecureRandom RANDOM = new SecureRandom();



    public static String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Ensure inclusion of at least one character from each category
        password.append(CHARACTERS.charAt(RANDOM.nextInt(26))); // Uppercase letter
        password.append(CHARACTERS.charAt(26 + RANDOM.nextInt(26))); // Lowercase letter
        password.append(CHARACTERS.charAt(52 + RANDOM.nextInt(10))); // Digit
        password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length()))); // Special character

        // Fill the rest of the password length with random characters from the allowed set
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        // Shuffle the characters to ensure randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }

    public static void main(String[] args) {
        log.info("Generated Password: {}" , generatePassword());
    }
}

//public class PasswordGenerator {
//    public PasswordGenerator() {
//        System.out.println("Created PasswordGenerator");
//    }
//
//
////    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
////    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+[]{}|;:,.<>?";
////    private static final int PASSWORD_LENGTH = 8; // Adjust the length as needed
////    private static final SecureRandom RANDOM = new SecureRandom();
////
////
////    public static String generatePassword() {
////        // Ensure at least one special character
////        String specialCharacter = RandomStringUtils.random(1, SPECIAL_CHARACTERS);
////
////        // Generate remaining characters from CHARACTERS
////        String remainingCharacters = RandomStringUtils.random(PASSWORD_LENGTH - 1, CHARACTERS);
////
////        // Combine both and shuffle to ensure the special character is not always at the beginning
////        String combined = specialCharacter + remainingCharacters;
////        return shuffleString(combined);
////    }
////
////    private static String shuffleString(String string) {
////        char[] characters = string.toCharArray();
////        for (int i = 0; i < characters.length; i++) {
////            int randomIndex = RANDOM.nextInt(characters.length);
////            char temp = characters[i];
////            characters[i] = characters[randomIndex];
////            characters[randomIndex] = temp;
////        }
////        return new String(characters);
////    }
////
////    public static void main(String[] args) {
////        System.out.println("Generated Password: " + generatePassword());
////
////    }
//
//
//
//
////    public static String generatePassword()
////    {
////        int length = 6;
////        boolean useLetters = true;
////        boolean useNumbers = true;
////        // Generate a password that includes letters and numbers
////        String password = RandomStringUtils.random(length, useLetters, useNumbers);
////
////        // Optionally, include special characters by concatenating with a separate special characters set
////        String specialChars = "!@#$%^&*()";
////        SecureRandom random = new SecureRandom();
////        for (int i = 0; i < 3; i++)
////        { // Ensure at least 3 special characters
////            int position = random.nextInt(password.length());
////            password = password.substring(0, position) + specialChars.charAt(random.nextInt(specialChars.length())) + password.substring(position + 1);
////        }
////        return password;
////    }
////
////    public static void main(String[] args)
////    {
////        System.out.println("Generated Password: " + generatePassword());
////    }
//}


//<%@ page isELIgnored="false"%>
//<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
//<!DOCTYPE html>
//<html>
//<head>
//<meta charset="ISO-8859-1">
//<title>Department Sign up</title>
//
//<!--BootStrap link-->
//<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
//
//<!--Font Awesome cdn icon link-->
//<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
//
//
//<!--Script link
//  <script src="/issue-management/js/SignUp.js"></script>-->
//
//
//<!--css link-->
//<link rel="stylesheet" href="/issue-management/css/SignUp.css">
//
//<style>
//.form-check-input {
//    /* Add any other styles you need, but avoid border-radius if you want the default checkbox look */
//    appearance: auto; /* Ensures default checkbox style is applied */
//}
//
//
//</style>
//</head>
//
//<body>
//
//<nav class="navbar navbar navbar-light bg-primary " >
//  <div class="container-fluid">
//   <div class="navbar-header">
//
//      <!-- Add your logo here -->
//              <a class="navbar-brand" href="#">
//                  <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="100" height="50">
//              </a>
//
//   <a class="navbar-brand text-light" href="HomePage"><b>Home</b></a>
//   <a class="navbar-brand text-light" href="DepartmentLogInPage"><b>Log In</b></a>
// </div>
//</div>
//</nav>
//
//
//    <div class="card border-dark container mt-5 mb-3 justify-content-center border-0 shadow-lg p-3 mb-5 bg-body rounded rounded form-width " >
//
//        <!--<div class="card-header">
//           <h3 style= "font-family:Lucida Handwriting, cursive;;"><b><center>Sign In Form</center></b></h3>
//        </div>-->
//
//              <div style = "margin-top: 15px;">
//                   <h1 style= "color:blue; "><center>Sign up Form</center></h1>
//              </div>
//
//               <!--text/word colors-->
//         <div class="card-body text-dark">
//
//             <!---showing errors--!>
//                <span style="color:red;">
//                    <c:forEach items="${errors}" var="objectError">
//${objectError.defaultMessage}<br>
//                    </c:forEach>
//                </span>
//<!--Form-->
//
//      <form action="DepartmentSignUp" method="post">
//
//<div class="text-primary"><b>${saveDeptAdmin}</b></div>
//
//           <!--Text: First Name-->
//              <div style="margin-bottom:2px;" class="form-group">
//                  <span id="AdminNameError"></span><br>
//                   <label for="AdminName" class="form-label">Admin Name:</label>
//                     <div class="input-icon">
//                        <i class="fas fa-user"></i>
//                     <input type="text" class="form-control" id="AdminName" onblur="AdminNameValidation()" name="adminName"   placeholder="Enter Admin Name" style="border-radius: 15px;" />
//               </div>
//               </div>
//
//
//               <!---dropdown select issue-->
//               <div style="margin-bottom:2px;" class="">
//                    <span id="DepartmentNameError"></span><br>
//                    <label for="DepartmentName" class="form-label">DepartmentName:</label>
//                    <select class="form-select custom-select-width"  id="DepartmentName" onblur="DepartmentNameValidation()" name="departmentName"  placeholder="Enter Department Name" style="border-radius: 15px;" required />
//                    <option value="0">Select</option>
//                                                    <option value="Electric issue">Electric issue</option>
//                                                    <option value="Water Supply">Water Supply</option>
//                                                    <option value="Network Problem">Network Problem</option>
//                                                    <option value="System Problem">System Problem</option>
//                                                    <option value="Water Problem">Water Problem</option>
//                    </select>
//               </div>
//
//
//
//                    <!--email: Email-->
//                <div style="margin-bottom:2px;" class="form-group">
//                   <span id="emailError"></span><br>
//                   <label for="email" class="form-label">Email:</label>
//                   <div class="input-icon">
//                   <i class="fa-regular fa-envelope"></i>
//                   <input type="email" class="form-control" id="email" oninput="emailValidation()"    name="email"  style="border-radius: 15px;"  placeholder="Enter Your Email"/>
//                 </div>
//                 </div>
//
//
//                  <!--tel: Contact Number-->
//                <div  style="margin-bottom:2px;" class="form-group">
//                     <span id="contactNumberError"></span><br>
//                     <label for="contactNumber" class="form-label">Contact Number:</label>
//                     <div class="input-icon">
//                     <i class="fa-solid fa-phone"></i>
//                     <input type="tel" class="form-control" id="contactNumber" oninput="contactNumberValidation()"  name="contactNumber"  style="border-radius: 15px;"  placeholder="Enter Contact Number"/>
//                </div>
//                </div>
//
//
//                 <!--tel: Alternative Number-->
//               <div style="margin-bottom:2px;" class="form-group">
//                  <span id="altContactNbrError"></span><br>
//                  <label for="alternateContactNumber" class="form-label">Alternate Number:</label>
//                  <div class="input-icon">
//                  <i class="fa-solid fa-phone"></i>
//                 <input type="tel"  class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternateContactNumber" style="border-radius: 15px;"  placeholder="Enter Alternative Number"/>
//               </div>
//               </div>
//
//
//              <!--checkbox: Agree-->
//            <div>
//                <span  id="agreeError"></span>
//                <label  for="agree" ></label></br>
//                <input  class="form-check-input"  id="agree" type="checkbox" onchange="agreeValidation()"  style="border-radius: 15px;"  type="checkbox"  value="agree" / >
//<b>I agree to </b><a href="#"> Terms & condition</a>
//                </label>
//           </div>
//           </div><br>
//
//
//              <div class="d-grid gap-2" style="margin-bottom:10px;">
//                                <input type="submit" class="btn btn-primary btn-lg " id="submit"  value="SignUp">
//                            </div><br>
//
//
//              <div style="text-align:center;">
//Already have an account?<a href="DepartmentLogInPage">Sign In</a>
//              </div>
//
//             <!--<div style="text-align:center;" class="proxima-nova">
//Already have an account? <a href="SignIn.jsp">Sign In</a>
//                </div>-->
//
//         </form>
//
//
//     </div>
//  </div>
// </body>
//
//</html>
