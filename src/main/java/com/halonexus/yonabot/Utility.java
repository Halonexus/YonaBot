package com.halonexus.yonabot;

import java.util.regex.Pattern;

public class Utility {
    public static final String ARGS_PATTERN = "\\s+";

    private Utility() {
    }

    public static String[] splitArguments(String arguments) {
        Pattern splitPattern = Pattern.compile(ARGS_PATTERN);
        return splitPattern.split(arguments);
    }

    public static String[] splitArguments(String arguments, int parts) {
        Pattern splitPattern = Pattern.compile(ARGS_PATTERN);
        return splitPattern.split(arguments, parts);
    }

    public static String[] splitString(String arguments, String pattern) {
        Pattern splitPattern = Pattern.compile(pattern);
        return splitPattern.split(arguments);
    }

    public static String[] splitString(String arguments, String pattern, int parts) {
        Pattern splitPattern = Pattern.compile(pattern);
        return splitPattern.split(arguments, parts);
    }

    public static String capitalise(String in) {
        if (in.isBlank() || in.isEmpty()) {
            return in;
        }
        return in.substring(0, 1).toUpperCase() + in.substring(1).toLowerCase();
    }
}
