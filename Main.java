import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Part 1: Pixel Neighborhood Operations
        System.out.println("Part 1: Pixel Neighborhood Operations");
        int[][] matrix = createMagicMatrix(5);
        printMatrix(matrix);
        
        System.out.print("Enter row (0-4): ");
        int row = scanner.nextInt();
        System.out.print("Enter column (0-4): ");
        int col = scanner.nextInt();
        
        if (row >= 0 && row < 5 && col >= 0 && col < 5) {
            System.out.println("Element at (" + row + "," + col + "): " + matrix[row][col]);
            
            // 4-point neighbors
            int[] n4 = get4PointNeighbors(matrix, row, col);
            System.out.println("4-Point Neighbors:");
            printArray(n4);
            
            // 8-point neighbors
            int[] n8 = get8PointNeighbors(matrix, row, col);
            System.out.println("8-Point Neighbors:");
            printArray(n8);
            
            // Diagonal neighbors
            int[] nd = getDiagonalNeighbors(matrix, row, col);
            System.out.println("Diagonal Neighbors:");
            printArray(nd);
        } else {
            System.out.println("Invalid row or column values!");
        }
        
        // Part 2: Image Transformations
        System.out.println("\nPart 2: Image Transformations");
        try {
            // Load and process image
            BufferedImage originalImage = ImageIO.read(new File("input.jpg"));
            if (originalImage != null) {
                // Create a frame to display images
                JFrame frame = new JFrame("Image Transformations");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridLayout(2, 2));
                
                // Original image
                JLabel originalLabel = new JLabel(new ImageIcon(originalImage));
                frame.add(originalLabel);
                
                // Scaled image
                System.out.print("Enter scaling factor: ");
                double scale = scanner.nextDouble();
                BufferedImage scaledImage = scaleImage(originalImage, scale);
                JLabel scaledLabel = new JLabel(new ImageIcon(scaledImage));
                frame.add(scaledLabel);
                
                // Rotated images
                BufferedImage rotated60 = rotateImage(scaledImage, 60);
                JLabel rotated60Label = new JLabel(new ImageIcon(rotated60));
                frame.add(rotated60Label);
                
                BufferedImage rotated45 = rotateImage(scaledImage, 45);
                JLabel rotated45Label = new JLabel(new ImageIcon(rotated45));
                frame.add(rotated45Label);
                
                frame.pack();
                frame.setVisible(true);
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    // Helper methods for Part 1
    private static int[][] createMagicMatrix(int size) {
        int[][] matrix = new int[size][size];
        int num = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = num++;
            }
        }
        return matrix;
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
    
    private static int[] get4PointNeighbors(int[][] matrix, int row, int col) {
        int[] neighbors = new int[4];
        int index = 0;
        
        if (row + 1 < matrix.length) neighbors[index++] = matrix[row + 1][col];
        if (row - 1 >= 0) neighbors[index++] = matrix[row - 1][col];
        if (col + 1 < matrix[0].length) neighbors[index++] = matrix[row][col + 1];
        if (col - 1 >= 0) neighbors[index++] = matrix[row][col - 1];
        
        return neighbors;
    }
    
    private static int[] get8PointNeighbors(int[][] matrix, int row, int col) {
        int[] neighbors = new int[8];
        int index = 0;
        
        // 4-point neighbors
        if (row + 1 < matrix.length) neighbors[index++] = matrix[row + 1][col];
        if (row - 1 >= 0) neighbors[index++] = matrix[row - 1][col];
        if (col + 1 < matrix[0].length) neighbors[index++] = matrix[row][col + 1];
        if (col - 1 >= 0) neighbors[index++] = matrix[row][col - 1];
        
        // Diagonal neighbors
        if (row + 1 < matrix.length && col + 1 < matrix[0].length) 
            neighbors[index++] = matrix[row + 1][col + 1];
        if (row + 1 < matrix.length && col - 1 >= 0) 
            neighbors[index++] = matrix[row + 1][col - 1];
        if (row - 1 >= 0 && col - 1 >= 0) 
            neighbors[index++] = matrix[row - 1][col - 1];
        if (row - 1 >= 0 && col + 1 < matrix[0].length) 
            neighbors[index++] = matrix[row - 1][col + 1];
        
        return neighbors;
    }
    
    private static int[] getDiagonalNeighbors(int[][] matrix, int row, int col) {
        int[] neighbors = new int[4];
        int index = 0;
        
        if (row + 1 < matrix.length && col + 1 < matrix[0].length) 
            neighbors[index++] = matrix[row + 1][col + 1];
        if (row + 1 < matrix.length && col - 1 >= 0) 
            neighbors[index++] = matrix[row + 1][col - 1];
        if (row - 1 >= 0 && col - 1 >= 0) 
            neighbors[index++] = matrix[row - 1][col - 1];
        if (row - 1 >= 0 && col + 1 < matrix[0].length) 
            neighbors[index++] = matrix[row - 1][col + 1];
        
        return neighbors;
    }
    
    private static void printArray(int[] arr) {
        for (int val : arr) {
            if (val != 0) {
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }
    
    // Helper methods for Part 2
    private static BufferedImage scaleImage(BufferedImage original, double scale) {
        int newWidth = (int) (original.getWidth() * scale);
        int newHeight = (int) (original.getHeight() * scale);
        
        BufferedImage scaled = new BufferedImage(newWidth, newHeight, original.getType());
        Graphics2D g2d = scaled.createGraphics();
        g2d.drawImage(original, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        return scaled;
    }
    
    private static BufferedImage rotateImage(BufferedImage original, double degrees) {
        double rads = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        
        int w = original.getWidth();
        int h = original.getHeight();
        
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, original.getType());
        Graphics2D g2d = rotated.createGraphics();
        
        g2d.translate((newWidth - w) / 2, (newHeight - h) / 2);
        g2d.rotate(rads, w / 2, h / 2);
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();
        
        return rotated;
    }
}
