package com.halonexus.yonabot.base;

public enum Category {
    ACTION("Action",0),
    MISC("Misc",1),
    INFO("Info",2);

    private final String name;
    private final int priority;

    Category(String name, int priority){
        this.name = name;
        this.priority = priority;
    }

    public Category getCategoryFromString(String categoryName){
        for(Category c : Category.values()){
            if(c.name.equalsIgnoreCase(categoryName)){
                return c;
            }
        }
        return null;
    }
    public String getName(){return name;}
    public int getPriority(){return priority;}
}
