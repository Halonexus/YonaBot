package com.halonexus.yonabot.base;

public enum Category {
    ACTION("Action"),
    MISC("Misc");

    private final String name;

    Category(String name){
        this.name = name;
    }

    public Category getCategoryFromString(String categoryName){
        for(Category c : Category.values()){
            if(c.name.equalsIgnoreCase(categoryName)){
                return c;
            }
        }
        return null;
    }

}
