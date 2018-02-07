package com.nag.util;

/**
 * Created by nagendra on 1/14/18.
 */
public class TextUtil {
    public String sanitize(String s){
        return s.replaceAll("\\+s", "");
    }
}
