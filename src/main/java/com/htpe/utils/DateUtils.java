package com.htpe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateUtils {
    
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";
    
    /**
     * 時間轉字串
     * @param date Date 需要格式化的时间
     * @param pattern 指定格式化后的格式，如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) {
        if(pattern.isEmpty()){
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    
    /**
     * 開始時間&結束時間
     * @param date 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String startAndEnd(String date, String type){
       if(type.equals("start")) {
    	   date = date.substring(0,10);
    	   date = date +" 00:00:00" ;
       }else if(type.equals("end")) {    	   
    	   date = date.substring(0,10);
    	   date = date +" 23:59:59" ;
       }
 
        return date;
    }


    
}
