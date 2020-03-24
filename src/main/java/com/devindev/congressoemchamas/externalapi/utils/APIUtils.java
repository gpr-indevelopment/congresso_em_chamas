package com.devindev.congressoemchamas.externalapi.utils;

public class APIUtils {

    public static String convertToQueryString(String input){
        return input.replaceAll(" ", "%20");
    }

    public static String convertLineJumpsToSpace(String input){
        return input.replaceAll("\\n", "");
    }
}
