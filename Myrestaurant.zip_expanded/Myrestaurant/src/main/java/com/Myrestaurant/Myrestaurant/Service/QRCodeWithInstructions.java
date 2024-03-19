package com.Myrestaurant.Myrestaurant.Service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class QRCodeWithInstructions {
    public static void main(String[] args) {
        String data = "https://www.example.com"; // Replace with your data
        String instructions = "To Scan the QR Code:\n\n1. Open your smartphone's camera app.\n2. Point your camera at the QR code.\n3. A notification or link will appear.\n4 Tap to open.";

        int qrCodeWidth = 300;
        int qrCodeHeight = 300;
        int textFontSize = 16;

        String qrCodeFilePath = "C:\\Users\\admin\\OneDrive\\Desktop\\qrcode\\qrcode.png"; // QR Code output file path
        String finalFilePath = "C:\\Users\\admin\\OneDrive\\Desktop\\qrcode\\Untitled.jpeg"; // Final image output file path

        try {
            // Generate the QR code image
            BufferedImage qrCodeImage = QRCodeGenerator.generateQRCode(data, qrCodeWidth, qrCodeHeight);

            // Load the instructions as an image
            BufferedImage instructionsImage = createInstructionsImage(qrCodeWidth, qrCodeHeight, instructions, textFontSize);

            // Create a larger canvas to combine the QR code and instructions
            int finalWidth = qrCodeWidth;
            int finalHeight = qrCodeHeight + (instructionsImage.getHeight());
            BufferedImage finalImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D finalGraphics = finalImage.createGraphics();

            // Draw the QR code and instructions on the final image
            finalGraphics.drawImage(qrCodeImage, 0, 0, null);
            finalGraphics.drawImage(instructionsImage, 0, qrCodeHeight, null);

            // Save the final image
            ImageIO.write(finalImage, "png", new File(finalFilePath));

            finalGraphics.dispose();

            System.out.println("QR Code with instructions generated successfully.");
        } catch (Exception e) {
            System.err.println("Error generating QR Code with instructions: " + e.getMessage());
        }
    }

    private static BufferedImage createInstructionsImage(int width, int height, String instructions, int textFontSize) {
        BufferedImage instructionsImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D instructionsGraphics = instructionsImage.createGraphics();
        instructionsGraphics.setColor(Color.WHITE);
        instructionsGraphics.fillRect(0, 0, width, height);
        instructionsGraphics.setColor(Color.BLACK);
        instructionsGraphics.setFont(new Font("Arial", Font.PLAIN, textFontSize));
        String[] lines = instructions.split("\n");
        int y = textFontSize;
        for (String line : lines) {
            instructionsGraphics.drawString(line, 10, y);
            y += textFontSize * 2; // Adjust vertical spacing
        }
        instructionsGraphics.dispose();
        return instructionsImage;
    }
}
