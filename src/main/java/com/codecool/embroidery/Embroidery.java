package com.codecool.embroidery;

import java.util.Arrays;

public class Embroidery {
    public static void main(String[] args) {
        int[][] rectangle0 = drawRectangle(5, 3);
        System.out.print("Default rectangle:\n");
        printShape(rectangle0);
        System.out.print("\n");
        int[][] rectangle = drawRectangle(8, 6, 2, 1, 2);
        printShape(rectangle);
    }
    private static int[][] drawRectangle(int width, int height){
        return drawRectangle(width, height, 0, 1, 0);
    }
    private static int[][] drawRectangle(int width, int height, Integer borderColor, Integer fillColor, Integer borderWidth) {
        // handle edge cases
        int lesserLength = Math.min(width,height);
        if (borderWidth * 2 >= lesserLength){
            borderWidth = (int) (lesserLength-1) / 2;
            // todo: szövegnél színezést felkiáltójel és új szám piros
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

    private static int[][] drawTriangle(int height, Integer borderColor, Integer fillColor) {
        return new int[0][0];
    }

    private static int[][] drawChristmasTree(int blocks, Integer borderColor, Integer fillColor) {
        return new int[0][0];
    }

    private static int[][] drawCircle(int radius, Integer borderColor, Integer fillColor) {
        return new int[0][0];
    }

    private static void printShape(int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < shape[0].length; j++) {
                System.out.print(shape[i][j] +" ");
            }
            System.out.println();
        }
    }
}
