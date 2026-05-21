import java.util.Random;

public class Lab1_Variant19 {

    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("-----------------------------------");

        int rows = 3;
        int cols = 2;
        int[][] S = new int[rows][cols];

        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                S[i][j] = random.nextInt(100);
            }
        }

        System.out.println("Матриця S:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(S[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");

        System.out.println("Суми елементів рядків матриці:");
        for (int i = 0; i < rows; i++) {
            int rowSum = 0;
            for (int j = 0; j < cols; j++) {
                rowSum += S[i][j];
            }
            System.out.println("Сума " + (i + 1) + "-го рядка: " + rowSum);
        }
    }
}