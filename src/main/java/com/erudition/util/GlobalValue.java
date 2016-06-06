package com.erudition.util;

/**
 * Created by tsj on 16-6-2.
 */
public class GlobalValue {
    private GlobalValue(){
    }
    private static int THRESHOLD=3;

    public static int getTHRESHOLD() {
        return THRESHOLD;
    }

    public static void setTHRESHOLD(int THRESHOLD) {
        GlobalValue.THRESHOLD = THRESHOLD;
    }
}
