package com.ninetaildemonfox.zdl.tongcheng_cai.utlis;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by NineTailDemonFox on 2019/3/14.
 */

public class DateUtils {

    /**
     * 时区差问题处理
     * @param time 时间戳
     * @param pattern 时间格式
     * @return
     */
    public static String formatDateByTimeZone(long time, String pattern) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08"));
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return sdf.format(date);
    }

    /*
   * 将时间转换为时间戳
   */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

}
