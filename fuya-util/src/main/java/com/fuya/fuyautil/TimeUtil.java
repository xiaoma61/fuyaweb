package com.fuya.fuyautil;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {
    //获取sqldate
    public static Date getsqldate(java.util.Date date){
        Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }
//    //获取stringdate
//    public static String getstringdate(java.sql.Date sqldate){
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = null;
//        return date.toString();
//
//    }
    //获取stringdate
    public static String datetostring(Date date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String str=sdf.format(date);
        return str;

    }
    public static Date stringtodate(String dateS) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateS);
        return getsqldate(date);

    }


}
