package com.recipeengine;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    
    public static void main(String[] args) {
        clearScreen();
        System.out.println("\n=== RECIPE SUGGESTION ENGINE ===\n");
        
        // Login/Register
        if (!loginOrRegister()) {
            System.out.println("Exiting...");
            return;
        }
        
        
        while (true) {
            clearScreen();
            System.out.println("\n=== RECIPE SUGGESTION ENGINE ===");
            System.out.println("Welcome, " + currentUser.getUsername() + "!\n");
            showMainMenu();
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> moodBasedRecipe();
                case 2 -> analyzeRecipe();
                case 3 -> {
                    System.out.println("\nThanks for using Recipe Engine! Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static boolean loginOrRegister() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            
            int choice = getIntInput("\nYour choice: ");
            
            switch (choice) {
                case 1:
                    if (login()) {
                        return true;
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static boolean login() {
        try {
            System.out.print("\nUsername: ");
            String username = scanner.nextLine().trim();
            
            if (username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty!");
            }
            
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            if (password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty!");
            }
            
            User user = UserManager.loginUser(username, password);
            if (user != null) {
                currentUser = user;
                System.out.println("\nLogin successful! Welcome, " + username + "!");
                Thread.sleep(1500);
                return true;
            } else {
                System.out.println("\nInvalid username or password!");
                Thread.sleep(1500);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("\nLogin interrupted!");
            return false;
        } catch (Exception e) {
            System.out.println("\nUnexpected error during login: " + e.getMessage());
            return false;
        }
    }
    
    private static void register() {
        try {
            System.out.print("\nEnter username: ");
            String username = scanner.nextLine().trim();
            
            if (username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty!");
            }
            
            if (username.length() < 3) {
                throw new IllegalArgumentException("Username must be at least 3 characters!");
            }
            
            if (UserManager.userExists(username)) {
                System.out.println("\nUsername already exists! Try another one.");
                Thread.sleep(1500);
                return;
            }
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            if (password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty!");
            }
            
            if (password.length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters!");
            }
            
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            
            if (email.isEmpty() || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email format!");
            }
            
            if (UserManager.registerUser(username, password, email)) {
                System.out.println("\nRegistration successful! You can now login.");
            } else {
                System.out.println("\nRegistration failed!");
            }
            
            Thread.sleep(1500);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("\nRegistration interrupted!");
        } catch (Exception e) {
            System.out.println("\nUnexpected error during registration: " + e.getMessage());
        }
    }
    
    private static void showMainMenu() {
        System.out.println("  1. Mood-Based Recipe Generator");
        System.out.println("  2. Analyze Recipe Categories");
        System.out.println("  3. Exit\n");
    }
    
    private static void moodBasedRecipe() {
        try {
            clearScreen();
            System.out.println("\n=== MOOD-BASED RECIPE GENERATOR ===\n");
            
            System.out.println("Select your mood:");
            System.out.println("1. Tired");
            System.out.println("2. Romantic");
            System.out.println("3. Hungry");
            System.out.println("4. Angry");
            System.out.println("5. Sad");
            System.out.println("6. Gym Day");
            System.out.println("7. Rainy Weather");
            
            int moodChoice = getIntInput("\nYour mood: ");
            
            if (moodChoice < 1 || moodChoice > 7) {
                throw new IllegalArgumentException("Invalid mood choice! Please select 1-7.");
            }
            
            String mood = switch (moodChoice) {
                case 1 -> "tired";
                case 2 -> "romantic";
                case 3 -> "hungry";
                case 4 -> "angry";
                case 5 -> "sad";
                case 6 -> "gym";
                case 7 -> "rainy";
                default -> "hungry";
            };
            
            System.out.println("\nGenerating recipe for " + mood + " mood...\n");
            
            Recipe recipe = RecipeDatabase.getRandomRecipeByMood(mood);
            
            if (recipe == null) {
                throw new NullPointerException("No recipes found for this mood.");
            }
            
            displayRecipe(recipe);
            
            System.out.println("\nRecipe Description:");
            System.out.println("-----------------------------------------------------------");
            System.out.println(recipe.getDescription());
            System.out.println("\n-----------------------------------------------------------");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nUnexpected error: " + e.getMessage());
        }
    }
    
    private static void analyzeRecipe() {
        try {
            clearScreen();
            System.out.println("\n=== RECIPE ANALYZER ===\n");
            
            List<Recipe> allRecipes = RecipeDatabase.getAllRecipes();
            
            if (allRecipes == null || allRecipes.isEmpty()) {
                throw new IllegalStateException("No recipes available in database!");
            }
            
            System.out.println("Available Recipes:");
            for (int i = 0; i < allRecipes.size(); i++) {
                System.out.println((i + 1) + ". " + allRecipes.get(i).getName());
            }
            
            int choice = getIntInput("\nSelect recipe to analyze: ");
            
            if (choice < 1 || choice > allRecipes.size()) {
                throw new IndexOutOfBoundsException("Invalid choice! Please select between 1 and " + allRecipes.size());
            }
            
            Recipe recipe = allRecipes.get(choice - 1);
            
            if (recipe == null) {
                throw new NullPointerException("Recipe not found!");
            }
            
            displayRecipe(recipe);
            
            System.out.println("\nCATEGORY ANALYSIS:");
            System.out.println("-----------------------------------------------------------");
            
            if (recipe.isHealthy()) System.out.println("- Healthy");
            if (recipe.isFastFood()) System.out.println("- Fast-Food");
            if (recipe.isFusion()) System.out.println("- Creative Fusion");
            if (recipe.isLowBudget()) System.out.println("- Low-Budget");
            if (recipe.isBeginnerFriendly()) System.out.println("- Beginner-Friendly");
            
            System.out.println("\n-----------------------------------------------------------");
        } catch (IllegalStateException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nUnexpected error: " + e.getMessage());
        }
    }
    
    private static void displayRecipe(Recipe recipe) {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("  RECIPE: " + recipe.getName());
        System.out.println("-----------------------------------------------------------");
        System.out.println("  Category: " + recipe.getCategory());
        System.out.println("  Prep Time: " + recipe.getPrepTime() + " minutes");
        System.out.println("  Difficulty: " + recipe.getDifficulty());
        System.out.println("  Estimated Cost: $" + String.format("%.2f", recipe.getEstimatedCost()));
        System.out.println("-----------------------------------------------------------");
        System.out.println("  Ingredients:");
        for (String ingredient : recipe.getIngredients()) {
            System.out.println("    - " + ingredient);
        }
        System.out.println("-----------------------------------------------------------");
    }
    
    private static int getIntInput(String prompt) {
        try {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("Please enter a valid number: ");
            }
            int result = scanner.nextInt();
            scanner.nextLine();
            return result;
        } catch (Exception e) {
            System.out.println("\nError reading input: " + e.getMessage());
            scanner.nextLine();
            return -1;
        }
    }
    
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(3));
        }
    }
}
