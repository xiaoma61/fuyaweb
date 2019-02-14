package com.fuya.fuyautil;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {
    //获取sqldate
    public static Date getsqldate(java.util.Date date){
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    //获取stringdate
    public static String getstringdate(java.sql.Date sqldate){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        return date.toString();

    }
    //获取stringdate
    public static java.util.Date datetostring(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);

    }


}
