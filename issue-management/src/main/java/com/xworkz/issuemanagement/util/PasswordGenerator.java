package com.xworkz.issuemanagement.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;


public class PasswordGenerator
{
    public PasswordGenerator()
    {
        System.out.println("Created PasswordGenerator");
    }


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 5; // Adjust the length as needed
    private static final SecureRandom RANDOM = new SecureRandom();

    public void EmailPasswordGenerator() {
        System.out.println("Created PasswordGenerator");
    }

    public static String generatePassword()
    {
        // Generate a password that includes letters and numbers
        return RandomStringUtils.random(PASSWORD_LENGTH, CHARACTERS);
    }

    public static void main(String[] args)
    {
        System.out.println("Generated Password: " + generatePassword());
    }





//    public static String generatePassword()
//    {
//        int length = 6;
//        boolean useLetters = true;
//        boolean useNumbers = true;
//        // Generate a password that includes letters and numbers
//        String password = RandomStringUtils.random(length, useLetters, useNumbers);
//
//        // Optionally, include special characters by concatenating with a separate special characters set
//        String specialChars = "!@#$%^&*()";
//        SecureRandom random = new SecureRandom();
//        for (int i = 0; i < 3; i++)
//        { // Ensure at least 3 special characters
//            int position = random.nextInt(password.length());
//            password = password.substring(0, position) + specialChars.charAt(random.nextInt(specialChars.length())) + password.substring(position + 1);
//        }
//        return password;
//    }
//
//    public static void main(String[] args)
//    {
//        System.out.println("Generated Password: " + generatePassword());
//    }
}
