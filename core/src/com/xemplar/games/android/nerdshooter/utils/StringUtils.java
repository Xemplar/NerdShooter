/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
 *
 * This file is part of NerdShooter
 *
 * NerdShooter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License, or
 * any later version.
 *
 * NerdShooter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
