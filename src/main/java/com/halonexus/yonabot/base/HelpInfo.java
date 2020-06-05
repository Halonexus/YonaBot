package com.halonexus.yonabot.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HelpInfo {
    @Getter
    private final String description;
    @Getter
    private final String usage;
    @Getter
    private final Map<String,String> arguments = new HashMap<>();

    public HelpInfo(String description, String usage){
        this.description = description;
        this.usage = usage;
    }

    public HelpInfo addArgument(String argument, String argumentInfo){
        arguments.put(argument,argumentInfo);
        return this;
    }
}
