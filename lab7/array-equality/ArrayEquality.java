import java.util.Scanner;

public class ArrayEquality {
    private Scanner scanner = new Scanner(System.in);
    
    public void execute() {
        System.out.println("\n === Testing Array Equality === ");
        
        int[] arr1 = inputArray("first");
        int[] arr2 = inputArray("second");
        
        boolean areEqual = checkArrayEquality(arr1, arr2);
        displayResults(arr1, arr2, areEqual);
    }
    
    private int[] inputArray(String arrayName) {
        System.out.print("Enter size for " + arrayName + " array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        
        System.out.println("Enter " + arrayName + " array elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }
    
    private boolean checkArrayEquality(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            System.out.println("Arrays have different lengths: " + arr1.length + " vs " + arr2.length);
            return false;
        }
        
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("Elements differ at index " + i + ": " + arr1[i] + " vs " + arr2[i]);
                return false;
            }
        }
        
        return true;
    }
    
    private void displayResults(int[] arr1, int[] arr2, boolean areEqual) {
        System.out.println("Array 1: " + java.util.Arrays.toString(arr1));
        System.out.println("Array 2: " + java.util.Arrays.toString(arr2));
        System.out.println("Arrays are equal: " + areEqual);
    }
}