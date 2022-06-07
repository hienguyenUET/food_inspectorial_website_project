package com.example.food_inspectorate_website_project.design_pattern;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }
    public static Singleton getInstance() {
        // Do something before get instance ...
        if (instance == null) {
            // Do the task too long before create instance ...
            // Block so other threads cannot come into while initialize
            synchronized (Singleton.class) {
                // Re-check again. Maybe another thread has initialized before
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        // Do something after get instance ...
        return instance;
    }
}
