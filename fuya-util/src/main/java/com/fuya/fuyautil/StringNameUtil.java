package com.fuya.fuyautil;

public class StringNameUtil {
    private static String string = "abcdefghijklmnopqrstuvwxyz";
    private static int getRandom(int count) {
            return (int) Math.round(Math.random() * (count));
    }



    public static String getRandomString(int length){
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len-1)));
        }
        return sb.toString();
    }
}
