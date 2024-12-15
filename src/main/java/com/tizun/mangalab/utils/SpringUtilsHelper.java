package com.tizun.mangalab.utils;

public class SpringUtilsHelper {
    public String[] split(String input, String delimiter) {
        if (input != null) {
            return input.split(delimiter);
        }
        return new String[0];
    }
}
