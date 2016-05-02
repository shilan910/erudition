package com.erudition.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sl on 16-3-1.
 */
public class DateTurn {

    /**
     * 传入时间戳，转换成距离当前时间的时间字符串
     * @param time:String
     * @return
     */
    public static String Turn(long time) {

        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();

        long nowTime = nowDate.getTime();

        int minute = (int)((nowTime-time)/60000);

        if (minute == 0) {
            return "1分钟前";
        } else if (minute < 60) {
            return minute + "分钟前";
        } else if (minute/60 < 24) {
            return minute / 60 + "小时前";
        } else if (minute/ (24 * 60) <= 30) {
            return minute / (24 * 60) + "天前";
        } else {
            return "很久以前";
        }

    }


    public static String Turn(Date date){

        long time = date.getTime();
        return Turn(time);

    }


//
//    public static void main(String args[]) throws ParseException {
//        DateTurn dateTurn = new DateTurn();
//
//        long t = 1454320399000L;
//
//        System.out.println(dateTurn.Turn(t));
//    }



}
