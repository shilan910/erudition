package com.erudition.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CrazyCodess on 2016/5/1.
 */
public class RegexUtils {

    public static String getPicPath(String content){

        Pattern pattern0 = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher matcher0 = pattern0.matcher(content);

        if(matcher0.find()) {
            String stringOfImg = content.substring(matcher0.start(), matcher0.end());
            Pattern pattern = Pattern.compile("src=\"[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*");
            Matcher matcher = pattern.matcher(stringOfImg);
            if(matcher.find()) {
                return stringOfImg.substring(matcher.start()+5,matcher.end());
            }
        }

        return null;
    }
}
