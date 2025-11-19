package com.recipeengine;

import java.util.HashMap;

public class UserManager {
    private static HashMap<String, User> users = new HashMap<>();
    
    static {
      
        users.put("admin", new User("admin", "admin123", "admin@recipe.com"));
        users.put("guest", new User("guest", "guest123", "guest@recipe.com"));
    }
    
    public static boolean registerUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            return false; 
        }
        users.put(username, new User(username, password, email));
        return true;
    }
    
    public static User loginUser(String username, String password) {
        try {
            if (username == null || password == null) {
                throw new IllegalArgumentException("Username and password cannot be null!");
            }
            
            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error during login: " + e.getMessage());
            return null;
        }
    }
    
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    public static int getTotalUsers() {
        return users.size();
    }
}
