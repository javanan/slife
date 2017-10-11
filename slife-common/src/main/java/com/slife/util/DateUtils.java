package com.slife.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chen on 2017/9/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class DateUtils {
    /**
     * 根据传入的日期格式化输出
     * yyyy-MM-dd 的字符串时间
     *
     * @param date      时间
     * @param formatStr yyyy-MM-dd等时间串
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String outDate = formatter.format(date);
        return outDate;
    }
}
