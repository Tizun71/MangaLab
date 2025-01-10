package com.tizun.mangalab.controller.common;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CaptchaController {
	@GetMapping("/captcha")
	public void generateCaptcha(HttpServletResponse response) throws Exception {
		System.out.println("Tạo Captcha");
	    int width = 300;
	    int height = 100;
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = image.createGraphics();

	    // Fill background
	    g2d.setColor(Color.WHITE);
	    g2d.fillRect(0, 0, width, height);

	    // Set font and color
	    g2d.setColor(Color.BLACK);
	    Font font = new Font("Arial", Font.BOLD, 48); // Larger font
	    g2d.setFont(font);

	    // Generate random text
	    String captchaText = generateRandomText();

	    // Calculate font metrics for centering
	    FontMetrics fontMetrics = g2d.getFontMetrics();
	    int textWidth = fontMetrics.stringWidth(captchaText);
	    int textHeight = fontMetrics.getHeight();
	    int textAscent = fontMetrics.getAscent();

	    // Calculate position to center text
	    int x = (width - textWidth) / 2;
	    int y = (height - textHeight) / 2 + textAscent;

	    // Draw text
	    g2d.drawString(captchaText, x, y);

	    // Dispose graphics
	    g2d.dispose();

	    // Set response content type and write image
	    response.setContentType("image/png");
	    ImageIO.write(image, "png", response.getOutputStream());
	}
	 	
	 	@PostMapping("/captcha/verify")
	    public String verifyCaptcha(@RequestParam("captcha") String captchaInput, HttpSession session) {
	        String captcha = (String) session.getAttribute("captcha");
	        if (captcha != null && captcha.equalsIgnoreCase(captchaInput)) {
	            return "CAPTCHA đúng!";
	        }
	        return "CAPTCHA sai!";
	    }

	    private String generateRandomText() {
	        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder sb = new StringBuilder();
	        Random random = new Random();
	        for (int i = 0; i < 5; i++) {
	            sb.append(chars.charAt(random.nextInt(chars.length())));
	        }
	        return sb.toString();
	    }
}
