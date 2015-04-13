package com.xemplar.games.android.nerdshooter.utils;

public final class StringUtils {
    public static String getBetween(String input, String regex){
        String out = "";

        try{
            int firstRegex = input.indexOf(regex, 0) + regex.length();
            int secondRegex = input.indexOf(regex, firstRegex);

            out = input.substring(firstRegex, secondRegex);
        } catch (Exception e){

        }
        return out;
    }

    public static String getBetween(String input, String regex1, String regex2){
        String out = "";

        try{
            int firstRegex = input.indexOf(regex1, 0) + regex1.length();
            int secondRegex = input.indexOf(regex2, firstRegex);

            out = input.substring(firstRegex, secondRegex);
        } catch (Exception e){

        }
        return out;
    }

    public static String getBetween(int start, String input, String regex){
        String out = "";

        try{
            int firstRegex = input.indexOf(regex, start) + regex.length();
            int secondRegex = input.indexOf(regex, firstRegex);

            out = input.substring(firstRegex, secondRegex);
        } catch (Exception e){

        }
        return out;
    }

    public static String getBetween(int start, String input, String regex1, String regex2){
        String out = "";

        try{
            int firstRegex = input.indexOf(regex1, start) + regex1.length();
            int secondRegex = input.indexOf(regex2, firstRegex);

            out = input.substring(firstRegex, secondRegex);
        } catch (Exception e){

        }
        return out;
    }

	private StringUtils(){}
}
