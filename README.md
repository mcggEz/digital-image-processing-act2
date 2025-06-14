# 🖼️ Digital Image Processing - Activity 2

## 📋 Overview
This Java program demonstrates two main functionalities:
1. Pixel neighborhood operations in a matrix
2. Image transformations (scaling and rotation)

## ✨ Features

### 1. Pixel Neighborhood Operations
- Creates a 5x5 matrix with sequential numbers
- Finds and displays:
  - 4-point neighbors (up, down, left, right)
  - 8-point neighbors (4-point + diagonal)
  - Diagonal neighbors only

### 2. Image Transformations
- Image scaling with user-defined factor
- Image rotation (45° and 60°)
- Displays original, scaled, and rotated images in a grid layout

## 🔧 Requirements
- Java Development Kit (JDK) 8 or higher
- An image file named `input.jpg` in the program directory

## 🚀 Getting Started

### Compilation
```bash
javac Main.java
```

### Execution
```bash
java Main
```

## 📖 Usage Guide

### Part 1: Pixel Neighborhood Operations
1. When prompted, enter a row number (0-4)
2. Enter a column number (0-4)
3. The program will display:
   - The selected element
   - 4-point neighbors
   - 8-point neighbors
   - Diagonal neighbors

### Part 2: Image Transformations
1. Make sure you have an image file named `input.jpg` in the program directory
2. When prompted, enter a scaling factor:
   - `0.5` for half size
   - `2.0` for double size
3. The program will open a window showing:
   - Original image
   - Scaled image
   - Image rotated 60 degrees
   - Image rotated 45 degrees

## 💻 Technical Details
- Uses Java Swing for GUI components
- Implements image processing using `BufferedImage` and `Graphics2D`
- Handles matrix operations for pixel neighborhood calculations
- Includes error handling for invalid inputs and file operations

## 📝 Notes
- The program assumes the input image is in JPG format
- Matrix indices are 0-based (0-4 for a 5x5 matrix)
- Invalid row/column inputs will be rejected

---
*Digital Image Processing - Activity 2* 