import java.util.Scanner;

public class SecondLargestSmallest {
    private Scanner scanner = new Scanner(System.in);
    
    public void execute() {
        System.out.println("\n === Finding Second Largest and Smallest === ");
        
        System.out.print(" Enter array size: ");
        int size = scanner.nextInt();
        
        if (size < 2) {
            System.out.println(" Array must have at least 2 elements! ");
            return;
        }
        
        int[] arr = new int[size];
        System.out.println(" Enter " + size + " numbers: ");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        
        findSecondValues(arr);
    }
    
    private void findSecondValues(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
            
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }
        
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Second Largest: " + secondLargest);
        System.out.println("Second Smallest: " + secondSmallest);
    }
}