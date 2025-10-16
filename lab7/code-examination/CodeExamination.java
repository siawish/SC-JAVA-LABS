import java.util.Scanner;

public class CodeExamination {
    private Scanner scanner = new Scanner(System.in);
    
    public void execute() {
        System.out.println("\n === Code Examination ===");
        
        while (true) {
            showExaminationMenu();
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    analyzeCodeStructure();
                    break;
                case 2:
                    traceExecution();
                    break;
                case 3:
                    observeOutput();
                    break;
                case 4:
                    identifyPatterns();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }
    
    private void showExaminationMenu() {
        System.out.println("\n| --- Code Examination Options ---");
        System.out.println("| 1. Analyze Code Structure");
        System.out.println("| 2. Trace Execution");
        System.out.println("| 3. Observe Output");
        System.out.println("| 4. Identify Patterns");
        System.out.println("| 5. Back to Main Menu");
        System.out.print("| Choose an option: ");
    }
    
    private void analyzeCodeStructure() {
        System.out.println("\n=== Analyzing Code Structure ===");
        System.out.println("Example: ArrayList vs Array implementation");
        
      
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Array: Fixed size, direct memory access");
        System.out.println("Array elements: " + java.util.Arrays.toString(array));
        System.out.println("Array length: " + array.length);
        
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        System.out.println("ArrayList: Dynamic size, object wrapper");
        System.out.println("ArrayList elements: " + list);
        System.out.println("ArrayList size: " + list.size());
    }
    
    private void traceExecution() {
        System.out.println("\n=== Tracing Execution ===");
        System.out.println("Step-by-step execution of nested loops:");
        
        int[] arr = {2, 4, 6};
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        
        System.out.println("Nested loop execution trace:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int result = arr[i] * arr[j];
                System.out.println("Step: i=" + i + ", j=" + j + 
                                 ", arr[" + i + "]=" + arr[i] + 
                                 ", arr[" + j + "]=" + arr[j] + 
                                 ", result=" + result);
            }
        }
    }
    
    private void observeOutput() {
        System.out.println("\n=== Observing Output ===");
        System.out.println("Comparing expected vs actual results:");
        
        int[] numbers = {15, 3, 9, 1, 12};
        System.out.println("Input array: " + java.util.Arrays.toString(numbers));
        
        int min = numbers[0];
        int max = numbers[0];
        
        for (int num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        System.out.println("Expected min: 1, Actual min: " + min);
        System.out.println("Expected max: 15, Actual max: " + max);
        System.out.println("Sum calculation:");
        
        int sum = 0;
        for (int num : numbers) {
            sum += num;
            System.out.println("Adding " + num + ", running sum: " + sum);
        }
        System.out.println("Final sum: " + sum);
    }
    
    private void identifyPatterns() {
        System.out.println("\n=== Identifying Common Patterns ===");
        
        System.out.println("1. Enhanced for loop pattern:");
        int[] demo1 = {10, 20, 30};
        for (int item : demo1) {
            System.out.println("Processing: " + item);
        }
        
        System.out.println("\n2. Traditional for loop pattern:");
        int[] demo2 = new int[5];
        for (int i = 0; i < demo2.length; i++) {
            demo2[i] = (i + 1) * 5;
            System.out.println("demo2[" + i + "] = " + demo2[i]);
        }
        
        System.out.println("\n3. While loop pattern:");
        int[] demo3 = {1, 3, 5, 7, 9};
        int index = 0;
        while (index < demo3.length) {
            System.out.println("Index " + index + ": " + demo3[index]);
            index++;
        }
        
        System.out.println("\n4. Array initialization patterns:");
        System.out.println("Pattern result: " + java.util.Arrays.toString(demo2));
    }
}