package com.codecool.embroidery;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Embroidery {
    public static void main(String[] args) {

//        int[][] rectangle0 = drawRectangle(5, 3);
//        System.out.print("Default rectangle:\n");
//        printShape(rectangle0);

        int[][] rectangle = drawRectangle(8, 6, 2, 1, 2);
        printShape(rectangle);
        GUI(rectangle);

        int[][] triangle = drawTriangle(6, 1, 3);
        printShape(triangle);
        GUI(triangle);

        int[][] christmasTree = drawChristmasTree(5, 1, 3);
        printShape(christmasTree);
        GUI(christmasTree);

        int[][] circle = drawCircle(4, 6, 3);
        printShape(circle);
        GUI(circle);
    }
    public static void GUI(int[][] graphic) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 600));
        JTextArea txtA = new JTextArea();
        panel.add(txtA);
        frame.pack();
        frame.setVisible(true);
        for (int i = 0; i < graphic.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < graphic[0].length; j++) {
                txtA.append(graphic[i][j] + "  ");
            }
            txtA.append("\n");
        }
    }
    private static int[][] QdrawRectangle(int width, int height){
        return drawRectangle(width, height, 0, 1, 0);
    }
    private static int[][] drawRectangle(int width, int height, Integer borderColor, Integer fillColor, Integer borderWidth) {
        // handle edge cases
        int lesserLength = Math.min(width,height);
        if (borderWidth * 2 >= lesserLength){
            borderWidth = (int) (lesserLength-1) / 2;
            System.out.print("\n" +
                    "!! Border setting is too high. The border\n" +
                    "!! won't fit into the specified rectangle,\n" +
                    "!! with discernible border-filler separation.\n" +
                    "!! Therefore border is reduced to " +
                    "[" + borderWidth + "]\n");
        }
        int[] arguments = new int[]{width, height, borderWidth.intValue(),
                                    borderColor.intValue(), fillColor.intValue()};
        return drawRectangle(arguments);
    }
    private static int[][] drawRectangle(int[] arguments) {

        int[][] rectangle = new int[arguments[1]][arguments[0]];
        int[] border = new int[arguments[0]];
        Arrays.fill(border, arguments[3]);
        int[] filler = new int[arguments[0]];
        for (int i = 0; i < filler.length; i++) {
            if (i < arguments[2] || i >= filler.length - arguments[2]){
                filler[i] = arguments[3];
            }else{
                filler[i] = arguments[4];
            }
        }
        for (int i = 0; i < rectangle.length; i++){
            if (i < arguments[2] || i >= rectangle.length - arguments[2]){
                rectangle[i] = border;
            }else{
                rectangle[i] = filler;
            }
        }
        return rectangle;
    }
    private static int[][] drawTriangle(int height){
        return drawTriangle(height, 1, 1);
    }
    private static int[][] drawTriangle(int height, Integer borderColor, Integer fillColor) {
        int[][] triangle = new int[height][height*2-1];
        for (int i = height-1; i >= 0; i--) {
            for (int j = 0; j < triangle[0].length; j++) {
                if(j < height - 1 -i || j> triangle[0].length -height + i){
                    triangle[i][j] = 0;
                } else if (j < height -i || j> triangle[0].length -height -1 + i || i == height-1){
                    triangle[i][j] = borderColor;
                } else {
                    triangle[i][j] = fillColor;}
            }
        }
        return triangle;
    }
    private static int[][] drawChristmasTree(int blocks){
        return drawChristmasTree(blocks, 1, 1);
    }
    private static int[][] drawChristmasTree(int blocks, Integer borderColor, Integer fillColor) {
        int triangleHeight = blocks + 2;
        int treeHeight = blocks * 3;
        int[][] triangle = Embroidery.drawTriangle(triangleHeight, borderColor, fillColor);
        int[][] christmasTree =  new int[treeHeight][triangleHeight*2-1];
        int j = triangleHeight-1;
        int blockrow = 1;
        for (int i = treeHeight-1; i >= 0;) {
            while (blockrow < 4){
                christmasTree[i] = triangle[j];
                blockrow++;
                j--;
                i--;
            }
            blockrow = 1;
            j += 2;
        }

        return christmasTree;

    }
    private static int[][] drawCircle(int radius){
        return drawCircle(radius, 1, 1);
    }
    private static int[][] drawCircle(int radius, Integer borderColor, Integer fillColor) {
        int[][] circle = new int[radius*2+1][radius*2+1];
        for (int i = radius; i >= 0; i--) { //y coordinate
            for (int j = radius; j < radius*2+1; j++) { //x coordinate
                int calcualtedR2 = (j-radius)*(j-radius) + (radius-i)*(radius-i);
                boolean circleInside = calcualtedR2 < (radius-1)*(radius-1)+1;
                boolean circleBorder = calcualtedR2 <= (radius)*(radius)+1;
                if (circleInside) {
                    circle[i][j] = fillColor.intValue();
                    circle[2*radius-i][j] = fillColor.intValue();
                    circle[i][radius*2-j] = fillColor.intValue();
                    circle[2*radius-i][radius*2-j] = fillColor.intValue();
                }else if(circleBorder){
                    circle[i][j] = borderColor.intValue();
                    circle[2*radius-i][j] = borderColor.intValue();
                    circle[i][radius*2-j] = borderColor.intValue();
                    circle[2*radius-i][radius*2-j] = borderColor.intValue();
                } else {
                    circle[i][j] = 0;
                    circle[2*radius-i][j] = 0;
                    circle[i][radius*2-j] = 0;
                    circle[2*radius-i][radius*2-j] = 0;
                }
            }
        }
        return circle;
    }
    private static void printShape(int[][] shape) {
        System.out.println();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                System.out.print(shape[i][j] +"  ");
            }
            System.out.println();
        }
    }
}
