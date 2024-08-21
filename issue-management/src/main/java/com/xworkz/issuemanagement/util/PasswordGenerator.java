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


