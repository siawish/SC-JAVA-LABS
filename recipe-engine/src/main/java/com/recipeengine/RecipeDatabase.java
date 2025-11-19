package com.recipeengine;

import java.util.*;

public class RecipeDatabase {
    private static List<Recipe> recipes = new ArrayList<>();
    
    static {
        initializeRecipes();
    }
    
    private static void initializeRecipes() {
        recipes.add(new Recipe(
            "Creamy Tomato Soup with Grilled Cheese",
            Arrays.asList("Tomatoes", "Cream", "Bread", "Cheese", "Butter"),
            "Comfort Food",
            25,
            "Beginner",
            8.0,
            Arrays.asList("rainy", "sad", "tired"),
            "Warm and comforting soup perfect for cozy days"
        ));
        
        recipes.add(new Recipe(
            "Protein Power Bowl",
            Arrays.asList("Chicken Breast", "Quinoa", "Broccoli", "Avocado", "Olive Oil"),
            "Healthy",
            30,
            "Intermediate",
            12.0,
            Arrays.asList("gym", "hungry"),
            "High-protein meal for muscle recovery"
        ));
        
        recipes.add(new Recipe(
            "Romantic Pasta Carbonara",
            Arrays.asList("Pasta", "Eggs", "Bacon", "Parmesan", "Black Pepper"),
            "Italian",
            20,
            "Intermediate",
            10.0,
            Arrays.asList("romantic", "hungry"),
            "Classic Italian dish perfect for date night"
        ));
        
        recipes.add(new Recipe(
            "Quick Veggie Stir-Fry",
            Arrays.asList("Mixed Vegetables", "Soy Sauce", "Garlic", "Ginger", "Rice"),
            "Healthy",
            15,
            "Beginner",
            7.0,
            Arrays.asList("tired", "hungry"),
            "Fast and nutritious meal"
        ));
        
        recipes.add(new Recipe(
            "Spicy Angry Chicken Wings",
            Arrays.asList("Chicken Wings", "Hot Sauce", "Butter", "Garlic Powder", "Cayenne"),
            "Fast-Food",
            35,
            "Beginner",
            9.0,
            Arrays.asList("angry", "hungry"),
            "Fiery wings to match your mood"
        ));
        
        recipes.add(new Recipe(
            "Comfort Mac and Cheese",
            Arrays.asList("Macaroni", "Cheddar", "Milk", "Butter", "Breadcrumbs"),
            "Comfort Food",
            20,
            "Beginner",
            6.0,
            Arrays.asList("sad", "rainy", "tired"),
            "Ultimate comfort food"
        ));
        
        recipes.add(new Recipe(
            "Energy Smoothie Bowl",
            Arrays.asList("Banana", "Berries", "Protein Powder", "Almond Milk", "Granola"),
            "Healthy",
            10,
            "Beginner",
            8.0,
            Arrays.asList("gym", "tired"),
            "Quick energy boost"
        ));
        
        recipes.add(new Recipe(
            "Romantic Chocolate Fondue",
            Arrays.asList("Dark Chocolate", "Cream", "Strawberries", "Marshmallows", "Bananas"),
            "Dessert",
            15,
            "Beginner",
            12.0,
            Arrays.asList("romantic"),
            "Sweet and shareable dessert"
        ));
        
        recipes.add(new Recipe(
            "Korean-Mexican Fusion Tacos",
            Arrays.asList("Beef", "Kimchi", "Tortillas", "Gochujang", "Cilantro"),
            "Creative Fusion",
            25,
            "Intermediate",
            11.0,
            Arrays.asList("hungry", "angry"),
            "Bold fusion of Korean and Mexican flavors"
        ));
        
        recipes.add(new Recipe(
            "Budget Ramen Upgrade",
            Arrays.asList("Instant Ramen", "Egg", "Green Onions", "Soy Sauce", "Sesame Oil"),
            "Fast-Food",
            10,
            "Beginner",
            4.0,
            Arrays.asList("tired", "hungry", "sad"),
            "Elevated instant ramen"
        ));
    }
    
    public static List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
    
    public static List<Recipe> getRecipesByMood(String mood) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getMoods().contains(mood.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }
    
    public static Recipe getRandomRecipeByMood(String mood) {
        List<Recipe> moodRecipes = getRecipesByMood(mood);
        if (moodRecipes.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return moodRecipes.get(random.nextInt(moodRecipes.size()));
    }
}
