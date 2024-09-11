package com.xworkz.issuemanagement.captchaServlet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 150;
        int height = 50;

        // Create CAPTCHA image
        BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = captchaImage.getGraphics();

        // Set background color and font
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        // Generate random CAPTCHA text
        String captchaText = generateCaptchaText();
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);

        // Draw CAPTCHA text on image
        g.setColor(Color.BLACK);
        g.drawString(captchaText, 25, 35);

        // Send the image as a response
        response.setContentType("image/jpeg");
        ImageIO.write(captchaImage, "jpeg", response.getOutputStream());
    }

    private String generateCaptchaText() {
        Random random = new Random();
        int length = 6;
        String captchaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder captchaText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captchaText.append(captchaChars.charAt(random.nextInt(captchaChars.length())));
        }

        return captchaText.toString();
    }
}
