import java.util.Scanner;

public class MatrixMultiplication {
    private Scanner scanner = new Scanner(System.in);
    
    public void execute() {
        System.out.println("\n=== Matrix Multiplication ===");
        
        System.out.print("Enter rows for first matrix: ");
        int rows1 = scanner.nextInt();
        System.out.print("Enter columns for first matrix: ");
        int cols1 = scanner.nextInt();
        
        System.out.print("Enter rows for second matrix: ");
        int rows2 = scanner.nextInt();
        System.out.print("Enter columns for second matrix: ");
        int cols2 = scanner.nextInt();
        
        if (!validateMatrices(cols1, rows2)) {
            return;
        }
        
        int[][] matrix1 = inputMatrix(rows1, cols1, "first");
        int[][] matrix2 = inputMatrix(rows2, cols2, "second");
        
        int[][] result = multiplyMatrices(matrix1, matrix2, rows1, cols2, cols1);
        displayResult(result);
    }
    
    private boolean validateMatrices(int cols1, int rows2) {
        if (cols1 != rows2) {
            System.out.println("Matrix multiplication not possible!");
            System.out.println("Columns of first matrix must equal rows of second matrix.");
            return false;
        }
        return true;
    }
    
    private int[][] inputMatrix(int rows, int cols, String matrixName) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter " + matrixName + " matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }
    
    private int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2, int rows1, int cols2, int cols1) {
        int[][] result = new int[rows1][cols2];
        
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
    
    private void displayResult(int[][] result) {
        System.out.println("Result Matrix:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}