package com.xworkz.issuemanagement.util;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class EmailOTPGenerator {

    private final Random random;

    public EmailOTPGenerator()
    {
        this.random = new Random();
    }

//    // Method to generate a 6-digit OTP
//    public String generateOtp() {
//        return String.format("%06d", random.nextInt(1000000));
//    }

    // Method to generate a 6-digit OTP
    public String generateOtp() {
        int otp = random.nextInt(1000000); // Generates a number between 0 and 999999
        return String.format("%06d", otp); // Ensures the OTP is 6 digits, with leading zeros if necessary
    }

    // Method to check if the OTP is valid within the time limit (e.g., 1 minute)
    public boolean isOtpValid(LocalDateTime otpTimestamp, int minutesValid) {
        return Duration.between(otpTimestamp, LocalDateTime.now()).toMinutes() < minutesValid;
    }

    // Optional: You can overload the isOtpValid method to use a default time limit
    public boolean isOtpValid(LocalDateTime otpTimestamp) {
        return isOtpValid(otpTimestamp, 1); // Default time limit is 1 minute
    }
}
