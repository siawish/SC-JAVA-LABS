package com.recipeengine;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private String category;
    private int prepTime;
    private String difficulty;
    private double estimatedCost;
    private List<String> moods;
    private String description;
    
    public Recipe(String name, List<String> ingredients, String category, 
                  int prepTime, String difficulty, double estimatedCost, 
                  List<String> moods, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
        this.estimatedCost = estimatedCost;
        this.moods = moods;
        this.description = description;
    }
    
    public String getName() { return name; }
    public List<String> getIngredients() { return ingredients; }
    public String getCategory() { return category; }
    public int getPrepTime() { return prepTime; }
    public String getDifficulty() { return difficulty; }
    public double getEstimatedCost() { return estimatedCost; }
    public List<String> getMoods() { return moods; }
    public String getDescription() { return description; }
    
    public boolean isHealthy() {
        return category.equalsIgnoreCase("healthy") || prepTime > 20;
    }
    
    public boolean isFastFood() {
        return prepTime <= 15;
    }
    
    public boolean isLowBudget() {
        return estimatedCost <= 10.0;
    }
    
    public boolean isBeginnerFriendly() {
        return difficulty.equalsIgnoreCase("beginner") && ingredients.size() <= 5;
    }
    
    public boolean isFusion() {
        return category.toLowerCase().contains("fusion");
    }
}
