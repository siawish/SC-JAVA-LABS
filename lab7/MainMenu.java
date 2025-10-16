import java.util.Scanner;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runJavaProgram("second-largest-smallest", "SecondLargestSmallest");
                    break;
                case 2:
                    runJavaProgram("matrix-multiplication", "MatrixMultiplication");
                    break;
                case 3:
                    runJavaProgram("array-equality", "ArrayEquality");
                    break;
                case 4:
                    runJavaProgram("code-examination", "CodeExamination");
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Plz Try again.");
            }
            System.out.println("\n Press Enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    private static void showMenu() {
        System.out.println("\n+ === Array Tasks Menu ===");
        System.out.println("| 1. Find Second Largest and Smallest");
        System.out.println("| 2. Matrix Multiplication");
        System.out.println("| 3. Test Array Equality");
        System.out.println("| 4. Code Examination");
        System.out.println("| 5. Exit");
        System.out.print("| Choose an option: ");
    }

    private static void runJavaProgram(String folder, String className) {
        try {
            // Load the class from the specified folder
            java.net.URLClassLoader loader = new java.net.URLClassLoader(
                new java.net.URL[]{new java.io.File(folder).toURI().toURL()}
            );
            Class<?> clazz = loader.loadClass(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            
            // Call the execute method
            java.lang.reflect.Method executeMethod = clazz.getMethod("execute");
            executeMethod.invoke(instance);
            
            loader.close();
        } catch (Exception e) {
            System.out.println("Error running " + className + ": " + e.getMessage());
        }
    }
}